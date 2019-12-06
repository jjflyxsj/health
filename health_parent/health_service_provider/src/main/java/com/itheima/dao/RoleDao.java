package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleDao {

    Set<Role> findByUserId(Integer userId);

    List<Role> findAll();

    Role findById(int id);

    void delete(int id);

    void add(Role role);

    void setRoleAndMenu(Map<String, Integer> map);

    void setRoleAndPermission(Map<String, Integer> map);

    void edit(Role role);


    void deleteRoleAndMenu(Integer map);

    void deleteRoleAndPermission(Integer map);

    List<Integer> findMenuIdsByRoleId(Integer roleId);

    List<Integer> findPermissionIdsByRoleId(Integer roleId);

    Page<Role> selectByCondition(String queryString);
}
