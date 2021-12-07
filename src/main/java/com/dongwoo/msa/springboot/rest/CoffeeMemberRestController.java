package com.dongwoo.msa.springboot.rest;

import com.dongwoo.msa.springboot.repository.ICoffeeMemberMapper;
import com.dongwoo.msa.springboot.repository.dvo.MemberDVO;
import com.dongwoo.msa.springboot.rest.rvo.MemberRVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope // 관련 설명 https://elfinlas.github.io/2019/06/25/spring-config-refresh/
@RestController
public class CoffeeMemberRestController {

    @Autowired
    ICoffeeMemberMapper iCoffeeMemberMapper;


    @HystrixCommand
    @RequestMapping(value="/coffeeMember/v1.0/{memberName}" , method = RequestMethod.GET)
    public boolean coffeeMember(@PathVariable("memberName") String memberName) {
        MemberDVO memberDVO = new MemberDVO();
        memberDVO.setMemberName(memberName);
        if(iCoffeeMemberMapper.existsByMemberName(memberDVO).getMemberName().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @HystrixCommand
    @RequestMapping(value = "/coffeeMember/v1.1", method = RequestMethod.GET)
    public boolean coffeeMember (@RequestBody MemberRVO memberRVO) {

        MemberDVO memberDVO = new MemberDVO();
        memberDVO.setMemberName(memberRVO.getMemberName());

        if(iCoffeeMemberMapper.existsByMemberName(memberDVO).getMemberName().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @HystrixCommand(fallbackMethod = "fallbackFunction")
    @RequestMapping(value = "/fallbackTest", method = RequestMethod.GET)
    public String fallbackTest() throws Throwable {
        throw new Throwable("fallbackTest");
    }

    public String fallbackFunction(){
        return "fallBackFunction";
    }

    @RequestMapping(value = "createMemberTable", method = RequestMethod.PUT)
    public void createMemberTable() {
        iCoffeeMemberMapper.createMemberTable();
    }
}
