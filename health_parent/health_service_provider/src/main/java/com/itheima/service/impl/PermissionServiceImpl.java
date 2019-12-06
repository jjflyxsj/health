package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class,retries = -1)
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionMapper;
    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        /*查询分页信息*/
        Page<Permission> pageInfo= PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<Permission> result = pageInfo.getResult();
        List<Permission> permissionList=permissionMapper.findAllByQueryString(queryPageBean);
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.add(permission);
    }

    @Override
    public void edit(Permission permission) {
        permissionMapper.edit(permission);
    }

    @Override
    public void delete(Integer id) {
        permissionMapper.delete(id);
    }
}
