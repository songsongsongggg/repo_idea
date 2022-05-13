package com.song.controller;

import com.song.domain.*;
import com.song.service.MenuService;
import com.song.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表查询&条件查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        try {
            List<Role> allRole = roleService.findAllRole(role);
            return new ResponseResult(true, 200, "响应成功", allRole);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 添加&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        try {
            if (role.getId() == null) {
                roleService.saveRole(role);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                roleService.updateRole(role);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据角色id 查询角色信息(回显)
     */
    @RequestMapping("/findRoleById")
    public ResponseResult findRoleById(@RequestParam Integer id) {
        try {
            Role roleById = roleService.findRoleById(id);
            return new ResponseResult(true, 200, "响应成功", roleById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 查询所有菜单信息
     */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        try {
            //-1 表示查询所有菜单数据
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("parentMenuList", menuList);
            return new ResponseResult(true, 200, "响应成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据角色ID查询关联菜单ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam Integer roleId) {
        try {
            List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
            return new ResponseResult(true, 200, "响应成功", menuByRoleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {
        try {
            roleService.roleContextMenu(roleMenuVO);
            return new ResponseResult(true, 200, "响应成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(@RequestParam Integer id) {
        try {
            roleService.deleteRole(id);
            return new ResponseResult(true, 200, "响应成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取角色拥有的 资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(@RequestParam Integer roleId) {
        try {
            ResponseResult result = roleService.findResourceListByRoleId(roleId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * 根据角色ID 查询关联 资源ID
//     */
//    @RequestMapping("/findResourceByRoleId")
//    public ResponseResult findResourceByRoleId(@RequestParam Integer roleId) {
//        try {
//            List<Integer> resourceByRoleId = roleService.findResourceByRoleId(roleId);
//            return new ResponseResult(true, 200, "响应成功", resourceByRoleId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 为角色分配 资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVO roleResourceVO) {
        try {
            roleService.roleContextResource(roleResourceVO);
            return new ResponseResult(true, 200, "响应成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}