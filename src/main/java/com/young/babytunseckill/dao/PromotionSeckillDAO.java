package com.young.babytunseckill.dao;

import com.young.babytunseckill.entity.PromotionSeckill;

import java.util.List;

public interface PromotionSeckillDAO {

    List<PromotionSeckill> findUnstartSeckill();

    int update(PromotionSeckill promotionSeckill);

    PromotionSeckill findById(Integer psId);

    List<PromotionSeckill> findExpireSeckill();

}
