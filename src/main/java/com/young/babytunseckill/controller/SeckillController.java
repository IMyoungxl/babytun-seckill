package com.young.babytunseckill.controller;

import com.young.babytunseckill.entity.*;
import com.young.babytunseckill.exception.SeckillException;
import com.young.babytunseckill.service.GoodsService;
import com.young.babytunseckill.service.OrderService;
import com.young.babytunseckill.service.PromotionSeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SeckillController {

    Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Value("${server.port}")
    private String port;


    @Resource
    PromotionSeckillService promotionSeckillService;

    @Resource
    OrderService orderService;

    @Resource
    GoodsService goodsService;

    @GetMapping("/goods-{goodsId}.html")
    public ModelAndView goods(@PathVariable("goodsId") Integer goodsId){
        logger.info(port);

        ModelAndView mav = new ModelAndView("/goods");
        System.out.println("ding");
        Goods goods = goodsService.findGoods(goodsId);
        List<GoodsCover> goodsCovers = goodsService.findGoodsCover(goodsId);
        List<GoodsDetail> goodsDetails = goodsService.findGoodsDetail(goodsId);
        List<Param> params = goodsService.findParam(goodsId);

        mav.addObject("goods",goods);
        mav.addObject("goodsCovers",goodsCovers);
        mav.addObject("goodsDetails",goodsDetails);
        mav.addObject("params",params);
        return mav;
    }

    @RequestMapping("/seckill")
    @ResponseBody
    public Map processSeckill(Integer psId, String userId){
        Map result = new HashMap();
        try {
            promotionSeckillService.processSecKill(psId, userId,1);
            String orderNo = promotionSeckillService.sendOrderToQueue(userId);
            Map data = new HashMap();
            data.put("orderNo",orderNo);
            result.put("code",0);
            result.put("message","success");
            result.put("data",data);
        } catch (SeckillException e) {
            result.put("code",500);
            result.put("message",e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/checkorder")
    public ModelAndView checkOrder(String orderNo){
        Order order = orderService.findByOrderNo(orderNo);
        ModelAndView mav = new ModelAndView();
        if(order != null){
            mav.addObject("order",order);
            mav.setViewName("/order");

        }else {
            mav.addObject("orderNo",orderNo);
            mav.setViewName("/waiting");
        }
        return mav;
    }

    @GetMapping("/seckillPage")
    public ModelAndView seckillPage(Integer psId){
        logger.info(port);
        ModelAndView mav = new ModelAndView("/seckill");
        PromotionSeckill promotionSeckill = promotionSeckillService.findById(psId);
        Integer goodsId = promotionSeckill.getGoodsId();
        Goods goods = goodsService.findGoods(goodsId);
        List<GoodsCover> goodsCovers = goodsService.findGoodsCover(goodsId);
        List<GoodsDetail> goodsDetails = goodsService.findGoodsDetail(goodsId);
        List<Param> params = goodsService.findParam(goodsId);

        mav.addObject("promotionSeckill",promotionSeckill);
        mav.addObject("goods",goods);
        mav.addObject("goodsCovers",goodsCovers);
        mav.addObject("goodsDetails",goodsDetails);
        mav.addObject("params",params);
        return mav;

    }

    @GetMapping("/login")
    @ResponseBody
    public String login(String user, WebRequest request){
        request.setAttribute("user",user,WebRequest.SCOPE_SESSION);
        return "port:" + port + ",login success";
    }

    @GetMapping("/check")
    @ResponseBody
    public String checkUser(WebRequest request) {
        String user = (String) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if(user!=null){
            return "port:" + port + ",user =" + user;
        }else {
            return "port:" + port + ", redirect to login!";
        }
    }

}
