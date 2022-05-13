package com.song.dao;

import com.song.domain.*;

import java.util.List;

public interface UserMapper {

    /**
     * 分页获取用户数据&条件查询用户数据
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(User user);

    /**
     * 用户登录
     */
    public User login(User user);

    /**
     * 注册用户
     */
    public void register(User user);

    /**
     * 删除用户
     */
    public void deleteUser(User user);

    /**
     * 根据userId 清空中间表的关联关系
     */
    public void deleteUserContextRole(Integer userId);

    /**
     * 为用户分配角色信息
     */
    public void userContextRole(User_role_relation user_role_relation);

    /**
     * 1、根据ID查询用户当前角色
     */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 2、根据角色id,查询角色拥有的顶级菜单信息
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 3、根据PID 查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 4、获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
    
    public List<Resource> findResourceByRoleId2(List<Integer> ids);
    
    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
    public void test7();
    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
    public void test7();







    
    
    
    
    
    
    
    
    
    

}
