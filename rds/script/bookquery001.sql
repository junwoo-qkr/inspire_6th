-- SQL 주석
SELECT * FROM employee;


-- 데이터베이스 변경
USE testdb;


-- 데이터베이스의 목록
SHOW DATABASES;


-- 데이터베이스의 설명
DESC country;


/*
SELECT: 데이터 검색 시 사용
*[]: optional
SELECT       DISTINCT | 컬럼명 | * | 표현식 및 함수 | [AS] 별칭
FROM         테이블 이름
[WHERE]      행의 제한
[GROUP BY]   데이터를 그룹으로 묶을 때
[HAVING]     그룹에 대한 조건
[ORDER BY]   정렬(ASC, DESC)

별칭: 문자, 특수문자, 공백 사용 가능
'' or `` 써야함

키워드는 대소문자를 가리지 않음
데이터는 대소문자 구분(인코딩에 따라 다름)
*/
SELECT * FROM job;

SELECT * FROM department;

SELECT	EMP_ID,
		 	EMP_NAME
FROM employee;


-- 부서 번호가 90번인 사원 검색
SELECT	*
FROM		employee
WHERE		DEPT_ID = '90';


-- 별칭 붙이기
SELECT	EMP_NAME,
			SALARY,
			(SALARY + (SALARY * BONUS_PCT)) * 12 AS `(연봉)`
FROM		employee;


-- NULL 처리: IFNULL(A, B): A가 NULL이면 B 반환 | NULLIF(A, B): A와 B가 같으면 NULL
SELECT	EMP_NAME,
			SALARY,
			(SALARY + (SALARY * IFNULL(BONUS_PCT, 0))) * 12 AS `(연봉)`
FROM		employee;


-- DISTINCT: 중복 값을 한번만 출력
SELECT	DISTINCT DEPT_ID
FROM		employee;


-- WHERE: 필드 필터링
SELECT	*
FROM		employee
WHERE		DEPT_ID = 90
OR			SALARY >= 4000000;


-- CONCAT(): 문자열 연결
SELECT	CONCAT('HELLO', ' WORLD');

SELECT	CONCAT(EMP_NAME, '님의 급여는 ', SALARY, '원 입니다.') AS 급여
FROM employee;


-- 급여가 3500000 이상 5500000 이하인 사원의 이름, 급여, 직급 검색
SELECT	EMP_NAME,
			SALARY,
			JOB_ID
FROM		employee
WHERE		SALARY >= 3500000 AND SALARY <= 5500000;


-- BETWEEN~AND: 경계값을 포함해서 구간 구하기
SELECT	EMP_NAME,
			SALARY,
			JOB_ID
FROM		employee
WHERE		SALARY BETWEEN 3500000 AND 5500000;


/*
LIKE, NOT LIKE: 패턴 검색

와일드카드
%: 하나 이상의 문자열
_: 하나의 문자열
*/
SELECT	*
FROM		employee
WHERE		EMP_NAME LIKE '김%';

SELECT	*
FROM		employee
WHERE		EMP_NAME NOT LIKE '김%'; 


-- 와일드카드를 실제 문자로 사용하려면 앞에 역슬래시 붙이기
SELECT	*
FROM		employee
WHERE		EMAIL LIKE '___\_%'; 


-- NULL 검색: 연산- (IS NULL, IS NOT NULL), 함수-(ISNULL())
SELECT	*
FROM 		employee
WHERE		DEPT_ID IS NULL;

SELECT	*
FROM 		employee
WHERE		ISNULL(DEPT_ID);  -- = IS NULL

SELECT	*
FROM 		employee
WHERE		ISNULL(DEPT_ID) = 0;  -- = IS NOT NULL


-- IN, OR 연산자
SELECT	*
FROM 		employee
WHERE		DEPT_ID = '60' OR DEPT_ID = '90';

SELECT	*
FROM 		employee
WHERE		DEPT_ID IN('60', '90');