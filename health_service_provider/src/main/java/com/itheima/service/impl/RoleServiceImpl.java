package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = RoleService.class,retries = -1)
@Transactional
public class RoleServiceImpl implements RoleService {
}
