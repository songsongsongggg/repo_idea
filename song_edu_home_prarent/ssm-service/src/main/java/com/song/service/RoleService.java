package com.song.service;

import com.song.domain.*;

import java.util.List;

public interface RoleService {

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
    public Role findRoleById(Integer id);

    /**
     * 修改角色
     */
    public void updateRole(Role role);

    /**
     * 根据角色ID 查询关联菜单ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /**
     * 删除角色
     */
    public void deleteRole(Integer id);

    /**
     * 查询角色拥有的 资源信息
     */
    public ResponseResult findResourceListByRoleId(Integer roleId);


//    /**
//     * 根据角色ID 查询关联 资源ID
//     */
//    public List<Integer> findResourceByRoleId(Integer resourceId);

    /**
     * 为角色分配 资源信息
     */
    public void roleContextResource(RoleResourceVO roleResourceVO);




}
