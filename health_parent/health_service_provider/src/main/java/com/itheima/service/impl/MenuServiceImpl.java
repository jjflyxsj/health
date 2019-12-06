package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = MenuService.class,retries = -1)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuMapper;

    @Override
    public List<Menu> findAll() {
        return menuMapper.findAll();
    }

    @Override
    public List<Menu> findAllMeau(String username) {
        /*查询出来父级菜单*/
        List<Menu> menuList=menuMapper.findAllMeau(username);
        for (Menu menu : menuList) {
            /*子集目录的parentMenuId*/
            Integer id = menu.getId();
            List<Menu> children = getChildren(id);
            menu.setChildren(children);
        }
        return menuList;
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Page<Menu> pageInfo = PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        List<Menu> menuList=menuMapper.pageQuery(queryPageBean);
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    }

    @Override
    public void add(Menu menu) {
        menuMapper.add(menu);
    }

    @Override
    public void edit(Menu menu) {
        menuMapper.edit(menu);
    }

    @Override
    public void delete(Integer id) {
        menuMapper.delete(id);
    }

    /*递归获取子集目录*/
    public List<Menu> getChildren(int id){
        List<Menu> allChildren = menuMapper.findAllChildren(id);
        for (Menu childsChild : allChildren) {
            /*通过子集目录再看数据库是否还有子集目录的子集目录*/
            List<Menu> menuList = menuMapper.findAllChildren(childsChild.getId());
            if (menuList!=null) {
                for (Menu menu : menuList) {
                    getChildren(menu.getId());
                }
            }
            childsChild.setChildren(menuList);
        }
        return allChildren;
    }
}
