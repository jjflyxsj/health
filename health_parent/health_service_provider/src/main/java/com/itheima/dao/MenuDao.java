package com.itheima.dao;

import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuDao {


    List<Menu> findAll();
    /* @Select("SELECT tm.* FROM t_menu tm, t_user tu ,t_user_role tur,t_role_menu trm WHERE tm.id=trm.menu_id AND trm.role_id = tur.role_id AND tur.user_id = tu.id AND tu.username = 'admin' AND LEVEL=1;")
     */    List<Menu> findAllMeau(String username);
    /*@Select("SELECT tm.* FROM t_menu tm, t_user tu ,t_user_role tur,t_role_menu trm WHERE tm.id=trm.menu_id AND trm.role_id = tur.role_id AND tur.user_id = tu.id AND tu.username = 'admin' AND parentMenuId=#{id};")
     */    List<Menu> findAllChildren(Integer id);

    List<Menu> pageQuery(QueryPageBean queryPageBean);

    void add(Menu menu);

    void edit(Menu menu);

    void delete(Integer id);
}
