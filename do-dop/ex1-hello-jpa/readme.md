# <섹션 1 - JPA 소개>

  ## SQL 중심적인 개발의 문제점

  무한 반복, 지루한 코드

  필드 추가 시 쿼리 내용 다 고쳐야 함

  객체를 관계형 데이터베이스에 저장

  객체 → SQL 변환 → SQL/RDB

  DB에 저장할 객체에는 상속관계 사용 X

  <연관관계>

  객체는 참조를 사용

  테이블을 외래 키 사용

  Memver member = list.get(memberId);

  Team team = member.getTeam();

  객체는 자유롭게 객체 그래프를 탐색할 수 있어야 한다.

  객체를 **자바 컬렉션에 저장 하듯이
  DB에 저장할 수는 없을까?**

  ## JPA 소개

  ORM? Object-relational mapping(객체 관계 매핑)

  **JPA(애플리케이션과 JDBC 사이에서 동작)**

    - JDBC api 사용
    - 패러다임 불일치 해결(상속, 연관관계,

# <섹션 2 - JPA 시작하기>

  ## 데이터 베이스 방언

  JPA는 특정 데이터베이스에 종속 X

  hibernate.dialect.H2Dialect(H2방언 지원)

  ## JPA 구동 방식

  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/ac8bc510-6c09-43d7-a7c0-84e6e9f739c9/Untitled.png)

  ## 애플리케이션 개발

  EntityManagerFactory는 DB당 하나만 생성 !
  EntityManager는 쓰레드간에 공유 X(사용하고 버려야 한다.)

  **JPA의 모든 데이터 변경은 트렌젝션 안에서 실행 (트렌젝션이 뭔디?)**

    ```java
    //정석 코드(회원 등록)
    try {
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
    
        em.persist(member);  //member 저장
        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }
    emf.close();
    
    //회원 조회
    try {
        Member findMember = em.find(Member.class, 1L);
        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());
    
        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }
    emf.close();
    
    //회원 수정, persist로 저장하지 않아도 됨.
     try {
        Member findMember = em.find(Member.class, 1L);
        findMember.setName("HelloDodo");
    
        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }
    emf.close();
    
    //회원 삭제
    try {
        Member findMember = em.find(Member.class, 1L);
        em.remove(findMember);   //삭제
        tx.commit();
    } catch (Exception e) {
        tx.rollback();
    } finally {
        em.close();
    }
    emf.close();
    
    ```

  난 왜 추가가 안 되는 것인가…

    <JPQL>

  테이블이 아닌 **객체를 대상으로 검색하는 객체 지향 쿼리**

# <섹션 3 - 영속성 관리(내부 동작 방식)>

  ## 영속성 컨텍스트

  JPA에서 가장 중요한 2가지

    1. 객체와 관계형 DB 매핑하기
    2. **영속성 컨텍스트**

    - EntityManagerFactiory, EntityManager

  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/c61dbe8f-81ac-4c2b-a674-a3b4bc27a589/Untitled.png)

  entitymanager를 통해 영속성 컨텍스트 접근

  <Entity의 생명주기>

    1. **비영속(new/transient)**

       영속성 컨텍스트와 전혀 관계가 없는 **새로운** 상태

    2. **영속(managed)**

       영속성 컨텍스트에 **관리**되는 상태 em.persist(member) →DB에 아직 저장된 것이 아님!

    3. **준영속 (detached)**

       영속성 컨텍스트에 저장되었다가 **분리**된 상태

    4. **삭제 (removed)**

       **삭제**된 상태


    <영속성 컨텍스트의 이점>
    
    1. 1차 캐시
        
        1차 캐시에서 먼저 조회 → (없으면)DB 조회 → 1차 캐시에 저장
        
    2. 영속 엔티티의 동일성 보장
    3. 엔티티 등록 시, 트랜젝션을 지원하는 쓰기 지연
        
        persist때 INSERT SQL을 데이터베이스에 보내지않음. 
        
        commit() 하는 순간 보냄
        
    4. 엔티티 수정 (변경 감지)
        
        엔티티 수정 후 업데이트를 하는 함수가 따로 필요없음 
        
        `member.setName("zzzzz");`
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/4f4ab787-6730-40e4-82de-02e3feac7ce1/Untitled.png)
        
    5. 엔티티 삭제
    
    ## 플러시
    
    영속성 컨텍스트의 변경 내용을 DB에 반영(like commit)
    
    - 변경 감지
    
    <플러시 방법>
    
    - em.flush() - 직접 호출
    - 트랜잭션 커밋 - 플러시 자동 호출
    - JPQL 쿼리 실행 - 플러시 자동 호출
    
    ## 준영속 상태
    
    영속 상태의 엔티티가 영속성 컨텍스트에서 분리
    
    ## 정리

# <섹션 4 - 엔티티 매핑>

  ## 객체와 테이블 매핑

  ### 엔티티 매핑 소개

    - 객체와 테이블 매핑 : @Entity, @Table
    - 필드와 컬럼 매핑 : @Column
    - 기본 키 매핑 : @Id
    - 연관관계 매핑 : @ManyToOne, @JoinColumn

  ### @Entity

    - @Entity가 붙은 클래스는 JPA가 관리

  ### @Table

  ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/313cdb6b-4bed-4efa-8d3d-2f45399aa6d6/Untitled.png)

  ## 데이터베이스 스키마 자동 생성

    - DDL을 애플리케이션 실행 시점에 자동 생성
    - 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL생성(DDL?)
    - **hibernate.hbm2ddl.auto**


        | 옵션 | 설명 |
        | --- | --- |
        | create | 기존테이블 삭제 후 다시 생성 (DROP + CREATE) |
        | create-drop | create와 같으나 종료시점에 테이블 DROP |
        | update | 변경분만 반영(운영DB에는 사용하면 안됨) |
        | validate | 엔티티와 테이블이 정상 매핑되었는지만 확인 |
        | none | 사용하지 않음 |
    
    !! 주의점 !!
    
    운영 장비에는 절대 create, create-drop, update를 사용하면 안 된다. 
    
    ## 필드와 컬럼 매핑
    
    ### hibernate.hbm2ddl.auto
    
    | 어노테이션  | 설명 |
    | --- | --- |
    | @Column | 컬럼 매핑 |
    | @Temporal | 날짜 타입 매핑 |
    | @Enumerated | enum 타입 매핑 |
    | @Lob | BLOB, CLOB 매핑 |
    | @Transient | 특정 필드를 컬럼에 매핑하지 않음(매핑 무시) |
    
    ### @Enumerated
    
    **주의! ORDINAL 사용X**
    • EnumType.STRING: enum 이름을 데이터베이스에 저장
    
    ## 기본 키 매핑
    
    @Id : 직접할당
    
    @GeneratedValue : 자동할당
    
    권장하는 식별자 전략
    
    - 기본 키 제약 조건 : null 아님, 유일, 변하면 안된다.
    - 권장 : Long형ㅇ + 대체키 + 키 생성전략 사용
    
    ## 실전예제

# <섹션 5 - 연관관계 매핑 기초>
# <섹션 6 - 다양한 연관관계 매핑>