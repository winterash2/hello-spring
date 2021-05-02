package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


//@Service
@Transactional // Jpa는 트랜잭셔널을 넣어줘야 함. 항상 트랜잭션이 있어야 함
public class MemberService { // ctrl shift T -> 테스트 자동으로 만들어주는것

    //이렇게 되어있던걸
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 이렇게 다른데서 넣어주도록
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public long join(Member member) { // 같은 이름의 회원은 안 된다면?
        // 같은 이름이 있는 중복회원X
        // 1번 방법, 예쁘지 않음
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // 2번 방법. 이게 더 이쁘니까 이렇게 하는걸 추천함
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        // 3번 방법. 메소드를 분리하는게 좋으니까 이렇게 하는게 더 좋음.
        // ctrl alt shift T 단축키로 리팩토링 관련 메뉴들을 불러오고 Extract Method를 선택

        long start = System.currentTimeMillis();
        try{
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        // 서비스에서는 이름을 비즈니스에 가깝게 하고,
        // repository에 보면 findById 이런 식으로 기능에 가깝게 이름을 지음
        // 이렇게해야 기획자와 개발자간의 소통이 더 원활하게 됨

        // 이런 식으로
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
