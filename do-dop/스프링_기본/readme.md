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
# <섹션 8 - 빈 생명주기 콜백>
# <섹션 9 - 빈 스코프>