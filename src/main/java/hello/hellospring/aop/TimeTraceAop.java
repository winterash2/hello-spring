package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimeTraceAop {

    // 이 공통 관심 사항을 어디에 적용시킬거야?
    // 적용을 시켜주는것임
    // 보통 패키지 밑으로 보통 함
    @Around("execution(* hello.hellospring..*(..))") // 이 hello.hellospring 하위에는 다 적용해 라는 의미임
    // @Around("execution(* hello.hellospring.service..*(..))") // 이렇게 하면 service에 있는것만 적용
    // 클래스 명도 넣을 수 있고 커스텀 가능함
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // 쉽게 말해서 호출이 될 때마다 joinPoint라는데다가
        // 파라미터가 여러 개 있음, 아규먼트가 뭔지, 어느 타겟을 호출하는지, 지금 내가 누군지 등등
        // 이걸 가지고 원하는걸 조작할수도 있고, 필요하면 다음 메서드 호출
        // 메서드 호출할 때마다 인터셉트가 딱딱 걸리는것임
        // 필요하면 다음으로 넘어가지 마! 이런것도 가능함
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
