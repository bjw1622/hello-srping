package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 여러 곳에서 MemberService 가져다 씀
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //    @Autowired
    // Autowired 연결한다!!
    // Member Controller 생성이 될 때 Spring Bean에 등록된 MemberService 객체를 가져와서 넣어줌
    // 이게 dependency injection
    // Component로 등록된 것들은 스프링이 객체를 생성해서 스프링 컨테이너 등록
    // 생성자 주입
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
