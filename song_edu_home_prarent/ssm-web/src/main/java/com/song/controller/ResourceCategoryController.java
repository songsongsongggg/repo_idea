package com.song.controller;

import com.song.domain.ResourceCategory;
import com.song.domain.ResponseResult;
import com.song.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询资源分类信息
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        try {
            List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
            return new ResponseResult(true, 200, "响应成功", allResourceCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 添加& 修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory) {
        try {
            if (resourceCategory.getId() == null) {
                resourceCategoryService.saveResourceCategory(resourceCategory);
                return new ResponseResult(true, 200, "响应成功", null);
            }else{
                resourceCategoryService.updateResourceCategory(resourceCategory);
                return new ResponseResult(true, 200, "响应成功", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据资源分类id 查询资源分类信息
     */
    @RequestMapping("/findResourceCategoryById")
    public ResponseResult findResourceCategoryById(@RequestParam Integer id){
        try {
            ResourceCategory resourceCategoryById = resourceCategoryService.findResourceCategoryById(id);
            return new ResponseResult(true, 200, "响应成功", resourceCategoryById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除资源分类信息
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(@RequestParam Integer id){
        try {
            resourceCategoryService.deleteResourceCategory(id);
            return new ResponseResult(true, 200, "响应成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
