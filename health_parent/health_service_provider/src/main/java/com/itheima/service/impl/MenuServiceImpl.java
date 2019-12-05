package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenuDao;
import com.itheima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = MenuService.class,retries = -1)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;
    @Override
    public List<Map> findMenu() {
        return menuDao.findMenu();
    }
}
