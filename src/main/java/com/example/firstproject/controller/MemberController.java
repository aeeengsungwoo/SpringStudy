package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/members/new")
    public String NewMemberForm(){
        return "members/new";
    }
    @PostMapping ("members/create")
    public String createMember(MemberForm form){
        Member member = form.toEntity(); //DTO를 entity로 변환
        //System.out.println(member.toString());
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "";
    }
}
