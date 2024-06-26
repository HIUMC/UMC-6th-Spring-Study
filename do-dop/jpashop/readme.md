# <섹션1 프로젝트 환경설정>

  ## View 환경 설정

  controller는 @Controller 어노테이션을 달아줘야 함

  GetMapping?
<aside>
💡 **@RequestMapping이란?**
특정 url을 요청을 수행할 Controller과 매핑하여 지정하는 어노테이션

**@RequestMapping이 사용하는 속성**

- **value**
  value는 연결할 url을 지칭한다. 보통 호스트 주소와 포트 번호를 제외하고, REST API에 따른 url을 설계한다.

    ```java
    @RequestMapping(value="/hello")
    ```

  localhost:8080/hello로 url을 입력했을 경우에 해당

  value를 생략하거나 다중 요청도 가능하다.

    ```java
    @RequestMapping({"/hello", "/hello-buddy", "/hello/**"}
    ```


**@GetMapping**

```java
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
```

HelloController를 새로 만들어주고 어노테이션을 통해 controller임을 명시해줍니다.

그 다음으로 @GetMapping("hello")를 적어줬습니다.

이는 localhost:8080/hello 요청이 들어오면 아래의 함수를 실행하라고 해석하시면 됩니다.

</aside>

  - **spring-boot-devtools**를 사용하면 html을 수정할 때마다 컴파일할 필요없이 build→recompile ‘hello.html’만 해주면 됨

  ## H2 데이터베이스 설치

  ## JPA와 DB 설정, 동작확인

  @Entity?
  해당 클래스가 엔티티 클래스임을 Spring에 알려준다

# <섹션2 도메인 분석 설계>

## 요구사항 분석

회원기능(회원가입, 회원목록)

상품기능(상품 등록, 상품 목록)

주문기능(상품 주문, 주문 내역)

## 도메인 모델과 테이블 설계

- 외래 키가 있는 곳을 연관관계의 주인으로 정해라

## 엔티티 클래스 개발

Enum을 사용할 때 @Enumerated(EnumType.STRING) → 꼭 STRING 사용해야함

값 타입은 변경 불가능하게 설계해야 한다. @Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자. JPA 스펙상 엔티티나 임베디드 타입( `@Embeddable` )은 자바 기본 생성자(default constructor)를 `public` 또는 `protected` 로 설정해야 한다. `public` 으로 두는 것 보다는 `protected` 로 설정하는 것이 그나마 더 안전하다.

JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.

## 엔티티 설계시 주의점

- 엔티티에는 가급적 Setter 사용 X (유지보수 어렵)
- **모든 연관관계는 지연로딩으로 설정!**
    - 즉시로딩(EAGER)은 예측이 어려움
    - 지연로딩(LAZY)으로 설정
    - XToOne관계는 기본이 EAGER이므로 LAZY로 설정해야한다.(fetch = LAZY)
- 컬렉션은 필드에서 초기화 하자
- 테이블, 컬럼명 생성 전략
    - 하이버네이트 기존 구현 : 엔티티의 필드명을 그대로 테이블 명으로 사용
    - 스프링 부트 신규 설정(엔티티(필드) → 테이블(칼럼))
        - 카멜 케이스 → 언더스코어
        - .(점) → _(언더스코어)
        - 대문자 → 소문자

# <섹션3 애플리케이션 구현 준비>

  ## 구현 요구사항

  ## 애플리케이션 아키텍쳐
<계층적 구조 사용>

- controller, web : 웹 계층
- service : 비지니스 로직, 트랜젝션 처리
- repository : JPA를 직접 사용하는 계층, 엔티티 매니저 사용
- domain : 엔티티가 모여 있는 계층, 모든 계층에서 사용

<패키지 구조>

- jpabook.jpashop
    - domain
    - exception
    - repository
    - service
    - web


개발 순서 : 서비스, 리포지토리 계층을 개발하고, 테스트 케이스를 작성해서 검증, 마지막에 웹 계층 적용

# <섹션4 회원 도메인 개발>
## 회원 리포지토리 개발

`@PersistenceContext` ?

<aside>
💡 **영속성 컨텍스트?**
엔티티를 영구 저장하는 환경

**@PersistenceContext**
EntityManager(영속성 관리)를 빈으로 주입할 때 사용하는 어노테이션

- EntityManagerFactory에서 새로운 EntityManager를 생성하거나
- Transaction에 의해 기존에 생성된 EntityManager를 반환해줍니다.
</aside>

EntityManager 주입

## 회원 서비스 개발

`@Transactional(readOnly = true)`  : DB에서 데이터를 읽기만 하는 서비스 메서드에 적용, 수정 X

`@RequiredArgsConstructor`  final필드에 대해 생성자를 생성해준다.

## 회원 기능 테스트
# <섹션5 상품 도메인 개발>

# <섹션6 주문 도메인 개발>
# <섹션7 웹 계층 개발>