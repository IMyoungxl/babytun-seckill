package com.young.babytunseckill;


import com.young.babytunseckill.dao.GoodsDAO;
import com.young.babytunseckill.dao.OrderDAO;
import com.young.babytunseckill.dao.PromotionSeckillDAO;
import com.young.babytunseckill.entity.*;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeckillTest {

    @Resource
    PromotionSeckillDAO promotionSeckillDAO;

    @Resource
    OrderDAO orderDAO;

    @Resource
    GoodsDAO goodsDAO;

    @Test
    public void test() {
        List<PromotionSeckill> list = promotionSeckillDAO.findUnstartSeckill();
        System.out.println(list);
    }

    @Test
    public void test1(){
        Order order = new Order();
        order.setOrderNo("1234");
        order.setOrderStatus(0);
        order.setUserId("awdf");
        order.setRecvName("young");
        order.setRecvAddress("china");
        order.setRecvMobile("123456");
        order.setPostage(12.3f);
        order.setAmount(13.4f);
        int r = orderDAO.insert(order);
        System.out.println(r);
    }

    @Test
    public void test2(){
        Order order = orderDAO.selectById(1);
        System.out.println(order);
    }

    @Test
    public void test3(){
        Goods goods = goodsDAO.findGoodsById(800);
        List<GoodsCover> goodsCover = goodsDAO.findGoodsCover(800);
        List<GoodsDetail> goodsDetail = goodsDAO.findGoodsDetail(800);
        List<Param> param = goodsDAO.findGoodsParam(800);
        System.out.println("");
    }

}
