SELECT	*
FROM		employee;


-- LENGTH(): 바이트 수 세기
-- 한글은 한 글자 당 3바이트라 글자 수 * 3이 나옴
SELECT	emp_name,
			LENGTH(emp_name)
FROM		employee;


-- UPPER(): 문자열을 대문자로
-- LOWER(): 문자열을 소문자로
SELECT	UPPER('hi'),
			LOWER('HELLO');
			
-- TRIM(): 양쪽 공백 제거
-- LTRIM(): 왼쪽 공백 제거
-- RTRIM(): 오른쪽 공백 제거
SELECT	LENGTH(TRIM('    BFBFBFB    ')),
			LENGTH(LTRIM('    BFBFBFB    ')),
			LENGTH(RTRIM('    BFBFBFB    ')),
			LENGTH(LTRIM(RTRIM('    BFBFBFB    ')));


-- LPAD(): 문자열이 정해진 길이가 될때 까지 주어진 문자를 왼쪽에 채움
-- RPAD(): 문자열이 정해진 길이가 될때 까지 주어진 문자를 오른쪽에 채움
SELECT	LPAD('5', 3, '#'),
			LENGTH(LPAD('5', 3, '#')),
			RPAD('5', 3, '#'),
			LENGTH(RPAD('5', 3, '#'));
			
SELECT 	EMAIL,
			LENGTH(EMAIL),
			LPAD(EMAIL, 20, '#')
FROM		employee;


-- SUBSTRING(): 부분 문자열을 반환하는 함수
-- SUBSTRING(문자열, 시작, 갯수)
-- SUBSTRING(문자열, FROM 시작 FOR 갯수)
-- 인덱스는 1부터 시작
-- LEFT(문자열, 갯수): 왼쪽부터 갯수 만큼의 부분 문자열 반환
-- RIGHT(문자열, 갯수): 오른쪽부터 갯수 만큼의 부분 문자열 반환
-- SUBSTRING_INDEX(): 주어진 문자로 나눠진 문자열을 N번째까지 반환
SELECT	SUBSTRING('ABCDEFG', 1, 2),
			LEFT('ABCDEFG', 2),
			RIGHT('ABCDEFG', 2);
			
SELECT	SUBSTRING('Our Conversations All In Blue' FROM 5 FOR 12),
			SUBSTRING_INDEX('Our Conversations All In Blue', ' ', 1),
			SUBSTRING_INDEX('Our Conversations All In Blue', ' ', 3),
			SUBSTRING_INDEX('Our Conversations All In Blue', ' ', -1),
			SUBSTRING_INDEX('Our Conversations All In Blue', ' ', -2);


-- INSTR(): 부분 문자열의 인덱스 반환
SELECT	INSTR('HELLO WORLD', 'WORLD');


SELECT	EMAIL,
			INSTR(EMAIL, 'c.com'),
			LEFT(EMAIL, INSTR(EMAIL, '@') - 1)
FROM		employee;


-- REPEAT(): 문자열 반복
SELECT	REPEAT('HI! ', 3);


-- REPLACE(): 문자열 교체
SELECT	REPLACE('오늘 점심은 햄버거', '햄버거', '국수');


SELECT	HIRE_DATE,
			LEFT(HIRE_DATE, 4),
			SUBSTRING_INDEX(HIRE_DATE, '-', 1)
FROM		employee;


SELECT	EMP_NO,
			CONCAT(LEFT(EMP_NO, 8), '******')
FROM		employee;


-- CAST(A AS B): A를 B로 캐스팅
SELECT	SUBSTRING(EMP_NO, 1, 6),
			SUBSTRING(EMP_NO, 8, 7),
			CAST(SUBSTRING(EMP_NO, 1, 6) AS INT) + CAST(SUBSTRING(EMP_NO, 8, 7) AS INT)
FROM		employee;


USE SQLDB;
SELECT	*
FROM		USERTBL;
SELECT	*
FROM		BUYTBL;

SELECT	AVG(AMOUNT),
			CAST(AVG(AMOUNT) AS SIGNED INTEGER)
FROM		BUYTBL;

SELECT	NUM AS 구매번호,
			CONCAT(CAST(PRICE AS VARCHAR(10)), ' * ', CAST(AMOUNT AS VARCHAR(10)), ' =') AS '총 금액',
			PRICE * AMOUNT AS '구매액'
FROM		BUYTBL;

/*
ABS(): 절댓값 반환
CEILING(): 크거나 같은 최소 정수
FLOOR(): 작거나 같은 최대 정수
ROUND(): 주어진 자릿수까지 반올림
TRUNCATE(): 주어진 자릿수 이하의 숫자 버림
GREATEST(): 최댓값 반환
LEAST(): 최솟값 반환
*/
SELECT	ABS(-30),
			CEILING(5.2),
			FLOOR(9.7),
			ROUND(8.78633, 2),
			ROUND(1758.78033, -2),
			TRUNCATE(8.78633, 2),
			TRUNCATE(1758.78033, -2),
			GREATEST(143, 66, 31, 9),
			LEAST(7, 1, 43, 3);
			

/*
NOW(): 현재 시간 반환(년월일 시분초)
SYSTDATE(): 현재 시간 반환(년월일 시분초)
CURDATE(): 현재 시간 반환(년월일)
CURTIME(): 현재 시간 반환(시분초)
ADDDATE(): 지정해주는 단위의 시간 더하기
DATEDIFF(): 시간 차이를 일수로 반환
SUBDATE(): 시간 간 뺄셈(년월일 시분초)
SUBTIME(): 시간 간 뺄셈(시분초)
YEAR(), MONTH(), DAY(), HOUR(), MINUTE(), SECOND(): DATE 값에서 특정 값 추출
WEEKDAY(): 요일을 숫자로 반환(월요일이 0)
DAYOFWEEK(): 요일을 숫자로 반환(월요일이 2)
*/
SELECT	NOW(),
			SYSDATE(),
			CURDATE(),
			CURTIME(),
			ADDDATE(CURDATE(), INTERVAL 30 YEAR),
			ADDDATE(CURDATE(), INTERVAL 22 MONTH),
			ADDDATE(CURDATE(), INTERVAL 2 DAY),
			SUBDATE(NOW(), INTERVAL 30 DAY),
			SUBTIME(NOW(), '13:00:00');

			
USE TESTDB;
SELECT	HIRE_DATE,
			CAST(HIRE_DATE + 1 AS DATE)
FROM 		EMPLOYEE;


-- Q) 입사일을 기준으로 근속년수가 30년이 되는 일자를 검색한다면?
SELECT	HIRE_DATE,
			ADDDATE(HIRE_DATE, INTERVAL 30 YEAR)
FROM EMPLOYEE;


-- Q) 오늘 날짜를 기준으로 근속년수가 30년이상인 사원의 모든 정보를 검색한다면?
SELECT	EMP_NAME,
			HIRE_DATE,
			TRUNCATE(DATEDIFF(CURDATE(), HIRE_DATE) / 365, 0) AS '근속년수'
FROM		EMPLOYEE
WHERE		DATEDIFF(CURDATE(), HIRE_DATE) / 365 > 30;


SELECT	HIRE_DATE,
			CAST(YEAR(HIRE_DATE) AS CHAR)
FROM		EMPLOYEE;


-- DDL
DROP TABLE COUPON_TBL;
CREATE TABLE COUPON_TBL(
	CREATE_AT	DATE,
	END_AT		DATE
);

SELECT	*
FROM		COUPON_TBL;


-- DML
INSERT INTO COUPON_TBL(CREATE_AT, END_AT)
VALUES(NOW(), ADDDATE(NOW(), INTERVAL 7 DAY));

/*
IF(): 삼항연산자 같은 거
CASE ~ WHEN ~ THEN ~ ELSE ~ END
*/
SELECT	IF(100 > 200, 'TRUE', 'FALSE');
SELECT	CASE 100 > 200
				WHEN TRUE THEN 'TRUE'
				WHEN FALSE THEN 'FALSE'
				ELSE '??'
			END AS `구분`;


-- Q) 부서번호가 50번인 사원의 이름, 주민번호, 성별 검색한다면?			
SELECT	EMP_NAME,
			EMP_NO,
/*
			IF(SUBSTRING(EMP_NO, 8, 1) IN ('1', '3'), '남자', '여자') AS '성별',
*/
/*
			CASE SUBSTRING(EMP_NO, 8, 1)
				WHEN '1' THEN '남자'
				WHEN '3' THEN '남자'
				WHEN '2' THEN '여자'
				WHEN '4' THEN '여자'
				ELSE '?'
			END AS '성별',
*/
			CASE SUBSTRING(EMP_NO, 8, 1) / 2 = 0
				WHEN TRUE THEN '남자'
				WHEN FALSE THEN '여자'
				ELSE '?'
			END AS '성별',
/*
			CASE
				WHEN SUBSTRING(EMP_NO, 8, 1) IN ('1', '3') THEN '남자'
				WHEN SUBSTRING(EMP_NO, 8, 1) IN ('2', '4') THEN '여자'
				ELSE '?'
			END AS '성별',
*/
			DEPT_ID
FROM		EMPLOYEE
WHERE		DEPT_ID = 50;


-- Q) 사원테이블에서 직급(JOB_ID) 이 'J4' 사원의 이름, 사번, 사수번호(MGR_ID) 검색한다면?
-- 조건) 사수번호가 없는 사원 MGR_ID 컬럼에 '관리자' 출력
SELECT	EMP_NAME,
			EMP_ID,
			-- IF(MGR_ID = '', '관리자', MGR_ID) AS 'MGR_ID'
			IF(CHAR_LENGTH(MGR_ID) <> 0, MGR_ID, '관리자') AS 'MGR_ID'
FROM		employee
WHERE		JOB_ID = 'J4';


-- Q) 급여등급을 나눠보고 싶다
-- 300 이하면 초급, 400 이하면 중급, 초과하면 고급
-- 사원번호, 이름, 급여, 급여등급 검색한다면?
SELECT	EMP_ID,
			EMP_NAME,
			SALARY,
			CASE
				WHEN SALARY <= 3000000 THEN '초급'
				WHEN SALARY <= 4000000 THEN '중급'
				ELSE '고급'
			END AS '급여등급'
FROM		employee;


/*
COUNT(): 컬럼의 갯수 세기
MIN(): 컬럼의 최솟값 반환
MAX(): 컬럼의 최댓값 반환
AVG(): 컬럼의 평균값 반환
SUM(): 컬럼의 합계 반환
*/
SELECT	COUNT(*),
			COUNT(BONUS_PCT),
			COUNT(IFNULL(BONUS_PCT, 0)),
			MIN(SALARY),
			MAX(SALARY),
			AVG(SALARY),
			SUM(SALARY)
FROM		employee;

/*
ORDER BY: 정렬
ORDER BY [기준컬럼 | 표현식 | 컬럼 인덱스 | 컬럼 별칭] ASC | DESC
*/
SELECT	EMP_ID,
			EMP_NAME,
			SALARY AS S,
			CASE
				WHEN SALARY <= 3000000 THEN '초급'
				WHEN SALARY <= 4000000 THEN '중급'
				ELSE '고급'
			END AS '급여등급'
FROM		employee
-- ORDER BY SALARY DESC;
-- ORDER BY 3 DESC;
ORDER BY S DESC;