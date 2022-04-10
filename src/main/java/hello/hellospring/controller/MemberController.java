package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 컨테이너라는 통이 생길 때 이 controller라는 어노테이션이 있으면
// 멤버 컨트롤러 객체를 생성해서 스프링에 넣어두고 스프링이 관리함
@Controller
public class MemberController {

    private MemberService memberService;

    // 멤버 컨트롤러는 스프링 컨테이너가 생성을 하는데, 생성자에 @Autowired 어노테이션이 붙어 있으면
    // 스프링 컨테이너가 멤버 서비스와 연결을 시켜줌
    // 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어있는 멤버 서비스 객체를 가져다가 넣어줌 = 의존성 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
