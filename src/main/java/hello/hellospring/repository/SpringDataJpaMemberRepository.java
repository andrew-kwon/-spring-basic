package hello.hellospring.repository;

import hello.hellospring.domein.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// japRepository를 extends하고 있기 때문에 구현체를 자동으로 만들어 스프링 빈에 등록함
public interface SpringDataJpaMemberRepository
        extends JpaRepository<Member, Long>,
        MemberRepository {

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
