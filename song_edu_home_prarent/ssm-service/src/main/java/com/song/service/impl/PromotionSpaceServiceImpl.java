package com.song.service.impl;

import com.song.dao.PromotionSpaceMapper;
import com.song.domain.PromotionSpace;
import com.song.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /**
     * 获取所有的广告位
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return promotionSpaceMapper.findAllPromotionSpace();
    }

    /**
     * 添加广告位
     *
     * @param promotionSpace
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        // 封装PromotionSpace
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        // 调用mapper
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /**
     * 根据id 查询广告位信息
     *
     * @param id
     */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    /**
     * 修改广告位
     *
     * @param promotionSpace
     */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
