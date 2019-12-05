package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

public interface OrderCheckService {
    Result checkOrder(Map map);

    Map findById(Integer id);
}
