package com.song.service.impl;

import com.song.dao.MenuMapper;
import com.song.domain.Menu;
import com.song.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    /**
     * 查询全部的父子菜单信息
     *
     * @param pid
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        return menuMapper.findSubMenuListByPid(pid);
    }

    /**
     * 查询菜单列表
     */
    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    /**
     * 根据菜单ID 查询菜单信息(回显)
     *
     * @param id
     */
    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    /**
     * 添加菜单
     *
     * @param menu
     */
    @Override
    public void saveMenu(Menu menu) {

        menuMapper.saveMenu(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {

        menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
}
