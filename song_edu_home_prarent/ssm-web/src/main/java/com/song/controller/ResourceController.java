package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.domain.Resource;
import com.song.domain.ResourceCategory;
import com.song.domain.ResourceVO;
import com.song.domain.ResponseResult;
import com.song.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源信息分页&条件查询
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO) {

        try {
            PageInfo<Resource> allResource = resourceService.findAllResource(resourceVO);
            return new ResponseResult(true, 200, "响应成功", allResource);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加&更新资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        try {
            if (resource.getId() == null) {
                resourceService.saveResource(resource);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                resourceService.updateResource(resource);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id 查询资源信息
     */
    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(@RequestParam Integer id) {
        try {
            Resource resourceById = resourceService.findResourceById(id);
            return new ResponseResult(true, 200, "响应成功", resourceById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(@RequestParam Integer id) {
        try {
            resourceService.deleteResource(id);
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
