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