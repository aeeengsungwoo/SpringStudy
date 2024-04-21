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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "redirect:/members/" + saved.getId();
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
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        // 수정할 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        // 뷰 페이지 설정하기
        return "members/edit";
    }
    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Member memberEntity = form.toEntity();
        log. info(memberEntity.toString());
        // 2. 엔티티를 DB에 저장하기
        // 2-1 DB데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        // 데이터 수정하기, 여기선 클릭으로 수정을 하기 때문에 타겟이 무조거 있지만, 비정상적인 접근을 막기 위해선 if조건문을 꼭 사용해야함
        if (target != null) {
            memberRepository.save(memberEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + memberEntity.getId();
    }
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다! !");
        // 1. 삭제할 대상 가져오기
        Member target = memberRepository.findById(id).orElse(null);
        log. info(target. toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            memberRepository.delete(target); // 엔티티를 DB에 저장(갱신)
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/members";
    }
}
