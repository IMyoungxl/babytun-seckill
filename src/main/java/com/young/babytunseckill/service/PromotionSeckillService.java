package com.young.babytunseckill.service;

import com.young.babytunseckill.dao.PromotionSeckillDAO;
import com.young.babytunseckill.entity.PromotionSeckill;
import com.young.babytunseckill.exception.SeckillException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PromotionSeckillService {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    PromotionSeckillDAO promotionSeckillDAO;

    @Resource
    RabbitTemplate rabbitTemplate;

    public void processSecKill(Integer psId, String userId, Integer num) throws SeckillException {
        PromotionSeckill ps = promotionSeckillDAO.findById(psId);
        if(ps==null){
            throw new SeckillException("该商品秒杀活动不存在");
        }
        if(ps.getStatus()==0){
            throw new SeckillException("秒杀活动尚未开始");
        }
        else if(ps.getStatus()==2){
            throw new SeckillException("秒杀活动已结束");
        }
        //判断是否已经参加过秒杀活动
        boolean isSeckilled = redisTemplate.opsForSet().isMember("seckill:users:"+ps.getPsId(),userId);
        if(!isSeckilled){
            Integer goodsId = (Integer) redisTemplate.opsForList().leftPop("seckill:count:"+ ps.getPsId());
            if(goodsId != null){
                System.out.println("恭喜您，抢到商品了，快去下单把");
                redisTemplate.opsForSet().add("seckill:users:" + ps.getPsId(),userId);


            }else {
                throw new SeckillException("抱歉，商品被抢光了，下次再来把");
            }
        }else {
            throw new SeckillException("抱歉，您已经参加过活动，请勿重复参加！");
        }

    }

    public String sendOrderToQueue(String userId){
        System.out.println("准备向队列发送信息");
        //订单基本信息
        Map data = new HashMap();
        data.put("userId",userId);
        String orderNo = UUID.randomUUID().toString();
        data.put("orderNo",orderNo);
        //附加额外的订单信息

        rabbitTemplate.convertAndSend("exchange-order", null,data);

        return orderNo;
    }

    public PromotionSeckill findById(Integer psId){
        return promotionSeckillDAO.findById(psId);
    }

}
