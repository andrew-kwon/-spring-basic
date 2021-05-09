package hello.hellospring.repository;

import hello.hellospring.domein.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // Jpa는 Entity Manager로 동작을 하기 때문에 주입 받아야 함
    // build.gradle에서 Jpa 관련 라이브러리를 받았기 때문에, 스프링 부트가 Entity Manager을 자동으로 생성함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
       List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

       return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql은 객체 대상으로 쿼리를 날림 (여기서는 member entity가 대상)
        // select하는데 member entiyty(m) 자체를 select함
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
