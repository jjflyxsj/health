package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderCheckService;
import com.itheima.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderCheckController {
    @Autowired
    JedisPool jedisPool;
    @Reference
    OrderCheckService orderCheckService;
    @RequestMapping("/checkOrder")
    public Result checkOrder(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        Result result=null;
        if (redisCode!=null&&validateCode!=null&&validateCode.equals(redisCode)){
            try {
                map.put("ordertype", Order.ORDERTYPE_WEIXIN);
                 result = orderCheckService.checkOrder(map);
                return result;
               // SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,validateCode);
            } catch (Exception e) {
              return   result;
            }
        }else {
           return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }

    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map= orderCheckService.findById(id);
            return new Result(true,"",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"");
        }

    }
}
