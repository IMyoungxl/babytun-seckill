package com.young.babytunseckill.scheduler;

import com.young.babytunseckill.dao.PromotionSeckillDAO;
import com.young.babytunseckill.entity.PromotionSeckill;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SeckillTask {

    @Resource
    private PromotionSeckillDAO promotionSeckillDAO;

    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public void startSeckill(){
        List<PromotionSeckill> list = promotionSeckillDAO.findUnstartSeckill();
        for(PromotionSeckill ps : list){
            System.out.println(ps);
            //删掉以前重复的任务
            redisTemplate.delete("seckill:count:" + ps.getGoodsId());

            //有几个库存商品，则初始化几个list对象
            for (int i=0; i<ps.getPsCount(); i++){
                redisTemplate.opsForList().rightPush("seckill:count:"+ps.getPsId(), ps.getGoodsId());
            }

            ps.setStatus(1);
            promotionSeckillDAO.update(ps);
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void cleanSeckill(){
        List<PromotionSeckill> psList = promotionSeckillDAO.findExpireSeckill();
        for(PromotionSeckill ps : psList){
            System.out.println(ps.getPsId()+"秒杀活动已经结束");
            ps.setStatus(2);
            promotionSeckillDAO.update(ps);
            redisTemplate.delete("seckill:count:"+ps.getPsId());
        }
    }

}
