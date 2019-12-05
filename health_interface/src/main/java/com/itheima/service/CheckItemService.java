package com.itheima.service;


import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult selectByCondition(QueryPageBean queryPageBean);

    void deleteById(int id);

    void editById(CheckItem checkItem);

    CheckItem findOne(int id);

    List<CheckItem> findAll();



}
