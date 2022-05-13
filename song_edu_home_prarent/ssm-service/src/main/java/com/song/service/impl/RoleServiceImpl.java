package com.song.service.impl;

import com.song.dao.RoleMapper;
import com.song.domain.*;

import com.song.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询角色列表(条件)
     *
     * @param role
     */
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        //补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("15014354444");
        role.setUpdatedBy("15043523215");

        roleMapper.saveRole(role);
    }

    /**
     * 根据id 查询回显角色信息
     *
     * @param id
     */
    @Override
    public Role findRoleById(Integer id) {
        return roleMapper.findRoleById(id);
    }

    /**
     * 修改角色
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        //补全信息
        role.setUpdatedTime(new Date());
        role.setUpdatedBy("2312312314");

        roleMapper.updateRole(role);
    }

    /**
     * 根据角色ID 查询关联菜单ID
     *
     * @param roleId
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    /**
     * 为角色分配菜单信息
     *
     * @param roleMenuVO
     */
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {
        // 清空中间表 的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        // 为角色分配菜单信息
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    public void deleteRole(Integer id) {
        // 清空中间表
        roleMapper.deleteRoleContextMenu(id);

        //删除角色
        roleMapper.deleteRole(id);
    }

    /**
     * * 查询角色拥有的 资源信息
     * @param roleId
     */
    @Override
    public ResponseResult findResourceListByRoleId(Integer roleId) {

        // 1、获取角色拥有的资源分类信息
        List<ResourceCategory> resourceCategoryByRoleId = roleMapper.findResourceCategoryByRoleId(roleId);

        // 2、获取角色拥有的 信息资源
        List<Resource> resourceByRoleId = roleMapper.findResourceByRoleId(roleId);

        // 3、将资源信息封装到资源分类信息的resourceList中
        for (ResourceCategory resourceCategory : resourceCategoryByRoleId) {
            List<Resource> list = new ArrayList<>();
            for (Resource resource : resourceByRoleId) {
                if (resource.getCategoryId() == resourceCategory.getId()){
                    list.add(resource);
                    resourceCategory.setResourceList(list);
                }
            }
        }

        return new ResponseResult(true,200,"响应成功",resourceCategoryByRoleId);
    }


//    /**
//     * 根据角色ID 查询关联 资源ID
//     *
//     * @param roleId
//     */
//    @Override
//    public List<Integer> findResourceByRoleId(Integer roleId) {
//        return roleMapper.findResourceByRoleId(roleId);
//    }

    /**
     * 为角色分配 资源信息
     *
     * @param roleResourceVO
     */
    @Override
    public void roleContextResource(RoleResourceVO roleResourceVO) {
        // 清空中间表 的关联关系
        roleMapper.deleteRoleContextResource(roleResourceVO.getRoleId());

        // 为角色分配菜单信息
        for (Integer rid : roleResourceVO.getResourceIdList()) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setResourceId(rid);
            role_resource_relation.setRoleId(roleResourceVO.getRoleId());
            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");

            roleMapper.roleContextResource(role_resource_relation);
        }
    }
}
