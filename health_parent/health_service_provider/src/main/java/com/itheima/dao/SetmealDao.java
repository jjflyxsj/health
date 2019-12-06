package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    List<Map> findSetmealReport();
}
