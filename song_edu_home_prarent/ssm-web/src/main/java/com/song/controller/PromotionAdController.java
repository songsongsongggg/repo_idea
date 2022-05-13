package com.song.controller;

import com.github.pagehelper.PageInfo;
import com.song.domain.PromotionAd;
import com.song.domain.PromotionAdVO;
import com.song.domain.ResponseResult;
import com.song.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 分页查询所有广告信息
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO) {
        try {
            PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", allAdByPage);
            return responseResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片上传接口
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            //1.判断文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }

            //2.获取项目部署路径
            // /Users/yiyi/Library/apache-tomcat-8.5.31/webapps/ssm_web/
            String realPath = request.getServletContext().getRealPath("/");
            // /Users/yiyi/Library/apache-tomcat-8.5.31/webapps/
            String webappsPath = realPath.substring(0, realPath.indexOf("ssm-web"));

            //3.获取原文件名
            String fileName = file.getOriginalFilename();

            //4.新文件名
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            //5.上传文件
            String uploadPath = webappsPath + "upload/";
            File filePath = new File(uploadPath, newFileName);
            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录: " + filePath);
            }
            file.transferTo(filePath);

            //6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新建&修改广告接口
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        try {
            if (promotionAd.getId() == null) {
                promotionAdService.savePromotionAd(promotionAd);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                promotionAdService.updatePromotionAd(promotionAd);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据id回显 广告数据
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam Integer id) {
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionAd);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 广告状态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam Integer id, @RequestParam Integer status) {
        try {
            promotionAdService.updatePromotionAdStatus(id, status);
            Map<String, Integer> map = new HashMap<>();
            map.put("status",status);
            return new ResponseResult(true, 200, "响应成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
