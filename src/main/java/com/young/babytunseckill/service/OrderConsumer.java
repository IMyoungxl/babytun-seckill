package com.young.babytunseckill.service;

import com.rabbitmq.client.Channel;
import com.young.babytunseckill.dao.OrderDAO;
import com.young.babytunseckill.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

@Component
public class OrderConsumer {

    @Resource
    private OrderDAO orderDAO;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue-order"),
                    exchange = @Exchange(value = "exchange-order",type = "fanout")
            )
    )
    @RabbitHandler
    public void handleMessage(@Payload Map data, Channel channel, @Headers Map<String,Object> headers){
        System.out.println("=======获取到订单数据" + data+"=======");

        try {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //跟支付宝对接，对接物流系统，日志登记等等等等

            Order order = new Order();
            order.setOrderNo(data.get("orderNo").toString());
            order.setOrderStatus(0);
            order.setUserId(data.get("userId").toString());
            order.setRecvName("xxx");
            order.setRecvAddress("fffff");
            order.setRecvMobile("1234567879");
            order.setPostage(0f);
            order.setAmount(9.8f);

            orderDAO.insert(order);
            Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(tag,false);
            System.out.println(data.get("orderNo") + "订单已创建");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
