package com.itheima.dao;

import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionDao {
    Set<Permission> findByRoleId(Integer roleId);

    List<Permission> findAll();
    Set<Permission> findPermissionsByRoleId(Integer role_id);

    Integer total();

    List<Permission> findAllByQueryString(QueryPageBean queryPageBean);

    void add(Permission permission);

    void edit(Permission permission);

    void delete(Integer id);
}
