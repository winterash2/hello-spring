package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller // 컨트롤러는 component 스캔으로 올라가게 되어있음. 어쩔수가 없음
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
    // 위의 방식은 생성자 주입 방식임
    // 그 이외에도 setter 주입, 필드 주입이 있음
    // 필드 주입은 @Autowired public MemberService; 이런식인데 안 좋은 방식임
    // setter 주입은 초기화될 때 누간가에 의해 setter가 호출이 되어있어야 하기 때문에 setter를 호출할 수 있게 열어놔야 함
    // 처음에 설정만 하고 나중에 불릴일이 없는데 setter를 열어놓는 것은 좋지 않음
    // 따라서 보통 생성자 주입으로 함

    @GetMapping("/members/new")
    public String createForm() {
        return "members/cteateMemberFrom";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

