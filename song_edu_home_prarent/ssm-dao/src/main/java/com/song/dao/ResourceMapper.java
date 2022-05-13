package com.song.dao;

import com.song.domain.Resource;
import com.song.domain.ResourceCategory;
import com.song.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /**
     * 资源信息分页&条件查询
     */
    public List<Resource> findAllResource(ResourceVO resourceVO);

    /**
     * 添加资源信息
     */
    public void saveResource(Resource resource);

    /**
     * 根据id 回显资源信息
     */
    public Resource findResourceById(Integer id);

    /**
     * 修改资源信息
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源信息
     */
    public void deleteResource(Integer id);


}
