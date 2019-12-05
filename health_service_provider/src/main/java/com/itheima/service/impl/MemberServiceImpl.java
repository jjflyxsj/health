package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = MemberService.class,retries = -1)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;
    @Override
    public Member check(String telephone) {

        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    @Override
    public Integer getMemberReport(String format) {
        return memberDao.findMemberCountBeforeDate(format);
    }
}
