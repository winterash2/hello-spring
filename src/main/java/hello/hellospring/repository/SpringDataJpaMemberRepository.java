package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 다중 상속
    // JpaRepository를 받고 있으면 얘가 구현체를 자동으로 만들어서 스프링 빈에 자동으로 등록해줌
    // 우리는 그냥 그걸 가져다쓰면 됨
    // 가져다 쓰는 방법은 SpringConfig에

    // 이름을 findByName 같이 규칙에 맞춰서 적어주면 JPA가 자동으로 아래의 쿼리로 만들어줌
    // JPOL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    // 응용으로
    Optional<Member> findByNameAndId(String name, Long Id);
    // 이런것도 select m from Member m where m.name = ? and m.id = ?
    // 이런식으로 변형해줌
    // 인터페이스 이름만으로도 개발이 끝난것임

    // 실무에서는 간단한 것은 스프링 데이터 JPA로 간단하게 하고
    // 복잡한 동적 쿼리는 Querydsl 이라는 라이브러리를 사용함
    // 이런 조합으로도 해결하기 어려운 경우는 JPA가 제공하는 쿼리를 사용하거나, 앞서 학습한 스프링 JbbcTemplate을 섞어서 이용하면 됨
}
