package com.itheima.dao;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderCheckDao {

    Order findByCondition(Order order);

    void add(Order order);

    Map findById(Integer id);



    List<Map> findHotSetmeal();

    Integer findVisitsCountAfterDate(String thisWeekMonday);
}
