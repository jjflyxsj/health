package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service(interfaceClass = UserService.class,timeout = 60000,retries = -1)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user==null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles=roleDao.findByUserId(userId);
        for (Role role : roles) {
            Integer roleId = role.getId();
            Set<Permission> permissions =  permissionDao.findByRoleId(roleId);
            for (Permission permission : permissions) {
                role.setPermissions(permissions);
            }

        }
        user.setRoles(roles);
        return user;
    }

    @Override
    public void add(User user, Integer[] roleIds) {

        String password = user.getPassword();
        String s = encodePassword(password);
        user.setPassword(s);
        userDao.add(user);
        Integer id = user.getId();
        if (roleIds != null && roleIds.length > 0) {
            Map<String,Object> map=new HashMap<>();

                map.put("id",id);
                map.put("roleIds",roleIds);
                userDao.setUserAndRole(map);

        }
    }

    @Override
    public PageResult selectByCondition(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<User> page = userDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<User> rows = page.getResult();
        return  new PageResult(total,rows);
    }

    @Override
    public void deleteById(Integer id) {
        Long num = userDao.selectByUserId(id);
        if (num>0){
            new RuntimeException();
        }else {
            userDao.deleteById(id);
        }
    }

    @Override
    public void editById(User user, Integer[] roleIds) {
        String password = user.getPassword();
        String password1 = encodePassword(password);
        user.setPassword(password1);
        userDao.editById(user);
        Integer userId = user.getId();
        userDao.deleteAssocication(userId);
        if (roleIds != null && roleIds.length > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", userId);
            map.put("roleIds", roleIds);
            userDao.setUserAndRole(map);

        }
    }

    @Override
    public List<Integer> findRoleIds(Integer id) {
        return userDao.findRoleIds(id);
    }
}
