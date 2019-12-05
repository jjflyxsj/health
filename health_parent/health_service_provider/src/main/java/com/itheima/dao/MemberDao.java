package com.itheima.dao;

import com.itheima.pojo.Member;

import java.util.List;

public interface MemberDao {
    void add(Member member);

    Member findByTelephone(String telephone);

    Integer findMemberCountBeforeDate(String time);

    Integer findMemberCountByDate(String today);

    Integer findMemberTotalCount();

    Integer findMemberCountAfterDate(String thisWeekMonday);


}
