package com.song.service;

import com.github.pagehelper.PageInfo;
import com.song.domain.*;

import java.util.List;


public interface UserService {
    /**
     * 分页获取用户数据&条件查询用户数据
     */
    public PageInfo findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(Integer id, String status);

    /**
     * 用户登录
     */
    public User login(User user) throws Exception;

    /**
     * 注册用户
     */
    public void register(User user) throws Exception;

    /**
     * 删除用户 (修改is_del)
     */
    public void deleteUser(User user);

    /**
     * 为用户分配角色信息
     */
    public void userContextRole(UserRoleVO userRoleVO);

    /**
     * 根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);

}
