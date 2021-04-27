package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MemberController {
    // 아직 기능은 없지만 스프링이 처음에 뜰 때 스프링 컨테이너라는 스프링 통이 생기는데
    // 거기에 이 MemberController 객체를 생성을 해서 넣어두고 관리함
    // Controller라는 에너테이션을 보고 하는것임

//    private final MemberService memberService = new MemberService();
    // 여기저기서 MemberService를 가져다가 쓸건데 굳이 여러개를 만들 필요가 없음

    public final MemberService memberService;

    @Autowired
    // 멤버 컨트롤러를 스프링이 실행을 할 때 생성자를 수행을 하는데
    // 생성자에 Autowired가 있으면 스프링이 스프링 컨테이너에서 가져와서 연결을 시켜줌
    public MemberController(MemberService memberService) { // 빨간불이 뜨면서 잘 안되는데
        // Parameter 0 of constructor in hello.hellospring.controller.MemberController required a
        // bean of type 'hello.hellospring.service.MemberService' that could not be found.
        // 이런 오류가 뜨는데 그 이유는 스프링 컨테이너에 memberService가 없기 때문임
        this.memberService = memberService;
    }
}
