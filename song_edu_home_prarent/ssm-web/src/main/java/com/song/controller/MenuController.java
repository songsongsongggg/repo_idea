package com.song.controller;

import com.song.domain.Menu;
import com.song.domain.ResponseResult;
import com.song.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        try {
            List<Menu> allMenu = menuService.findAllMenu();
            return new ResponseResult(true, 200, "响应成功", allMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据菜单ID 查询菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam Integer id) {

        try {
            if (id == -1) {
                //添加操作 回显不需要查询 menu信息
                List<Menu> menuList = menuService.findSubMenuListByPid(-1);

                //封装数据
                Map<String, Object> map = new HashMap<>();
                map.put("menuInfo", null);
                map.put("parentMenuList", menuList);

                return new ResponseResult(true, 200, "响应成功", map);
            } else {
                //修改操作 回显
                Menu menuById = menuService.findMenuById(id);
                List<Menu> menuList = menuService.findSubMenuListByPid(-1);
                //封装数据
                Map<String, Object> map = new HashMap<>();
                map.put("menuInfo", menuById);
                map.put("parentMenuList", menuList);

                return new ResponseResult(true, 200, "响应成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加&修改菜单
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {
        try {
            if (menu.getId() == null) {
                // 添加
                menuService.saveMenu(menu);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                // 修改
                menuService.updateMenu(menu);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(@RequestParam Integer id) {
        try {
            menuService.deleteMenu(id);
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
