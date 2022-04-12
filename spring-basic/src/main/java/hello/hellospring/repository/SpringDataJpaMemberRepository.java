package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// japRepository를 extends하고 있기 때문에 구현체를 자동으로 만들어 스프링 빈에 등록함
// <T>는 Member, id는 Long
// 다중상속 MemberRepository

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
