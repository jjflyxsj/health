package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderCheckDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderCheckService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service(interfaceClass = OrderCheckService.class,retries = -1)
@Transactional
public class OrderCheckServiceImpl implements OrderCheckService {
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private OrderCheckDao orderCheckDao;
    @Autowired
    private MemberDao memberDao;
    @Override
    public Result checkOrder(Map map) {
        String orderDate = (String) map.get("orderDate");
        try {
            OrderSetting orderSetting = orderSettingDao.findByOrderDate(DateUtils.parseString2Date(orderDate));
            if (orderSetting==null){
                return  new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            }
            if (orderSetting.getNumber()<=orderSetting.getReservations()){
                return new Result(false, MessageConstant.ORDER_FULL);
            }
            Order order=new Order();
            String setmealId = (String) map.get("setmealId");
            order.setSetmealId(Integer.parseInt(setmealId));
            order.setOrderType((String)map.get("ordertype"));
            order.setOrderDate(DateUtils.parseString2Date(orderDate));
             if (orderCheckDao.findByCondition(order)!=null){
                 return  new Result(false,MessageConstant.HAS_ORDERED);
             }
             Member member=new Member();
             if (memberDao.findByTelephone((String) map.get("telephone"))==null){
                 member.setName((String) map.get("name"));
                 member.setPhoneNumber((String) map.get("telephone"));
                 member.setIdCard((String) map.get("idCard"));
                 member.setSex((String) map.get("sex"));
                 member.setRegTime(new Date());
                 memberDao.add(member);
             }
            orderSetting.setReservations(orderSetting.getReservations()+1);
             orderSettingDao.editNumberByOrderDate(orderSetting);
             order.setMemberId(member.getId());
             order.setOrderStatus(Order.ORDERSTATUS_NO);
             orderCheckDao.add(order);
             return new Result(true,MessageConstant.ORDER_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }


    }

    @Override
    public Map findById(Integer id) {
        return orderCheckDao.findById(id);
    }
}
