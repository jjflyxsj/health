package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValiDateCodeController {
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //Integer code = ValidateCodeUtils.generateValidateCode(4);
        Integer code =5456;
      //  try {
            //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
            jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,code.toString());
     /*   } catch (ClientException e) {
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //Integer code = ValidateCodeUtils.generateValidateCode(4);
        Integer code =5456;
        //  try {
        //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,code.toString());
     /*   } catch (ClientException e) {
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
