package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // 테스트가 끝나면 레포지터리를 비워줘야 하는데 지금 MemberService 밖에 없어서 비울수가 없음
    // 현재는 memoryMemberRepository가 static으로 되어있어서 괜찮은데 static이 빠지면 다르게 됨

    // 이렇게 바꾸는걸 DI라고 함
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() { // 테스트는 과감하게 한글로 바꿔도 됨. production으로 나가는게 아니니까 상관없음
        //given -> 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when -> 무언가를 실행했을 때
        Long saveId = memberService.join(member);

        //then -> 어떤 결과가 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    } // 이건 너무 단순한 테스트임

    @Test
    public void joinDuplicateMember(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 1벙 방식. try catch
//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            //정상적으로 수행된 경우임
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // 2번 방식
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThrows(NullPointerException.class, () -> memberService.join(member2)); // 이렇게하면 실패함
        // 그럼 메시지는 어떻게 검증할건가요?
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}