package com.song.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.dao.PromotionAdMapper;

import com.song.domain.PromotionAd;
import com.song.domain.PromotionAdVO;
import com.song.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    /**
     * 分页获取所有的广告列表
     *
     * @param promotionAdVO
     */
    @Override
    public PageInfo findAllAdByPage(PromotionAdVO promotionAdVO) {

        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAdByPage);
        return adPageInfo;
    }

    /**
     * 添加广告位
     *
     * @param promotionAd
     */
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /**
     * 根据id 查询广告信息
     *
     * @param id
     */
    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    /**
     * 修改广告
     *
     * @param promotionAd
     */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /**
     * 修改广告上下线状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updatePromotionAdStatus(Integer id,Integer status) {
        //补全信息
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }



}
