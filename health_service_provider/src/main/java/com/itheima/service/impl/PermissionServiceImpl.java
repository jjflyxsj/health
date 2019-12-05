package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.service.PermissionService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = PermissionService.class,retries = -1)
@Transactional
public class PermissionServiceImpl implements PermissionService {
}
