# <섹션 1 - 객체 지향 설계와 스프링>
### 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크 !!

객체 지향은 유연하고, 변경이 용이

다형성 ,, 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경

## 좋은 객체 지향 설계의 5가지 원칙 SOLID

### SRP 단일 책임 원칙

- **중요한 기준은 변경 !** 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것

### OCP 개방-폐쇄 원칙

- 소프트웨어는 확장에는 열려 있고, 변경에는 닫혀 있어야 한다.

### LSP 리스코프 치환 원칙

- 인터페이스 규약을 지켜야 함

### ISP 인터페이스 분리 원칙

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.

### DIP 의존관계 역전 원칙

- 추상화에 의존해야지, 구체화에 의존하면 안 된다. 즉 구현 클래스에 의존하는 것이 아닌, 인터페이스(역할)에 의존

<정리>

→ **역할과 구현 분리**

# <섹션 2 - 스프링 핵심 원리 이해 1 (예제 만들기)>
## 회원 도메인 설계

클라이언트 → 회원 서비스(MemberServiceImpl) → 회원 저장소(메모리, DB)

## 주문과 할인 도메인 개발

impl(implements), interface 헷갈림 ~

**interface** : 추상 메서드들을 나열한 형태 → 이 명세를 보고 클라이언트 프로그램은 선언되어있는 명세만 보고 이를 구현한 클래스를 사용할 수 있다

- 코드에서 인터페이스의 메서드를 호출하면 인터페이스는 구현 객체의 메서드를 찾아서 호출

**implementation**: 구현체.  인터페이스와 그에 해당하는 구현 클래스가 있을 때, 구현 클래스의 이름에 "Impl"이라는 접미사가 붙는다. 이는 해당 클래스가 해당 인터페이스의 구현체임을 나타냄.

```sql
@Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
      Member member = memberRepository.findById(memberId);
      int discountPrice = discountPolicy.discount(member, itemPrice);
  }
```

위 코드는 단일 체계원칙을 잘 지킨 것임. 할인에 대한 것을 변경할 때 Order를 변경할 필요가 없다.

## 주문과 할인 도메인 테스트

역할을 분리 잘 해야함

# <섹션 3 - 스프링 핵심 원리 이해 2 (객체 지향 원리 적용)>
assertion이 뭘까 
## 새로운 할인 정책 적용과 문제점

OrderServiceImpl는 DiscountPolicy뿐만 아니라 FixDiscountPolicy에도 의존하던 상태라, FixDiscountPolicy에서 RateDiscountPolicy로 의존관계를 변경하면, OrderServiceImpl의 코드도 바꿔야 한다.

→ Interface에만 의존하도록 변경하면 문제 해결 !

```sql
//변경 전
private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

//변경 후
private DiscountPolicy discountPolicy;  
```

→ 의존성 해결 ! BUT 실제로 실행을 하면 NPE(Null Pointer Exception)이 발생한다.

해결방안 : OderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.

## 관심사의 분리

AppConfig : 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, “구현 객체를 생성”하고, “연결”하는 책임을 가지는 별도의 설정 클래스

생성자 주입,, 의존관계에 대한 고민은 외부에 맡기고, 실행에 집중

Appconfig가 MemberServiceImpl이 원래 생성했던 MemoryMemberRepository를 대신 생성함

그 후 Appconfig가  memberServiceImpl 생성 후 MemoryMemberRepository 주입

## 새로운 구조와 할인 정책 적용

정액 할인 정책을 정률% 할인 정책으로 변경(FixDiscountPolicy → RateDiscountPolicy)

구성영역인 AppConfig만 변경!!

## 전체 흐름 정리

AppConfig는 공연 기획자 역할 → “구현 객체를 생성”하고 “연결”

## 좋은 객체 지향 설계의 4가지 원칙의 적용

### SRP(단일 책임 원칙) :  한 클래스는 하나의 책임만 가진다.

- 클라이언트 객체는 실행하는 책임만 담당, 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당

### DIP(의존관계 역전 원칙) : 추상화에 의존 O , 구체화에 의존 X

- 클라이언트 코드가 `DiscountPolicy` 추상화 인터페이스 뿐만 아니라 `FixDiscountPolicy` 구체화 구현 클래스도 함께 의존하였음
- AppConfig가 `FixDiscountPolicy` 을 대신 생성해서 의존관계를 주입함

### OCP : 소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 함.

- AppConfig가 의존관계를 `FixDiscountPolicy` 에서 `RateDiscountPolicy`로 변경해서 클라이언트 코드에 주입하므로 클라이언트 코드는 변경하지 않아도 된다.

## IoC, DI, 그리고 컨테이너

### 제어의 역전 IoC(Inversion of Control)

프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것

### 의존관계 주입 DI(Dependency Injection)

정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체 의존 관계를 분리해서 생각

- 정적인 클래스 의존 관계 : import코드만 보고 실행하지 않아도 의존관계 판단 가능
- 동적인 의존 관계 : 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계

의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 할 수 있다.

### 컨테이너

AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것

## 스프링으로 전환

`ApplicationContext`를 스프링 컨테이너라고 한다.

@Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록

스프링 빈은 `applicationContext.getBean()` 메서드를 사용 해서 찾을 수 있다.

# <섹션 4 - 스프링 컨테이너와 스프링 빈>
## 스프링 컨테이너 생성

ApplicationContext → 컨테이너, 인터페이스

1. 스프링 컨테이너 생성
    1. 스프링 컨테이너 안에는 스프링 빈 저장소가 있음.
    2. 스프링 컨테이너를 생성할 때는 구성 정보를 지정해줘야 함.
2. 스프링 빈 등록
    1. 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해 스프링 빈 등록
    2. 빈 이름은 메서드 이름을 사용(직접 부여도 가능) → **항상 다른 이름 부여!**
3. 스프링 빈 의존관계 설정 - 준비
    1. 스프링 빈 생성
4. 스프링 빈 의존관계 설정 - 완료
    1. 의존관계를 주입(DI)

## 컨테이너에 등록된 모든 빈 조회

Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈

Role ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈

## 스프링 빈 조회 - 기본

## 스프링 빈 조회 - 동일한 타입이 둘 이상

같은 타입이 둘 이상이면 오류 발생 → 빈 이름을 지정하자

## 스프링 빈 조회 - 상속 관계

부모 타입으로 조회하면, 자식 타입도 함께 조회됨.

**assertThrow?**

- 예외가 발생하는지를 확인하는 데 사용된다.

    ```java
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    //NoUniqueBeanDefinitionException.class는 예상되는 예외 유형, ac 애플리케이션 컨텍스트에서 DiscountPolicy 타입의 빈이 유일하지 않을 때 예외가 발생하는지를 확인
    ```


**assertThat?(AssertJ라이브러리)**

- 특정 조건을 검증하기 위해 사용되며 예상되는 결과와 실제 결과를 비교하여 테스트를 수행한다.

    ```java
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    //rateDiscountPolicy 객체가 RateDiscountPolicy 클래스의 인스턴스인지를 검증
    ```


**object type?**

Object는 Java에서 모든 클래스의 부모 클래스

따라서 **`Object`** 타입으로 조회하면 애플리케이션 컨텍스트에 등록된 모든 빈을 조회할 수 있습니다.

## BeanFactory와 ApplicationContext

**“BeanFactory”**

- 스프링 컨테이너의 최상위 인터페이스
- 스프링 빈을 관리하고 조회

**“ApplicationContext”**

- BeanFactory 기능을 모두 상속받아서 제공
- 스프링 빈 관리/조회 외에 많은 부가기능 필요
- MessageSource, EnvironmentCapable, ApplicationEventPublisher, ResourceLoader

## 다양한 설정 형식 지원 - 자바 코드, XML

스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계되어 있음

## 스프링 빈 설정 메타 정보 - BeanDefinition
# <섹션 5 - 싱글톤 컨테이너>
## 웹 애플리케이션과 싱글톤

## 싱글톤 패턴

클래스 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴 !

→ 객체 인스턴스를 2개 이상 생성하지 못하도록 private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막음.

단점 : 코드를 많이 짜야함, 구체클래스에 의존하게 됨, 테스트하기 어려움, 내부 속성을 변경하고 초기화하기 어려움 → 유연성 떨어짐(안티패턴)

## 싱글톤 컨테이너

스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다. → 싱글톤 패턴의 단점 보완

## 싱글톤 방식의 주의점

여러 클라이언트가 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계하면 안 된다. → **무상태(stateless)**로 설계 !!

## @Configuration과 싱글톤

## @Configuration과 바이트코드 조작의 마법

클래스의 바이트 코드 조작 라이브러리(CGLIB 기술)를 사용해서 Appconfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록!

```sql
@Bean
public MemberRepository memberRepository() {
	if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) { 
		return 스프링 컨테이너에서 찾아서 반환;
	} else { //스프링 컨테이너에 없으면 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록 
		return 반환
	}
}
```

@Configuration을 빼도 되지만, 빼게 되면??

→ **싱글톤을 보장하지 않는다**

<정리>

- @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
- `memberRepository()` 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않 는다.
- 크게 고민할 것이 없다. 스프링 설정 정보는 항상 `@Configuration` 을 사용하자.## 웹 애플리케이션과 싱글톤

## 싱글톤 패턴

클래스 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴 !

→ 객체 인스턴스를 2개 이상 생성하지 못하도록 private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막음.

단점 : 코드를 많이 짜야함, 구체클래스에 의존하게 됨, 테스트하기 어려움, 내부 속성을 변경하고 초기화하기 어려움 → 유연성 떨어짐(안티패턴)

## 싱글톤 컨테이너

스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다. → 싱글톤 패턴의 단점 보완

## 싱글톤 방식의 주의점

여러 클라이언트가 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계하면 안 된다. → **무상태(stateless)**로 설계 !!

## @Configuration과 싱글톤

## @Configuration과 바이트코드 조작의 마법

클래스의 바이트 코드 조작 라이브러리(CGLIB 기술)를 사용해서 Appconfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록!

```sql
@Bean
public MemberRepository memberRepository() {
	if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) { 
		return 스프링 컨테이너에서 찾아서 반환;
	} else { //스프링 컨테이너에 없으면 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록 
		return 반환
	}
}
```

@Configuration을 빼도 되지만, 빼게 되면??

→ **싱글톤을 보장하지 않는다**

<정리>

- @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
- `memberRepository()` 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않 는다.
- 크게 고민할 것이 없다. 스프링 설정 정보는 항상 `@Configuration` 을 사용하자.
# <섹션 6 - 컴포넌트 스캔>
## 컴포넌트 스캔과 의존관계 자동 주입

스프링은 자동으로 스프링 빈을 등록하는 **컴포넌트 스캔**이라는 기능을 제공하고, 의존관계도 자동으로 주입하는 `@Autowired` 라는 기능도 제공한다.

@Component를 사용할 때 의존관계를 주입하기 위해 @Autowired를 사용한다.

(이전에 AppConfig에서는 `@Bean` 으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다.)

## 탐색 위치와 기본 스캔 대상

```jsx
@ComponentScan(
         basePackages = "hello.core",
}
```

- basePackages` : 탐색할 패키지의 시작 위치를 지정

  지정하지 않으면 `@ComponentScan` 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.


**권장하는 방법**

패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것

컴포넌트 스캔은 `@Component` 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다. 

`@Component` : 컴포넌트 스캔에서 사용

`@Controller` : 스프링 MVC 컨트롤러에서 사용

`@Service` : 스프링 비즈니스 로직에서 사용

`@Repository` : 스프링 데이터 접근 계층에서 사용

`@Configuration` : 스프링 설정 정보에서 사

## 필터

- `includeFilters` : 컴포넌트 스캔 대상을 추가로 지정한다.
- `excludeFilters` : 컴포넌트 스캔에서 제외할 대상을 지정한다

**<FilterType 5가지 옵션>**

**ANNOTATION**: 기본값, 애노테이션을 인식해서 동작한다. ex) `org.example.SomeAnnotation`

**ASSIGNABLE_TYPE**: 지정한 타입과 자식 타입을 인식해서 동작한다. ex) `org.example.SomeClass`

**ASPECTJ**: AspectJ 패턴 사용 ex) `org.example..*Service+`

**REGEX**: 정규 표현식 ex) `org\.example\.Default.*`

**CUSTOM**: `TypeFilter` 이라는 인터페이스를 구현해서 처리 ex) `org.example.MyTypeFilter`

## 중복 등록과 충돌

컴포넌트 스캔에 같은 빈 이름을 등록했을 때

1. 자동 빈등록 vs 자동 빈등록

자동으로 스프링 빈이 등록돼서 거의 없음, `ConflictingBeanDefinitionException` 예외 발생

2. 수동 빈등록 vs 자동 빈등록

수동 빈 등록이 우선권을 가짐.

최근 스프링은 오류를 내버림

`Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true`
# <섹션 7 - 의존관계 자동 주입>
## 다양한 의존관계 주입 방법

<의존관계 주입의 4가지 방법>

- **생성자 주입**

  생성자를 통해 의존 관계 주입, **“불변, 필수”** 의존관계에 사용

  생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입

- **수정자 주입(setter 주입)**

  setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입

- **필드 주입**

  의존관계를 필드에 바로 주입, 코드는 간결해지지만 외부에서 변경이 어려워 사용하지 말자 !

- **일반 메서드 주입**

  일반 메서드를 통해서 주입, 일반적으로 잘 사용하지 않음


## 옵션 처리

<자동 주입 대상을 옵션으로 처리하는 방법>

**@Autowired(required=false)** : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 

```jsx
//호출 안됨
@Autowired(required = false)
public void setNoBean1(Member noBean1) {
    System.out.println("setNoBean1 = " + noBean1);
}
```

**org.springframework.lang.@Nullable** : 자동 주입할 대상이 없으면 null이 입력된다. 

```jsx
//null 호출
@Autowired
public void setNoBean2(@Nullable Member noBean2) {
    System.out.println("setNoBean2 = " + noBean2);
}
```

**Optional<>** : 자동 주입할 대상이 없으면 `Optional.empty` 가 입력된다.

```jsx
//Optional.empty 호출
@Autowired(required = false)
public void setNoBean3(Optional<Member> noBean3) {
    System.out.println("setNoBean3 = " + noBean3);
}
```

## 생성자 주입을 선택하라!

**final 키워드**

생성자 주입을 사용하면 필드에 `final` 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.

```jsx
@Component
 public class OrderServiceImpl implements OrderService { 
	 private final MemberRepository memberRepository;
	 private final DiscountPolicy discountPolicy;
	 
	@Autowired
	    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
	        this.memberRepository = memberRepository;
	    }
	//...
}
```

## 롬복과 최신 트랜드

롬복 라이브러리가 제공하는 `@RequiredArgsConstructor`기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다. 

## 조회 빈이 2개 이상

## @Autowired 필드 명, @Qualifier, @Primary

**<조회 대상 빈이 2개 이상일 때 해결 방법>**

- **@Autowired 필드 명 매칭**

  타입 매칭, 필드 명을 빈 이름으로 변경

    ```java
     @Autowired
     private DiscountPolicy rateDiscountPolicy
    ```

- **@Qualifier**

  추가 구분자

    ```java
    @Qualifier("mainDiscountPolicy")
    ```

- **@Primary 사용**

  우선순위 정하는 방법(@Qualifier가 우선순위가 더 높음)

## 애노테이션 직접 만들기
## 조회한 빈이 모두 필요할 떄, List, Map
## 자동, 수동의 올바른 실무 운영 기준

# <섹션 8 - 빈 생명주기 콜백>
## 빈 생명주기 콜백 시작

네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요함.

초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
**스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공**한다. 또한 **스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백**을 준다. 

**<스프링 빈의 이벤트 라이프사이클>**

**스프링 컨테이너 생성 → 스프링 빈 생성** → **의존관계 주입** → **초기화 콜백** → **사용 → 소멸전 콜백 → 스프링 종료

객체의 생성과 초기화 분리 !**

## 인터페이스 InitializingBean, DisposableBean

초기화, 소멸 인터페이스 단점

→ 스프링 의존 인터페이스

## 빈 등록, 초기화, 소멸 메서드

**설정 정보 사용 특징**

- 메서드 이름을 자유롭게 줄 수 있다.
- 스프링 빈이 스프링 코드에 의존하지 않는다.
- 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적 용할 수 있다.

**종료 메서드 추론**

- `@Bean`의 `destroyMethod` 속성에는 아주 특별한 기능이 있다.
- 라이브러리는 대부분 `close` , `shutdown` 이라는 이름의 종료 메서드를 사용한다.
- @Bean의 `destroyMethod` 는 기본값이 `(inferred)` (추론)으로 등록되어 있다.
- 이 추론 기능은 `close` , `shutdown` 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추론해서 호출해준다.

## 애노테이션 @PostConstruct, @PreDestroy

`@PostConstruct` , `@PreDestroy` 이 두 애노테이션을 사용하면 가장 편리하게 초기화와 종료를 실행할 수 있다.

최신 스프링에서 **가장 권장**하는 방법이다.애노테이션 하나만 붙이면 되므로 매우 편리하다.

코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 `@Bean` 의 `initMethod` , `destroyMethod` 를 사용하자.
# <섹션 9 - 빈 스코프>
## 빈 스코프란?

스코프는 말 그대로 빈이 존재할 수 있는 범위를 뜻한다. 스프링 빈이 스프링 컨테이너 시작과 함께 생성되어 스프링 컨테이너가 종료될 때까지 유지되는 것은 스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문이다.

**싱글톤**: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프이다.

## 프로토타입 스코프

**싱글톤** 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환한다.

반면에 **프로토타입** 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환한다. **핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다는 것이다.** 그래서 `@PreDestroy` 같은 종료 메서드가 호출되지 않는다.

**<프로토타입 빈의 특징 정리>**

- 스프링 컨테이너에 요청할 때 마다 새로 생성된다.
- 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
- **종료 메서드가 호출되지 않는다.**
- 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.

## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점

싱글톤 빈과 함께 사용할 때는 의도한 대로 잘 동작하지 않으므로 주의해야 한다.

clientBean이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다. 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 새로 생성이 된 것이지, **사용 할 때마다 새로 생성되는 것이 아니다!**

## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제

프로토타입 빈과 싱글톤 빈을 함께 사용할 때, 어떻게 하면 사용할 때마다 항상 새로운 프로토타입 빈을 생성할 수 있을까? → 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것

의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 **Dependency Lookup (DL)** 의존관계 조회(탐색) 이라한다.

## 웹 스코프

* 싱글톤은 스프링 컨테이너의 시작과 끝까지 함께하는 매우 긴 스코프이고, 프로토타입은 생성과 의존관계 주입, 그리고 초기화까지만 진행하는 특별한 스코프이다.

**웹 스코프 특징**

- 웹 스코프는 웹 환경에서만 동작
- 웹 스코프는 스프링이 해당 스코프의 종료시점까지 관리(종료 메서드 호출)

**웹 스코프 종류**

- request : HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리
- session : HTTP Session과 동일한 생명주기를 가지는 스코프
- application : 서블릿 컨텍스트( `ServletContext` )와 동일한 생명주기를 가지는 스코프
- websocket : 웹 소켓과 동일한 생명주기를 가지는 스코프

## request 스코프 예제

## 스코프와 Provider

## 스코프와 프록시

의존관계 주입이 가짜 프록시 객체로 주입된다.

- **가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.**
    - 가짜 프록시 객체는 내부에 진짜 `myLogger` 를 찾는 방법을 알고 있다.
    - 클라이언트가 `myLogger.log()` 을 호출하면 사실은 가짜 프록시 객체의 메서드를 호출한 것이다.
    - 가짜 프록시 객체는 request 스코프의 진짜 `myLogger.log()` 를 호출한다.
    - 가짜 프록시 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에 이 객체를 사용하는 클라이언트 입장에서는 사 실 원본인지 아닌지도 모르게, 동일하게 사용할 수 있다(다형성)
- **동작 정리**
    - **CGLIB**라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다.
    - 이 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.
    - 가짜 프록시 객체는 실제 request scope와는 관계가 없다. 그냥 가짜이고, 내부에 단순한 위임 로직만 있고, 싱 글톤 처럼 동작한다.
- **특징 정리**
    - 프록시 객체 덕분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다.
    - 사실 Provider를 사용하든, 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
