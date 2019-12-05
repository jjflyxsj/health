package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    @Select("select count(*) from t_ordersetting where orderDate=#{orderDate}")
    long findCountByOrderDate(Date orderDate);

    @Update("update t_ordersetting set number=#{number} where orderDate=#{orderDate}")
    void editNumberByOrderDate(OrderSetting orderSetting);

    @Insert("insert into t_ordersetting(orderDate,number) values(#{orderDate},#{number}) ")
    void add(OrderSetting orderSetting);

    @Select("select * from t_ordersetting where orderDate between #{start} and #{end} ")
    List<OrderSetting> findAll(Map<String, Object> date);

    @Select("select * from t_ordersetting where orderDate=#{date}")
    OrderSetting findByOrderDate(Date date);
}
