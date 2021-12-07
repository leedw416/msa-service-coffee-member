package com.dongwoo.msa.springboot.repository;


import com.dongwoo.msa.springboot.repository.dvo.MemberDVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICoffeeMemberMapper {
    MemberDVO existsByMemberName(MemberDVO memberDVO);
    int createMemberTable();
    int insertMemberData();
}
