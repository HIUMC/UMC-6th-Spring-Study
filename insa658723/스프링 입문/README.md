# 스프링입문

### 섹션1. 프로젝트 환경설정

1강. 프로젝트 생성

- start.spring.io에서 스프링 프로젝트 시작
- gradle
- Artifact: 빌드되어서 나오는 결과물
- template engine ⇒ tyhmeleaf: html을 만들어줌
- src에 main, test 폴더가 나뉘어져있음.
- main→java→resources에는 실제 자바 코드파일을 제외한 다른 설정파일들이 들어감.
- build.gradle이 중요함.
- **컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( `viewResolver` )가 화면을 찾아서 처리한다. ex) return “hello” → templates/hello.html**
- Tomcat started on port 8080 (http) with context path '’
    
    **⇒ 중요!! localhost:8080으로 접속할 수 있음.**
    

2강. 라이브러리

- 의존관계를 고려하여 하나의 라이브러리만 가지고 와도 그에 필요한 다른 라이브러리도 알아서 가져온다.
- spring core까지.
- gradle→dependencies
- Dependencies: 라이브러리간의 의존관계.

4강. 빌드하고 실행하기

- iTerm에서 Spring 프로젝트에 들어가서 ./gradlew build로 실행.

### 섹션2.웹개발 기초

- 정적컨텐츠: 웹브라우저에서 url을 호출하면 톰캣서버가 스프링 컨테이너 안에 있는 관련된 Controller를 찾고, 없는 경우 resource(설정파일) 안에 있는 관련 문서를 반환한다.
- MVC vs API: HTML로 내리냐 API방식으로 데이터로 바로 내리냐.

```java
//MVC
//@Controller public class HelloController
@GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") 
	    String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
     }
```

1. 매핑된 "hello-mvc"를 통해서 들어가면 
2. Controller에서 "hello-template"을 model에 파라미터 “name”을 넣어서 반환. 
3. viewResolver가 templates에서 hello-template를 찾아서 thymleaf 템플릿 엔진처리를 한 후에 반환.

⇒ 여기서 모델은 DB대신 사용하는거??

- API: ResponseBody 사용.

```java
@GetMapping("hello-string")
     @ResponseBody
     public String helloString(@RequestParam("name") String name) {
         return "hello " + name;
     }
     // viewResolver 사용x
    //html이 아니라 데이터를 직접반환.
```

- @Responsebody: http의 body부에 이 데이터를 직접넣겠다라는 뜻.
- API는 html을 사용하지 않고 데이터를 그대로 내림.
- 컨트롤러가 responsebody를 사용해서 객체를 반환하면 객체가 JSON으로 변환되고,
- viewResolver 대신 HttpMessageConverter 동작.
- 그리고 변환된 데이터를 웹브라우저로 전달한다.

### **섹션3. 회원 관리 예제**

- 회원 레포지토리 테스트 케이스 작성
    
    ⇒ 테스트는 깊은 내용 따로 공부 필요.
    
    ⇒ assetThat 왜 사용안되는거야.
    

### **섹션4. 스프링 빈과 의존관계**

스프링 빈을 등록하는 2가지 방법

1. 컴포넌트 스캔과 자동 의존관계 설정.
    
    Controller, Service, Repository는 정형화된 패턴이다. Controller를 통해서 외부 요청을 받고 Service에서 비즈니스 로직을 만들고, 리포지토리에서 데이터를 저장한다. 때문에 각 카테고리마다 어노테이션을 붙여준다. 이렇게 설정해놓으면 스프링이 뜰 때 이 세가지를 다 가지고 올라온다.
    
    ```java
    @Autowired
    public MemberController(MemberService memberService) {  
    	this.memberService = memberService;
    	}
    	// @AutoWired가 MemberController가 생성이 될때 스프링 빈에 등록되어 있는 Member Service 객체를
    	// 가져와서 넣어준다. 이것이 의존관계를 주입하는 방법이다.
    ```
    
    - 컴포넌트 스캔?? → @Service, Repository, Contoller안에 @Component가 들어가있다.
    - @Component annotation이 있으면 스프링 빈으로 자동등록된다. ⇒ 이것이 컴포넌트 스캔.
    - 즉 컴포넌트와 관련된 annotation이 있으면 스프링이 객체를 생성해서 스프링 컨테이너에 등록을 해놓는다.
    - @Autowired는 각 카테고리 사이의 연관관계를 만들어준다. 그래서 각각이 서로 사용할 수 있게 만들어 준다.
        
        ⇒ @Autowired를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아 서 주입한다.
        
    - 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 하나만 등록해서 공유한다. 때문에 같은 스프링 빈이면 모두 같은 인스턴스이다.
2. 자바 코드로 직접 스프링 빈 등록하기.
    
    Config class를 만들어서 그안에 Bean을 등록한다.
    
    @Bean으로 스프링에 스프링 빈을 등록한다.
    
    ```java
    @Configuration
    public class SpringConfig {
    
      @Bean // 스프링 빈을 직접 등록하겠다는 뜻.
      public MemberService memberService() {
        return new MemberService(memberRepository()); // 스프링 빈에 등록된 Member repository를 넣어준다.
      }
      
      @Bean
      public MemberRepository memberRepository() { // MemberRepository는 인터페이스??
        return new MemoryMemberRepository() // MemoryMemberRepository는 구현체??
      }
    } // 이렇게 하면 스프링이 뜰때 Configuration을 읽고 스프링빈에 등록하라는 뜻으로 받아들인다.
    
    //Controller는 그대로 annotation과 Autowired를 사용한다.
    ```
    
    - DI(Defendency Injection)은 생성자 주입 ,
    `public MemberController(MemberService memberService)`
        
        필드주입
        
        @Autowired private MemberService memberSevice; // 별로 좋진않음.
        
        Setter 주입
        
        생성자 주입이 권장됨.
        
    
    스프링 컨테이너에 올라가는 것들만 Autowired 기능을 한다.
    
    ⇒ 정형화된 코드는 컴포넌트 스캔을 사용하고, **상황에 따라 구현클래스를 변경해야하면** 설정을 통해 스프링 빈으로 등록한다.
    
    ### **섹션5. 회원관리예제 - 웹 MVC 개발**
    
    1. 회원 웹 기능 - 등록
        
        회원가입을 누르면 /members/new로 들어간다.
        
        URL창에 엔터를 치는 것은 @GetMapping 방식이다.
        
        @PostMapping은 일반적으로 데이터를 폼에 넣어서 전달할 때 POST를 사용한다.
        
        @GetMapping는 조회할 때 사용한다.
        
        ```java
        @GetMapping("/members/new")
          public String createForm() {
            return "members/createMemberForm";
          }
        
          @PostMapping("/members/new")
          public String create(MemberForm form) {
            Member member = new Member();
            member.setName(form.getName());
        
            memberService.join(member);
            return "redirect:/"; // 회원가입이 끝났으므로 홈화면으로 감.
          }
        ```
        
        ⇒ URL은 같지만 get이냐 post냐에 따라서 다르게 매핑할 수 있다.
        
    2. 회원 웹 기능 - 조회
    
    ### **섹션6. 스프링 DB 접근기술**
    
    - 실무에서는 전부 데이터베이스에 데이터를 저장하고 관리한다.
    - JPA라는 기술을 쓰면 객체를 바로 DB에 쿼리없이 저장할 수 있다.
    - 순수 JDBC
        - 데이터베이스에 insert query, select query를 날려서 DB에 넣고 빼는 것을 해본다.
        - 개방-폐쇄 원칙(OCP) → 확장에는 열려있고 수정, 변경에는 닫혀있다.
        - 스프링DI를 사용하면 기존코드를 손대지 않고 설정만으로 구현클래스를 변경할 수 있다.
    - 단위테스트(자바코드만 사용, 심플하게) vs 통합테스트(DB까지 연동함)
    - **스프링 JDBCTemplate**
        
        ⇒ SQL은 직접작성 해야하지만 반복코드를 대부분 제거해준다.
        
    
    ```java
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper());
        return result.stream().findAny();
      } // => jdbc 템플렛에서 쿼리를 날려서 그결과를 memberRowMapper를 이용해서 매핑을 하고 그 값을
    	  // 리스트로 받아서 옵셔널로 바꿔서 반환한다.
    ```
    
    - **JPA**
        - jpa에 데이터를 집어넣으면 jpa가 중간에서 DB에 sql을 날리고 DB에서 데이터를 가져오는 것을 해준다.
        - JPA는 인터페이스. 거의 JPA 인터페이스에 hybernate만 사용한다.
        - JPA는 orm기술. object, relational, mapping
        - JPA를 사용하기 위해서는 EntityManager라는 것을 주입받아야한다.
    
    - **스프링데이터 JPA**
        - 순수 jdbc 기술은 쿼리하나하나의 코드량이 많다.
        - 스프링 jdbc템플릿은 반복되는 코드가 줄어들지만 sql을 직접 작성해야한다.
        - jpa는 쿼리를 작성할 필요가 없다.
        - 스프링 데이터 jpa는 구현 클래스를 작성할 필요없이 인터페이스만으로 개발이 끝난다.