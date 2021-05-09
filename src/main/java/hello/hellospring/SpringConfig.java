package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    //// JDBC 이용할 경우
//    private DataSource datasource;
//
//    @Autowired
//    public SpringConfig(DataSource datasource) {
//        this.datasource = datasource;
//    }


    //// Jpa 이용할 경우
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    //// 스프링Jpa 이용할 경우
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository);
//    }


    //// Jpa 이용할 경우
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }


    //// Jpa 이용할 경우
    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(datasource);
        return new JpaMemberRepository(em);
    }

}
