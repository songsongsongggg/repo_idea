package com.song.dao;

import com.song.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询全部的父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     */
    public List<Menu> findAllMenu();

    /**
     * 根据菜单ID 查询菜单信息
     */
    public Menu findMenuById(int id);

    /**
     * 添加菜单
     */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单
     */
    public void updateMenu(Menu menu);

    /**
     * 删除菜单
     */
    public void deleteMenu(Integer id);

}
