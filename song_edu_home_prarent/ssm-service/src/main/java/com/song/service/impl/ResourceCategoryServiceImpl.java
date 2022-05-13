package com.song.service.impl;

import com.song.dao.ResourceCategoryMapper;
import com.song.domain.ResourceCategory;
import com.song.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询资源分类信息
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    /**
     * 添加资源分类信息
     *
     * @param resourceCategory
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /**
     * 根据资源分类id 查询资源分类信息(回显)
     *
     * @param id
     */
    @Override
    public ResourceCategory findResourceCategoryById(Integer id) {
        return resourceCategoryMapper.findResourceCategoryById(id);
    }

    /**
     * 修改资源分类信息
     *
     * @param resourceCategory
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("system1");

        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /**
     * 删除资源分类信息
     *
     * @param id
     */
    @Override
    public void deleteResourceCategory(int id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
