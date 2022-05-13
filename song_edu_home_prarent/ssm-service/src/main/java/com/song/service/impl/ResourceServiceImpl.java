package com.song.service.impl;

import com.github.pagehelper.PageInfo;
import com.song.dao.ResourceMapper;
import com.song.domain.Resource;
import com.song.domain.ResourceCategory;
import com.song.domain.ResourceVO;
import com.song.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class ResourceServiceImpl implements ResourceService {


    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 资源信息分页&条件查询
     *
     * @param resourceVO
     */
    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);
        PageInfo<Resource> adPageInfo = new PageInfo<>(allResource);
        return adPageInfo;
    }


    /**
     * 添加资源信息
     *
     * @param resource
     */
    @Override
    public void saveResource(Resource resource) {
        // 补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        // 调用Mapper
        resourceMapper.saveResource(resource);
    }

    /**
     * 根据资源id 资源信息(回显)
     *
     * @param id
     */
    @Override
    public Resource findResourceById(Integer id) {
        return resourceMapper.findResourceById(id);
    }

    /**
     * 修改资源信息
     *
     * @param resource
     */
    @Override
    public void updateResource(Resource resource) {
        // 补全信息
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("system1");

        // 调用Mapper
        resourceMapper.updateResource(resource);
    }

    /**
     * 删除资源信息
     *
     * @param id
     */
    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
