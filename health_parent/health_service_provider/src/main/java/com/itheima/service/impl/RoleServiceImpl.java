package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleService.class,retries = -1)
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }

    @Override
    public void add(Role role, Integer[] menuIds, Integer[] permissionIds) {
        roleDao.add(role);
        Integer roleId = role.getId();
        if(menuIds!=null&&menuIds.length>0){
            for (Integer menuId : menuIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("roleId",roleId);
                map.put("menuId",menuId);
                roleDao.setRoleAndMenu(map);
            }
        }
        if(permissionIds!=null&&permissionIds.length>0){
            for (Integer permissionId : permissionIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("roleId",roleId);
                map.put("permissionId",permissionId);
                roleDao.setRoleAndPermission(map);
            }
        }
    }

    @Override
    public void edit(Role role, Integer[] menuIds, Integer[] permissionIds) {
        roleDao.edit(role);
        Integer roleId = role.getId();
        roleDao.deleteRoleAndMenu(roleId);
        roleDao.deleteRoleAndPermission(roleId);
        if(menuIds!=null&&menuIds.length>0){
            for (Integer menuId : menuIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("menuId",menuId);
                map.put("roleId",roleId);
                roleDao.setRoleAndMenu(map);
            }
        }
        if(permissionIds!=null&&permissionIds.length>0){
            for (Integer permissionid : permissionIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("permissionid",permissionid);
                map.put("roleId",roleId);
                roleDao.setRoleAndPermission(map);
            }
        }
    }

    @Override
    public List<Integer> findMenuIdsByRoleId(Integer roleId) {
        return roleDao.findMenuIdsByRoleId(roleId);
    }

    @Override
    public List<Integer> findPermissionIdsByRoleId(Integer roleId) {
        return roleDao.findPermissionIdsByRoleId(roleId);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);

        Page<Role> page = roleDao.selectByCondition(queryString);

        long total = page.getTotal();
        List<Role> rows = page.getResult();


        return new PageResult(total,rows);
    }
}
