package com.song.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.dao.UserMapper;
import com.song.domain.*;
import com.song.service.UserService;
import com.song.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页获取用户数据&条件查询用户数据
     *
     * @param userVO
     */
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateUserStatus(Integer id, String status) {
        //补全信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);

    }

    /**
     * 用户登录
     *
     * @param user
     */
    @Override
    public User login(User user) throws Exception {

        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(), "song", user2.getPassword())) {
            return user2;
        } else {
            return null;
        }
    }

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void register(User user) throws Exception {


        User user1 = new User();
        user1.setPassword(Md5.md5(user.getPassword(), "song"));

        // 补全信息
        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        user.setPassword(user1.getPassword());
        user.setStatus("ENABLE");
        user.setIs_del(0);

        userMapper.register(user);
    }

    /**
     * 删除用户 (修改is_del)
     *
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        //修改is_del
        user.setIs_del(1);

        userMapper.deleteUser(user);
    }

    /**
     * 为用户分配角色信息
     *
     * @param userRoleVO
     */
    @Override
    public void userContextRole(UserRoleVO userRoleVO) {

        // 清空中间表 的关联关系
        userMapper.deleteUserContextRole(userRoleVO.getUserId());

        // 补全信息
        for (Integer rid : userRoleVO.getRoleIdList()) {
            User_role_relation user_role_relation = new User_role_relation();
            user_role_relation.setUserId(userRoleVO.getUserId());
            user_role_relation.setRoleId(rid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    /**
     * 根据ID查询用户当前角色
     *
     * @param id
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        return userMapper.findUserRelationRoleById(id);
    }

    /**
     * 获取用户权限
     *
     * @param id
     */
    @Override
    public ResponseResult getUserPermissions(Integer id) {

        //1.获取当前用户拥有的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //2.获取角色id,保存到 list
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }

        //3.根据角色id查询 父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        //4.封装父菜单下的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6.封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        //menuList: 菜单权限数据
        map.put("resourceList", resourceList);
        //resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }


}
