package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 entity manager라는걸로 모든것들이 동작함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    // 아까 build.gradle에서 ~-data-jpa 라이브러리를 받았는데 그러면 스프링 부트가 자동으로 entity manager를 생성을 해줌
    // 현재 데이터베이스랑 연결을 해서 만들어줌
    // 우리는 이걸 인젝션만 해주면 됨

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
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
}
