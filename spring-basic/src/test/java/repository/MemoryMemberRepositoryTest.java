package repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 젼체테스트를 한 번에 돌릴 때, 테스트 순서는 보장이 되지 않음 (사용자가 그 순서까지 정해서 돌리는 것이 아님)
    // 그래서 메서드별로 따로 동작하게 만들어야 함 (순서에 의존적으로 설계하면 안 됨)
    // 그래서 테스트가 끝날 때마다 repository를 비우는 코드를 작성해야 함
    // @AftterEach는 메서드가 실행이 끝날 때마다 어떤 동작을 하게 하는 콜백 메서드
    // 테스트가 끝날 때마다 repository를 비우기 때문에 순서에 의존관계 없이 테스트 할 수 있음
    @AfterEach
    public void afterEach() {
        repository.cleatStore();
    }

    @Test
    public void save() {
        // 객체 생성
        Member member = new Member();
        member.setName("spring");

        // repository에 저장
        repository.save(member);

        ////  검증하는 부분
        // optional에서 값을 꺼낼 때는 .get() 으로 꺼낼 수 있음
        Member result = repository.findById(member.getId()).get();

        // result가 member와 같은지 테스트
        // Assertions.assertThat
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
