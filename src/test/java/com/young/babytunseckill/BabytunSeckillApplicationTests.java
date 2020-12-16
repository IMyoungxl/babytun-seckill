package com.young.babytunseckill;

import com.young.babytunseckill.dao.PromotionSeckillDAO;
import com.young.babytunseckill.entity.PromotionSeckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class BabytunSeckillApplicationTests {

    @Resource
    PromotionSeckillDAO promotionSeckillDAO;

    @Test
    void contextLoads() {
        List<PromotionSeckill> list = promotionSeckillDAO.findUnstartSeckill();
        System.out.println(list);
    }

}
