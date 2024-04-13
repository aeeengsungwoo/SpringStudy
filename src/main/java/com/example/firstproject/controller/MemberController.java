package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String SignUpPage(){
        return "members/new";
    }
    @PostMapping ("/join")
    public String join(MemberForm form){
        log.info(form.toString());

        Member member = form.toEntity(); //DTO를 entity로 변환
        //System.out.println(member.toString());
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "";
    }
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id );
        // 1. id를 조회해 데이터 가져오기
        Member memberentity = memberRepository.findById(id).orElse(null);
        // id가 없으면 null을 반환한다.
        // 2. 모델에 데이터 등록하기
        model.addAttribute("member",memberentity);
        // 3. 뷰 페이지 반환하기
        return "members/show";
    }
    @GetMapping("/members")
    public String index(Model model){
        // 1 . 모든 데이터 가져오기
        List<Member> memberEntityList = memberRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("memberList",memberEntityList);
        // 3. 뷰 페이지 설정하기
        return "members/index";
    }
}
