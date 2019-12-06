package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User findByUsername(String username);

    Page<User> selectByCondition(String queryString);

    void add(User user);

    void deleteById(Integer id);

    Long selectByUserId(Integer id);

    void editById(User user);

    List<Integer> findRoleIds(Integer id);

    void setUserAndRole(Map<String, Object> map);

    void deleteAssocication(Integer userId);
}
