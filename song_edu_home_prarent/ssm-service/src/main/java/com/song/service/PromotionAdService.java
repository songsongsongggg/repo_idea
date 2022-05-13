package com.song.service;

import com.github.pagehelper.PageInfo;
import com.song.domain.PromotionAd;
import com.song.domain.PromotionAdVO;

public interface PromotionAdService {

    /**
     * 分页获取所有的广告列表
     */
    public PageInfo findAllAdByPage(PromotionAdVO promotionAdVO);

    /**
     * 添加广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id 查询广告信息
     */
    public PromotionAd findPromotionAdById(Integer id);

    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告上下线状态
     */
    public void updatePromotionAdStatus(Integer id,Integer status);
}
