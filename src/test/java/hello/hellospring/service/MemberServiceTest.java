package hello.hellospring.service;

import hello.hellospring.domein.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 의존성 주입
    }

    @AfterEach
    public void afterEach() {
        memberRepository.cleatStore();
    }

    @Test
    void 회원가입() {
        // given (주어짐)
        Member member = new Member();
        member.setName("hello");

        // when (실행했을 때)
        Long saveId = memberService.join(member);

        // then (이 결과가 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        memberService.join(member1);
        // 로직을 실행하면 예외가 터져야 함
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}
