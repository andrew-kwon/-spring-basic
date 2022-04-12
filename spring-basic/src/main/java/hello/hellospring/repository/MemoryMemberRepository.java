package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// @Repository 어노테이션이 붙어있어야 스프링이 실행될 때 가져옴
// @Repository
public class MemoryMemberRepository implements MemberRepository {

    // key는 회원의 아이디라서 Long, 값은 Member
    private static Map<Long, Member> store = new HashMap<>();

    // sequence는 키 값을 생성하는 역할
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id 세팅 : id는 시스템에 저장할 때 필요하며, 여기서 sequence를 이용해 저장함
        member.setId(++sequence);

        // store에 저장(map에 저장)
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 없을 경우를 대비하여 Optional을 이용함 (null이어도 감싸서 반환)
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다 이용, 루프로 돌리면서 파라미터로 넘어온 name과 member에 있는 name과 같은지 점검
        // findAny()는 하나라도 값을 찾는 것
        // --> 루프를 돌면서 하나라도 값을 찾으면 반환하고, 없는 경우에는 Optional에 Null이 포함되서 반환
        return store.values().stream()
                // member의 name 파라미터로 넘어온 name과 같으면 결과 값을 찾고 반환하라는 의미
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

   @Override
   // Map 형식인데 반환은 List로 진행
   public List<Member> findAll(){
        // store.values()는 member들을 의미함
        return new ArrayList<>(store.values());
   }

    public void cleatStore() {
        store.clear();
    }

}
