# Chapter 6. API URL의 설계 & 프로젝트 세팅

ERD 설계 → API 설계

1. API End point 설계
2. 요청 데이터, 응답 데이터 설계

**API란?**

Application Programming Interface

Application을 Programming할 때 사용되는 인터페이스

애플리케이션을 프로그래밍 할 때 보다 쉽게 할 수 있게 해주는 도구들

**REST API**

REST(Representational State Transfer), HTTP 기반 웹서비스 아키텍쳐

HTTP 메서드와 자원을 이용해 서로 간의 통신을 주고받음

**<HTTP 메소드>**

1. GET : 조회
2. POST : 생성
3. PUT : 갱신(전체)
4. PATCH : 갱신(일부)
5. DELETE : 삭제

**Restful API Endpoint의 설계**

1. URI에 동사 포함 X
2. URI에서 단어의 구분은 -(하이픈) 사용
3. 자원은 기본적으로 복수형으로 표현
4. 단 하나의 자원을 명시적으로 표현하기 위해서는 /users/id와 같이 식별 값을 추가로 사용
5. 자원 간 연관 관계가 있을 경우 URI에 표현



**로그인, 회원가입 API**

POST /users/login - 로그인

POST /users - 회원가입

**리소스 간 연관관계**

N:M관계에서는 비즈니스 로직상 더 중요한 대상을 계층 관계에서 앞에 두는 방법을 사용

ex) /articles/hash-tag

### 세부적인 API 설계

- **path variable**

  서버에 단 하나, 특정 대상을 식별하는 데이터를 넘길 때

  GET /users/articles/{articleId} → {}부분이 path variable 의미

- **Query String**

  1개 혹은 여러 개의 데이터를 조회하고자 할 때(GET)

  GET /users/articles?name=umc

  GET /users/articles?name=umc&owner=ddol

  !! 쿼리 스트링은 API 엔드포인트에 포함되지 않음 ! 엔드 포인트는 GET/users/articles로 설계

- **Request Body**

  url에 노출되지 않고 request body에 해당 데이터를 담을 수 있음(json 형태)

- Request Header

  전송에 관련된 기타 정보들이 담기는 부분

  body에 담기는 데이터의 형식 등