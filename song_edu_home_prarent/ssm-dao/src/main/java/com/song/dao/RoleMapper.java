package com.song.dao;

import com.song.domain.*;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询角色列表(条件)
     */
    public List<Role> findAllRole(Role role);

    /**
     * 添加角色
     */
    public void saveRole(Role role);

    /**
     * 根据id 查询回显角色信息
     */
    public Role findRoleById(int id);

    /**
     * 修改角色
     */
    public void updateRole(Role role);

    /**
     * 根据角色ID 查询关联菜单ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 根据roleId 清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer roleId);

    /**
     * 为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     */
    public void deleteRole(Integer id);


    /**
     * * 查询角色拥有的 资源信息
     * 查询当前角色拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);

    /**
     * * 查询角色拥有的 资源信息
     * 查询当前角色拥有的资源信息
     */
    public List<Resource> findResourceByRoleId(Integer roleId);

//    /**
//     * 根据角色ID 查询关联资源ID
//     */
//    public List<Integer> findResourceByRoleId(Integer roleId);

    /**
     * 根据resourceId 清空中间表的关联关系
     */
    public void deleteRoleContextResource(Integer resourceId);

    /**
     * 为角色分配资源信息
     */
    public void roleContextResource(Role_resource_relation role_resource_relation);


}
