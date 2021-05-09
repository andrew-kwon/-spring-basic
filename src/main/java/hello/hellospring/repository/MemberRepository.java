package hello.hellospring.repository;

import hello.hellospring.domein.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // findById를 가져올 때 null인 경우에는, null 그대로 반환하는 것이 아니라 optional로 감싸서 반환함

    Member save(Member member); // 회원 저장소에 저장
    Optional<Member> findById(Long id); // id로 저장소에서 데이터를 찾아옴
    Optional<Member> findByName(String name); // name으로 저장소에서 데이터를 찾아옴
    List<Member> findAll(); // 지금까지 저장된 회원리스트 모두 반환

}
