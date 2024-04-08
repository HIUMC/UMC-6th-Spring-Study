
## 좋은 객체 지향 설계의 5가지 원칙 (SOLID)

### 1. SRP : 단일 책임 원칙

-한 클래스는 하나의 책임만 가져야 한다.

ㄴ책임에 대한 기준은 변경. 변경이 있을 때 파급 효과가 적으면 원칙을 잘 따른 것

ex) UI 변경, 객체의 생성과 사용을 분리

### 2. OCP : 개방-폐쇄 원칙

-소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀있어야 한다.

ㄴ 즉, 다형성을 활용해보자!! → 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현한다는 뜻. 이러면 확장은 되고, 변경은 안함!

```java
public class MemberService {

// private MemberRepository memberRepository = new MemoryMemberRepository();
 private MemberRepository memberRepository = new JdbcMemberRepository();

}
```

**문제점**

MemberService가 직접 구현 클래스를 선택함 → 구현 객체를 변경하려면 코드를 변경해야함

즉, 다형성을 사용했으나  OCP원칙을 지킬 수 없음..

⇒ 객체를 생성하고, 연관 관계를 맺어주느 별도의 조립, 설정자가 필요,, ⇒ **스프링!!!**

### 3. LSP : 리스코프 치환 법칙

-프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야함.

ex) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능 ㅇㅇ 뒤로 가게 구현하면 LSP 위반

### 4. ISP : 인터페이스 분리 원칙

-특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다

⇒ 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### 5. DIP : 의존관계 역전 원칙

-프로그래머는 추상화에 의존해야함. 구체화에 의존 ㄴㄴ

→ 구현 클래스에 의존하지 말고, 인터페이스에 의존해야한다. 그래야 유연하게 구현체를 변경할 수 있음

```java
public class MemberService {

// private MemberRepository memberRepository = new MemoryMemberRepository();
 private MemberRepository memberRepository = new JdbcMemberRepository();

}
```

이 코드도 MemberService는 인터페이스에 의존하지만, 구현 클래스도 동시에 의존한다.

⇒ DIP를 위반함

### 정리

-객체 지향의 핵심은 다형성이나, 그것만으로는 OCP, DIP를 지킬 수 없으므로  개발할 수 없음..

→ 뭔가 더 필요함… 

### ⇒ 그것이 바로 스프링!

모든 설계에 역할과 구현을 분리해야함! 그래야 유연하게 변경할 수 있다

⇒ 즉 이상적으로는 모든 설계에 인터페이스를 부여하자!

그러나,,, 인터페이스를 무분별하게 도입하면 추상화라는 비용이 발생함. 

→ 기능 확장을 안 한다면 구체 클래스를 직접 사용하거나 인터페이스를 리팩터링해서 도입하면 됨


스프링 핵심 원리 이해2 - 객체 지향 원리 이용

https://www.notion.so/makeus-challenge/2-8058ec3b0be24817bbde79ffce2cfbdf

//사진이 있어서 노션 링크로 대체합니
