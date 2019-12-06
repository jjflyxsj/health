package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> findAll();
    List<Menu> findAllMeau(String username);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void add(Menu menu);

    void edit(Menu menu);

    void delete(Integer id);

}
