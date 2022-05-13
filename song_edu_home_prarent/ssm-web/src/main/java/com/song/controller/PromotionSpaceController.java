package com.song.controller;

import com.song.domain.PromotionSpace;
import com.song.domain.ResponseResult;
import com.song.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 查询所有广告位列表
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        try {
            List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
            return new ResponseResult(true, 200, "响应成功", allPromotionSpace);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加&修改广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        try {
            if (promotionSpace.getId() == null) {
                //添加
                promotionSpaceService.savePromotionSpace(promotionSpace);
                ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", "");
                return responseResult;
            } else {
                //修改
                promotionSpaceService.updatePromotionSpace(promotionSpace);
                ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", "");
                return responseResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 回显广告位名称
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id) {
        try {
            PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);
            return new ResponseResult(true, 200, "响应成功", promotionSpaceById);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
