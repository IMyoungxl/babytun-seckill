package com.young.babytunseckill.service;

import com.young.babytunseckill.dao.GoodsDAO;
import com.young.babytunseckill.entity.Goods;
import com.young.babytunseckill.entity.GoodsCover;
import com.young.babytunseckill.entity.GoodsDetail;
import com.young.babytunseckill.entity.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    GoodsDAO goodsDAO;

    public Goods findGoods(Integer goodsId){
        return goodsDAO.findGoodsById(goodsId);
    }

    public List<GoodsCover> findGoodsCover(Integer goodsId){
        return goodsDAO.findGoodsCover(goodsId);
    }

    public List<GoodsDetail> findGoodsDetail(Integer goodsId){
        return goodsDAO.findGoodsDetail(goodsId);
    }

    public List<Param> findParam(Integer goodsId){
        return goodsDAO.findGoodsParam(goodsId);
    }

}
