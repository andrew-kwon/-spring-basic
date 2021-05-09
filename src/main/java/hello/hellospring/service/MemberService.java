package hello.hellospring.service;

import hello.hellospring.domein.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 서비스는 회원 리포지토리와 도메인을 활용하여 실제 비즈니스 로직을 작성하는 곳임
// @Service 어노테이션을 붙여야 스프링 컨테이너에 등록을 함
@Service
@Transactional
public class MemberService {

    // 회원서비스를 만드려면 memberRepository가 필요함
    private final MemberRepository memberRepository;

    // 멤버 서비스를 스프링이 생성할 때, 스프링 컨테이너에 등록하면서 이 생성자를 호출함
    // Autowired가 붙어있으면 스프링 컨테이너에 있는 멤버 리포지토리를 서비스에 주입함
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원(같은 이름) 검증
        memberRepository.save(member); // 중복 검증을 통과하면 저장함
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // findByName으로 매개변수로 넘어온 값이 리포지토리에 있는지 확인
        // ifPresent (만약 값이 있으면, member가 들어옴) --> 이미 존재한다는 메세지 출력
        // optional로 감쌌기 때문에 .ifPresent가 가능해진 것임
        memberRepository.findByName(member.getName())
             .ifPresent(m -> {
             try {
                 throw new IllegalStateException("이미 존재하는 회원입니다");
             } catch (IllegalStateException e) {
                 e.printStackTrace();
             }
         });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
