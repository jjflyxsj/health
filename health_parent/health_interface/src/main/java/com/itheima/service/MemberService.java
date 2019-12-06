package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberService {
    Member check(String map);

    void add(Member member);

    Integer getMemberReport(String time);
}
