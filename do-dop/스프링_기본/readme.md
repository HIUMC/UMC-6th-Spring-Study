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

# <섹션 5 - 싱글톤 컨테이너>
# <섹션 6 - 컴포넌트 스캔>
# <섹션 7 - 의존관계 자동 주입>
# <섹션 8 - 빈 생명주기 콜백>
# <섹션 9 - 빈 스코프>