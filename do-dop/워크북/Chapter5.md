## Join이란?

SQL 기본 문법 중 하나로 두 개의 테이블을 엮어서 원하는 데이터를 추출.

서로 다른 각각의 테이블 속 데이터를 동시에 보여주려고 할 때 사용 !

join을 위해서는 기본키(Primary key, PK)와 외래키(Foreign key, FK) 관계로 맺어져야 하고, 이를 일대다 관계라 한다.

- SELECT문
    - FROM : 테이블 선택(MEMBER)
    - WHERE : 테이블 필터링(gender = ‘man’)
    - GROUP BY : 테이블 그룹화
    - ORDER BY : 테이블 정렬

### 1. INNER JOIN(내부 조인)

가장 많이 사용 , 두 테이블 모두 데이터가 있어야 한다.

```sql
SELECT <열 목록>
FROM <첫 번째 테이블>
    INNER JOIN <두 번째 테이블>
    ON <조인 조건>
[WHERE 검색 조건]
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/d66bcd37-0d33-40e0-a948-c1f44ec8629f/Untitled.png)

### 2. OUTER JOIN(외부 조인)

한쪽에만 데이터가 있어도 결과가 나온다.

```sql
SELECT <열 목록>
FROM <첫 번째 테이블(LEFT 테이블)>
    <LEFT | RIGHT | FULL> OUTER JOIN <두 번째 테이블(RIGHT 테이블)>
     ON <조인 조건>
[WHERE 검색 조건]
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/84b5e728-cc9f-4065-8454-7c057c002e9c/Untitled.png)

### 3. CROSS JOIN(상호 조인)

한쪽 테이블의 모든 행과 다른 쪽 테이블의 모든 행을 조인시킨다.

```sql
SELECT *
FROM <첫 번째 테이블>
    CROSS JOIN <두 번째 테이블>
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/3e204a93-b350-47d1-81b7-1e303ea7cf43/Untitled.png)

### 4. SELF JOIN(자체 조인)

자기 자신과 조인하므로 1개의 테이블 사용

```sql
SELECT <열 목록>
FROM <테이블> 별칭A
    INNER JOIN <테이블> 별칭B
[WHERE 검색 조건]
```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/cc350e70-9365-4a7e-93dc-6ba3540804ec/Untitled.png)

## 서브 쿼리(SubQuery)란?

하나의 SQL문 안에 포함되어 있는 또 다른 SQL문(SELECT문)이다.

메인쿼리가 서브쿼리를 포함하는 종속적 관계

```sql
//메인 쿼리
SELECT*
FROM db_table
WEHER table_fk IN (
	//서브 쿼리
	SELECT table_fk FROM db_table_other WHERE..
	)
```

**메인 쿼리**(부모 쿼리, 외부 쿼리) - 서브쿼리 컬럼 사용 불가

**서브 쿼리**(자식 쿼리, 내부 쿼리) - 메인쿼리 컬럼 사용 가능

<서브 쿼리 실행 조건>

1. 서브 쿼리는 괄호()로 감싸서 사용한다.
2. 서브 쿼리는 SELECT문으로만 작성 가능
3. 괄호가 끝나고 ; 사용 안 함
4. ORDER BY를 사용할 수 없다.

<서브 쿼리 사용 가능한 곳>

1. SELECT
2. FROM
3. WHERE
4. HAVING
5. ORDER BY
6. INSERT문의 VALUES 부분 대체
7. UPDATE문의 SET 부분 대체

<서브쿼리 종류>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/f6d0f2a7-4a22-4b1e-a5ff-200599919429/Untitled.png)

<위치에 따라 사용되는 서브쿼리>

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/e46c0e25-4664-420c-8901-f45feb53d136/Untitled.png)

## 워크북 내용 정리

<aside>
🌟 **TIP)**

***커뮤니티 성격의 app의 경우 신고, 차단 기능이 없을 경우 reject를 받아 런칭이 안됩니다.***

</aside>

query를 작성할 떄 차단 기능과 신고 기능을 처음부터 고려하여 작성 !!

### Paging(페이징)

데이터 베이스 자체에서 끊어서 가져오는 것

1. **Offset based 페이징**

   직접 페이지 번호를 찾아내어 이동하는 페이징

    ```sql
    select *     //*는 모든 열을 선택
    from book
    order by likes desc    //likes열을 내림차순으로 정렬
    limit 10 offset 0; //offset : 어디서부터 시작할 지
    
    select *
    from book order by likes desc
    limit 10 offset (n-1)*10 //n은 페이지 번호
    ```

   → 단점 : 사용자가 1페이지에서 2페이지로 넘어가려는 찰나, 게시글 6개가 추가되면 1페이지에서 봤던 게 또 보이는 문제 발생 !

2. **Cursor based 페이징**

   마지막으로 조회한 대상부터 가져옴

    ```sql
    SELECT *
    FROM book
    WHERE created_at < :cursor
    ORDER BY created_at DESC
    LIMIT 15;         //cursor로부터 15개를 가져옴
    ```

   → 인기순 정렬 같이 같은 값이 있을 수 있는 경우 정렬기준을 하나 더 세워야 함! (최신순은 밀리초까지 따지기에 겹칠일이 거의 없음)

   ## 🔥미션

    1. 내가 진행 중, 진행 완료한 미션을 모아서 보는 쿼리(페이징 포함)

        ```sql
        
        ```

    2. 리뷰 작성하는 쿼리

        ```sql
        
        ```

    3. 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)

        ```sql
        
        ```

    4. 마이 페이지 화면 쿼리