package com.song.dao;

import com.song.domain.PromotionAd;
import com.song.domain.PromotionSpace;

import java.util.List;

public interface PromotionAdMapper {
    /**
     * 分页获取所有的广告列表
     */
    public List<PromotionAd> findAllAdByPage();

    /**
     * 添加广告位
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id 查询广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告上下线状态
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
