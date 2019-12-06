package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(int id);

    void delete(int id);

    void add(Role role, Integer[] menuIds, Integer[] permissionIds);

    void edit(Role role, Integer[] menuIds, Integer[] permissionIds);

    List<Integer> findMenuIdsByRoleId(Integer roleId);

    List<Integer> findPermissionIdsByRoleId(Integer roleId);

    PageResult pageQuery(QueryPageBean queryPageBean);

}
