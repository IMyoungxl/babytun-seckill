package com.young.babytunseckill.dao;

import com.young.babytunseckill.entity.Goods;
import com.young.babytunseckill.entity.GoodsCover;
import com.young.babytunseckill.entity.GoodsDetail;
import com.young.babytunseckill.entity.Param;

import java.util.List;


public interface GoodsDAO {

    Goods findGoodsById(Integer goodsId);

    List<GoodsCover> findGoodsCover(Integer goodsId);

    List<Param> findGoodsParam(Integer goodsId);

    List<GoodsDetail> findGoodsDetail(Integer goodsId);
}
