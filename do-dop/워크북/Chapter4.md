## SQL(Structured Query Language)

SQL은 관계형 데이터베이스를 다룰 때 사용되는 표준언어.

관계형 데이터베이스는 데이터를 표로 표시함

RDBMS는 관계형 데이터베이스를 관리하는 시스템이며, MySQL은 대표적인 RDBMS이다.

## MySGL 기초 문법

- 데이터베이스와 테이블 생성

    ```sql
    CREATE DATABASE [데이터베이스 명]; // 데이터베이스 생성
    DROP DATABASE [데이터베이스 명]; // 데이터베이스 제거
    SHOW DATABASES; // 데이터베이스 목록
    USE [데이터베이스 명]; //데이터베이스 선택, 사용
    ```

  데이블에 접급하기 위해서 데이터베이스를 USE로 선택해준다.

  테이블을 만들 떄, 칼럼과 데이터타입을 설정해줘야함

    ```sql
    USE [데이터베이스명];
    CREATE TABLE [테이블명]
        [칼럼1] [데이터타입] [설정(기본 형식)],
        [칼럼2] [데이터타입] [설정(기본 형식)],
        [칼럼3] [데이터타입] [설정(기본 형식)]
    )
    ```

- 데이터의 CRUD(Create, Read, Update, Delete)

  테이블에 접근하므로 데이터베이스 선택

  DESC문으로 테이블의 구조 확인

    ```sql
    USE mydb01
    DESC topic // 테이블 구조 정보 (describe)
    ```

  <Create 생성>

    ```sql
    CREATE TABLE 테이블명 (
         컬럼명 데이터형,
         컬럼명 데이터형,
                  ˙˙˙
         PRIMARY KEY(기본키가 될 필드명)    //NULL이 되면 안됨
    );
    ```

  테이블에 새로운 컬럼을 추가하는 기본 문법

    ```sql
    ALTER TABLE [테이블명] ADD COLUMN [추가할 컬럼명] [추가할 컬럼 데이터형]
    ```

  테이블의 컬럼 타입을 변경하는 문법

    ```sql
    ALTER TABLE [테이블명] MODIFY COLUMN [변경할 컬럼명] [변경할 컬럼 타입]
    ```

  테이블 필드(열) 삭제

    ```sql
    ALTER TABLE [테이블명] DROP [컬럼명];
    ```

  테이블에 레코드(행) 추가

    ```sql
    INSERT INTO [table name] VALUES (value1, value2, value3…);
    ```

  테이블의 레코드(행) 선택

    ```sql
    SELECT * FROM [table];
    ```

  테이블의 레코드(행) 내용 수정

    ```sql
    UPDATE [table] SET [column]=[value] WHERE [condition];
    ```

  테이블의 레코드(행) 삭제

    ```sql
    DELETE FROM [table] WHERE [condition];
    ```


## 데이터 모델링

## ERD(Entity Relationship Diagram)

데이터베이스 구조를 시각적으로 한 눈에 알아보기 위해 사용됨.

“Entity”, “Relationship”, “Attribute”

### #Entity(개체)

- 관리하고자 하는 정보의 실체(데이터베이스 설계 시 테이블이 Entity로 정의됨), 객체
- Entity는 하나 이상의 식별자 UID를 지녀야 함.
- Weak Entity : 단독으로 존재할 수 없고, 다른 개체에 의존

### #Attribute(속성)

- Entity를 구성하고 있는 구성 요소, 객체가 가지는 속성
- 데이터 타입을 반드시 명시해줘야 함.
- Key Attribute : 다른 객체들과 중복되지 않은 고유한 값을 가짐
- Composite Attribute : 독립적인 Attribute들이 모여 생성
- Multi-Valued Attribute : 하나의 Attribute가 여러개의 값을 가짐
- Derived Attribute : 다른 Attribute가 갖고 있는 값으로부터 유도된 속성

### #Relationship

- Entity간의 관계, 두 Entity간에 선을 긋고, 관계 명칭 기록
- 선택사항 표시 : 점선(선택적인 사항), 실선(필수적인 사항)
- 관계형태 표시 : 삼치창 모양(하나 이상), 단선(하나)
- 1:1, 1:다수, 다수:다수

## 워크북

ERD는 언제 ?

<aside>
🌟 **ERD는 프로젝트 시작과 동시에 설계하는 것이 좋습니다.**

물론, 기획과 디자인이 어느 정도 나와 개발자가 작업을 시작할 수 있는 시점을 말하는 것입니다.

</aside>

<aside>
❗ **모든 팀원이 인지하는 데이터베이스는 동일해야 한다는 것입니다.**

**각자 자기 마음대로 DB를 설계하고, 작업 후 나중에 합치는 행위는 매우 좋지 않습니다.**

꼭 처음에 빠르게 ERD를 설계하여,
**모두가 공통된 데이터베이스에 대해 인지한 후 작업을 하는 것**이 좋습니다!

</aside>