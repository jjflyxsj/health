package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class MemberController {
    @Autowired
    JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/check")
    public Result check(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        String redisCode = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        String validateCode = (String) map.get("validateCode");
        if (redisCode != null && validateCode != null && validateCode.equals(redisCode)) {
            try {
                Member member1 = memberService.check(telephone);
                if (member1 == null) {
                    //新建会员
                    Member member = new Member();
                    member.setPhoneNumber(telephone);
                    member.setRegTime(new Date());
                    memberService.add(member);
                    String json = JSON.toJSON(member).toString();
                    jedisPool.getResource().setex(telephone, 60 * 30, json);
                } else {
                    // Cookie   cookie = new Cookie("login_member_telephone", telephone);

                    String json = JSON.toJSON(member1).toString();
                    jedisPool.getResource().setex(telephone, 60 * 30, json);
                }
                return new Result(true, MessageConstant.LOGIN_SUCCESS);

            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "登录失败");
            }
        } else {
            return new Result(false, "登录失败");
        }


    }

}
