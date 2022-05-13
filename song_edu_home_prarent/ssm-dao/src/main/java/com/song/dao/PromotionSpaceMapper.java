package com.song.dao;

import com.song.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /**
     * 获取所有的广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id 查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /**
     * 修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
