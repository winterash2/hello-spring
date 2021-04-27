package hello.hellospring.domain;


import javax.persistence.*;

@Entity // 이제부터 이건 jpa가 관리하는 Entity구나 라고 인식함
public class Member {

    @Id // pk를 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 디비가 알아서 생성해주는 전략은 IDENTITY임
    private Long id;
//    @Column(name = "username") // DB에 만약에 컬럼명이 username이면 이 방식으로 매핑을 해줘야 함. 현재는 그냥 name이기 때문에 상관없음
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
