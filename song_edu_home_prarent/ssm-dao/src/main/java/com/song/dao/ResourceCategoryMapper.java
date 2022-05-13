package com.song.dao;

import com.song.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /**
     * 查询资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 添加资源分类信息
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);


    /**
     * 根据资源分类id 查询资源分类信息(回显)
     */
    public ResourceCategory findResourceCategoryById(int id);

    /**
     * 修改资源分类信息
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 删除资源分类信息
     */
    public void deleteResourceCategory(int id);

}
