package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderCheckDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.dao.SetmealDao;
import com.itheima.service.ReportService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class,timeout = 60000,retries = -1)
@Transactional
public class ReportServiceImpl implements ReportService{
    @Autowired
    MemberDao memberDao;
    @Autowired
    OrderCheckDao orderDao;
    @Override
    public Map<String, Object> findBusinessReportData() throws Exception {

        String today = DateUtils.parseDate2String(DateUtils.getToday());
        //获得本周一日期
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        //获得本月第一天日期
        String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());

        //本日新增会员数
        Integer todayNewMember = memberDao.findMemberCountByDate(today);
        //总会员数
        Integer totalMember = memberDao.findMemberTotalCount();
        //本周新增会员数
        Integer thisWeekNewMember = memberDao.findMemberCountAfterDate(thisWeekMonday);
        //本月新增会员数
        Integer thisMonthNewMember = memberDao.findMemberCountAfterDate(firstDay4ThisMonth);

        //今日预约数
        Integer todayOrderNumber = memberDao.findMemberCountByDate(today);
        //本周预约数
        Integer thisWeekOrderNumber = memberDao.findMemberCountAfterDate(thisWeekMonday);
        //本月预约数
        Integer thisMonthOrderNumber = memberDao.findMemberCountAfterDate(firstDay4ThisMonth);
        //今日到诊数
        Integer todayVisitsNumber = memberDao.findMemberCountByDate(today);
        //本周到诊数
        Integer thisWeekVisitsNumber = orderDao.findVisitsCountAfterDate(thisWeekMonday);
        //本月到诊数
        Integer thisMonthVisitsNumber = orderDao.findVisitsCountAfterDate(firstDay4ThisMonth);

        //热门套餐查询
        List<Map> hotSetmeal = orderDao.findHotSetmeal();

        Map<String,Object> result = new HashMap<>();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember);
        result.put("totalMember",totalMember);
        result.put("thisWeekNewMember",thisWeekNewMember);
        result.put("thisMonthNewMember",thisMonthNewMember);
        result.put("todayOrderNumber",todayOrderNumber);
        result.put("thisWeekOrderNumber",thisWeekOrderNumber);
        result.put("thisMonthOrderNumber",thisMonthOrderNumber);
        result.put("todayVisitsNumber",todayVisitsNumber);
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        result.put("hotSetmeal",hotSetmeal);
        return result;
    }
}
