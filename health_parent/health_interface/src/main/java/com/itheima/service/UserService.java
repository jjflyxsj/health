package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void add(User user, Integer[] roleIds);

    PageResult selectByCondition(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void editById(User user, Integer[] roleIds);

    List<Integer> findRoleIds(Integer id);
}
