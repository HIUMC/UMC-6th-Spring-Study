# Chapter7 JPA를 통한 엔티티 설계, 매핑 & 프로젝트 파일 구조 이해
설계한 DB의 ERD를 통해 **Spring Data JPA를 이용하여 Entity를 설계하는 것 필요 !**

### domain

JPA에서 사용하기 위한 엔티티 클래스를 저장

### controller

http요청이 오면, 그에 대한 응답을 주는 클래스의 모임, 응답을 주기 위한 과정들은 service에서 처리하도록 함

### service

비즈니스 로직이 필요한 클래스들의 모임이며, 가장 복잡한 코드가 들어 감

### repository

database와 통신하는 계층으로, Spring Data JPA를 이용해서 만든 repository 이용

controller에서 service의 메소드를 호출

service는 repository의 메소드를 호출

### dto

클라이언트가 body에 담아서 보내는 데이터를 받는 클래스

DB에서 받아온 데이터를 클라이언트에게 보여주기 위한 클래스

**dto를 통해 응답 데이터를 결정하게 되면, Database의 변경이 생길 경우 dto만 변경하면 되기에** 더 좋은 설계가 된다.

### converter

데이터 형식 간의 변환을 수행

repository에서 받아온 엔티티를 dto로 바꾸는 과정을 converter에서 수행

entity의 생성 역시 converter에서 수행 → service는 순수하게 비즈니스 로직에만 집중할 수 있음

- converter 사용 위치
    1. service에서 dto 생성

       service에서 converter를 통해 dto를 controller에 리턴

    2. controller에서 dto 생성

       service에서 entity를 리턴하고, controller에서 converter를 통해 dto를 만들어서 응답을 줌


## Entity 매핑

**@GeneratedValue(strategy = GenerationType.IDENTITY)**

→  **JPA가 통신을 하는 DBMS의 방식을 따른다**

```java
@Entity //해당 클래스가 JPA의 엔티티임을 명시
@Getter //Lombok에서 제공, Getter를 만들어주는 어노테이션
@Builder  //Builder패턴 사용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor     
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
```

### Enum

정해진 값들 중에 특정 값이 저장된다.

**@Enumerated 어노테이션을 이용해서 enum을 entity에 적용**

이때, 반드시 EnumType을 STRING으로 적용 !

기본 값인 ORDINAL을 사용하면 데이터베이스에 enum의 순서가 저장이 되는데,

만약 Springboot에서 enum의 순서를 바꾸게 될 경우 에러 발생

### Created_at, Updated_at

모든 엔티티에서 다 사용하여 매번 넣어주기 귀찮은 요소

base라는 패키지에 따로 작성

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

//모든 엔티티 클래스마다 상속 받기
public class Member extends BaseEntity {
...
```

SpringBoot Application 자체도 JpaAuditing 사용이 가능하도록 변경

```java
@SpringBootApplication
@EnableJpaAuditing
public class StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}
}
```

## 연관관계 매핑

**연관관계 주인이란?** 실제 데이터베이스에서 외래키를 가지는 엔티티를 의미

### 단방향 매핑

- 연관 관계 주인에게만 연관 관계 주입
- 1 : N의 경우, N에 해당하는 테이블이 외래키를 가지며, N에 해당하는 엔티티가 연관 관계의 주인이다.
- 1 : 1의 경우, 둘 중 하나만 외래키를 가지면 되기에 원하는 엔티티를 연관관계 주인으로 설정

N : 1에서 N에 해당하는 엔티티가 연관 관계를 매핑할 때

`@ManytoOne` 사용

`@JoinColumn`은 실제 데이터베이스에서 해당 칼럼(외래키)의 이름을 설정

### 양방향 매핑

연관 관계 주인이 아닌 엔티티에게도 연관 관계 주입

- 버그가 생길 위험이 있지만, 객체 그래프 탐색은 프로그래밍의 편리 제공
- cascade의 설정이 가능(연관 관계의 주인인 테이블에 설정을 하여, 참조 대상인 테이블의 칼럼이 변경될 때 같이 변경이 되는 기능)
- BUT ! JPA에서는 연관관계의 주인이 아닌, 참조의 대상이 되는 엔티티에 설정

`@OneToMany`

1에 해당하는 엔티티가 N에 해당하는 엔티티와 관계가 있음을 명시

N에 해당하는 엔티티를 mappedBy해야 함

```java
@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
```

## 칼럼 별 세부 설정

각 멤버 변수 별 세부 설정

ex) Member에서 name은 table에서는 varchar(20), JPA에서는 String

`@Column`

유니크, 디폴트 값, null이 가능한지 등을 설정

```java
@Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)") //칼럼의 타입을 직접 지정
    private Gender gender;

```

mysql은 문자열을 무조건 ‘’로 감싸야한다.

@Column(columnDefinition = "VARCHAR(15) DEFAULT **'ACTIVE'**")