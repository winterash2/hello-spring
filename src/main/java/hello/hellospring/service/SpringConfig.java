package hello.hellospring.service;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//
//    public DataSource getDataSource() {
//        return dataSource;
//    }
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 그냥 이렇게 인젝션 받으면 SpringDataJpaMemberRepository에서 자동으로 빈에 등록된 것이 인젝션 됨
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
    // 여기에도 memberRepository 의존관계를 세팅
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // AOP관련된 것은 특별한 것이기 떄문에 스프링빈에 직접 등록해서 사용하는 것이 좋음
    // 이걸 보고 AOP가 등록되서 사용되는구나 알 수 있음
    // Component 스캔 써도 됨 @Component
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }


//    @Bean
//    public MemberRepository memberRepository(){ // MemberRepository는 인터페이스고
////        return new MemoryMemberRepository(); // 구현체는 MemoryMemberRepository
////        return new JdbcMemberRepository(getDataSource());
////        return new JdbcTemplateMemberRepository(getDataSource());
//        return new JpaMemberRepository(em);
//    }


    // 보통은 자동으로 의존관계를 설정해주는 Service, Repository, Autowired 등을 사용하지만
    // 상황에 따라 다른걸로 바꾸고 싶을 수 있음.
    // 기존의 코드를 손대지 않고 바꾸기 위해 설정을 위해 스프링 빈으로 등록하는게 좋음

    // 예를 들면
    // 위의꺼를
//    @Bean
//    public MemberRepository memberRepository(){ // MemberRepository는 인터페이스고
//        return new DbMemoryMemberRepository(); // 구현체는 MemoryMemberRepository
//    }
    // 이런 식으로 MemoryMemberRepository -> DbMemoryMemberRepository 이런식으로
}
