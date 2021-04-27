package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions; // assertEquals
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions; // assertThat

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // 위에꺼를 static import

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // 테스트 끝날때마다 레포지토리를 깔끔하게 지워주는걸 만들어야 함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // Optional에서 값을 꺼내기 위해
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result, member);
//        Assertions.assertEquals(result, null);
        assertThat(member).isEqualTo(result);
        // 실무에서는 build 투링랑 엮어서 테스트가 실패하면 다음 단계로 못 넘어가게 막아버림
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        assertThat(result1).isEqualTo(member1);
//        Member result2 = repository.findByName("spring2").get();
//        assertThat(result2).isEqualTo(member1); // 이렇게하면 틀리게 나옴 result2와 member1 은 다르니까
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
