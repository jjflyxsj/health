package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class,retries = -1)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        if (orderSettings != null && orderSettings.size() > 0) {
            for (OrderSetting orderSetting : orderSettings) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map>  findMessage(String date) {
        List<Map> list1=new ArrayList<>();
        String start =date+"-1";
        String end =date+"-31";
        Map<String, Object> m=new HashMap<>();
        m.put("start",start);
        m.put("end",end);
        List<OrderSetting> list=orderSettingDao.findAll(m);
        for (OrderSetting orderSetting : list) {
            Map<String, Object> map=new HashMap<>();
            Date orderDate = orderSetting.getOrderDate();
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            map.put("date",orderDate.getDate());
            map.put("number",number);
            map.put("reservations",reservations);
            list1.add(map);
        }
        return list1;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        if (orderSetting != null) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }

}

