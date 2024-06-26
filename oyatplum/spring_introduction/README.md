## 1주차 정리 📝
***
[참고] https://oyatplum.tistory.com/47
### 1. 웹 기초
* 정적 컨텐츠
  * 관련 컨트롤러 X > html 파일 반환
* MVC 패턴
  * Model, View, Controller
  * MVC 패턴은 소프트웨어 디자인 패턴 중 하나로 Controller를 통해서 Model로부터 받은 데이터를 View에 전달하여 사용자에게 제공하는 것
* API
  * @ResponseBody 필요 - 메소드의 반환 값이 HTTP 응답의 body로 사용
  * 메소드가 반환하는 문자열 / 객체가 View를 통하지 않고 client에게 전달
  * HttpMesaageConveter
    * JsonConveter
    * StringConverter

### 2. 일반적인 웹 애플리케이션 계층 구조
* 컨트롤러 : 웹 MVC의 컨트롤러
* 도메인 : db에서 저장하고 관리하는 객체
* 서비스 : 핵심 비즈니스 로직
* 레포지토리 : 도메인 객체를 가지고 서비스가 동작하도록 구현

### 3. API와 Server
* API
  * 서버와 클라이언트 간의 통신을 위한 규칙
  * 웹 API는 HTTP 프로토콜을 사용하여 통신하며, JSON 또는 XML과 같은 데이터 형식을 주고받음
* Server
  * 서버는 이러한 API를 사용하여 클라이언트의 요청을 처리하고 응답을 제공

### 4. RESTful
* Representational State Transfer(표현 상태 전송)의 약어로, 네트워크 아키텍처 원리 중 하나
* 자원을 정의하고 이러한 자원에 대한 상태 전송을 HTTP 프로토콜을 통해 수행하는 방법을 기술
  * 자원(resource) : 모든 것을 자원으로 간주하고 각 자원은 고유한 식별자(URI)를 가지고 있음
  * 행위(verb) : HTTP 메서드(GET, POST, PUT, DELETE 등)를 통해 자원에 대한 행위를 정의. RESTful 아키텍처에서는 이를 통해 자원의 상태를 변경, 조회 가능.
  * 표현(representation) : 클라이언트가 요청할 때 서버는 해당 자원의 표현을 반환. 표현은 다양한 형식(XML, JSON, HTML 등)으로 제공.
  * 연결(connectedness) : 하이퍼링크를 통해 자원 간의 관계를 표현

***
### 추가적 사항
#### - 다형성
```MemberRepository repository = new MemoryMemberRepository();```
- 다형성은 상속과 인터페이스로 구현.
- Java에서는 인터페이스를 구현한 클래스의 객체를 해당 인터페이스 타입의 변수에 할당할 수 있음. 이를 통해 다형성(polymorphism)구현
- 상속 : 상속하면 기능 급격히 증가 > class간 호환성 하락 > 자식 class를 부모 class로서 작동하도록 해야 함
- **자식 클래스를 부모 클래스로서 작동**: 다형성은 자식 클래스 객체를 부모 클래스 타입의 변수에 할당하여 사용할 수 있다는 개념.
    - 이를 통해 여러 가지 하위 클래스들을 단일한 인터페이스나 부모 클래스로 다룰 수 있음.
        - 즉, 부모 클래스로 선언된 변수는 실제로 그 부모 클래스의 객체일 수도 있고, 그 부모 클래스를 상속받은 자식 클래스의 객체일 수도 있음. 이것은 코드의 유연성을 높이고, 코드를 보다 일반적으로 작성할 수 있게 해 줌.