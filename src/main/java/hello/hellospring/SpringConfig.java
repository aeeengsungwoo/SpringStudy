package hello.hellospring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //자바 코드로 직접 스프링빈을 등록할 때
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
