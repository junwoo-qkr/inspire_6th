USE tabledb;
-- 문제 1번
DROP TABLE IF EXISTS customers;
CREATE TABLE CUSTOMERS (
	CNO INT(5) PRIMARY KEY,
	CNAME VARCHAR(10) NOT NULL,
	ADDRESS VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(20) NOT NULL,
	PHONE VARCHAR(20) NOT NULL
);


DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
	ORDERNO INT(10) PRIMARY KEY,
	ORDERDATE DATE DEFAULT SYSDATE() NOT NULL,
	ADDRESS VARCHAR(50) NOT NULL,
	PHONE VARCHAR(20) NOT NULL,
	STATUS VARCHAR(20) NOT NULL CHECK(STATUS IN ('결제완료', '배송중', '배송완료')),
	CNO INT(5) NOT NULL REFERENCES CUSTOMERS(CNO)
);


DROP TABLE IF EXISTS PRODUCTS;
CREATE TABLE PRODUCTS (
	PNO INT(5) PRIMARY KEY,
	PNAME VARCHAR(20) NOT NULL,
	COST INT(8) DEFAULT 0 NOT NULL,
	STOCK INT(5) DEFAULT 0 NOT NULL
);


DROP TABLE IF EXISTS ORDERDETAIL;
CREATE TABLE ORDERDETAIL (
	ORDERNO INT(10),
	PNO INT(5),
	QTY INT(5) DEFAULT 0,
	COST INT(8) DEFAULT 0,
	PRIMARY KEY(ORDERNO, PNO),
	FOREIGN KEY(ORDERNO) REFERENCES ORDERS(ORDERNO),
	FOREIGN KEY(PNO) REFERENCES PRODUCTS(PNO)
);


-- 문제 2번
INSERT INTO products
VALUES
	(1001, '삼양라면', 1000, 200),
	(1002, '새우깡', 1500, 500),
	(1003, '월드콘', 2000, 350),
	(1004, '빼빼로', 2000, 700),
	(1005, '코카콜라', 1800, 550),
	(1006, '환타', 1600, 300);
SELECT * FROM products;


-- 문제 3번
INSERT INTO customers
VALUES
(101, '김철수', '서울 강남구', 'cskim@naver.com', '899-6666'),
(102, '이영희', '부산 서면', 'yhlee@empal.com', '355-8882'),
(103, '최진국', '제주 동광양', 'jkchoi@gmail.com', '852-5764'),
(104, '강준호', '강릉 홍제동', 'jhkang@hanmail.com', '559-7777'),
(105, '민병국', '대전 전민동', 'bgmin@hotmail.com', '559-8741'),
(106, '오민수', '광주 북구', 'msoh@microsoft.com', '542-9988');
SELECT * FROM customers;


-- 문제 4번
INSERT INTO orders(ORDERNO, ORDERDATE, ADDRESS, PHONE, STATUS, CNO)
VALUES(
	1, 
	SUBDATE(SYSDATE(), 3), 
	'서울 강남구', 
	'899-6666', 
	'결제완료', 
	(SELECT CNO FROM customers WHERE CNAME = '김철수')
);


INSERT INTO orderdetail(ORDERNO, PNO, QTY, COST)
VALUES(
	1,
	(SELECT PNO FROM products WHERE PNAME = '삼양라면'),
	50,
	1000
);


SELECT * FROM orders;
SELECT * FROM orderdetail;


-- 문제 5번
UPDATE	products
SET		STOCK = 150
WHERE		PNAME = '삼양라면';


-- 문제 6번
INSERT INTO orders(ORDERNO, ORDERDATE, ADDRESS, PHONE, STATUS, CNO)
VALUES(
	2, 
	SUBDATE(SYSDATE(), 2), 
	'부산 수영구', 
	'337-5000', 
	'결제완료', 
	(SELECT CNO FROM customers WHERE CNAME = '이영희')
);


INSERT INTO orderdetail(ORDERNO, PNO, QTY, COST)
VALUES
	(
		2,
		(SELECT PNO FROM products WHERE PNAME = '새우깡'),
		100,
		1500
	),
	(
		2,
		(SELECT PNO FROM products WHERE PNAME = '월드콘'),
		150,
		2000
	);
	
SELECT * FROM orders;
SELECT * FROM orderdetail;


-- 문제 7번
UPDATE	products
SET		STOCK = 400
WHERE		PNAME = '새우깡';

UPDATE	products
SET		STOCK = 200
WHERE		PNAME = '월드콘';

SELECT * FROM products;


-- 문제 8번
INSERT INTO orders(ORDERNO, ORDERDATE, ADDRESS, PHONE, STATUS, CNO)
VALUES(
	3, 
	SUBDATE(SYSDATE(), 1), 
	'광주 북구', 
	'652-2277', 
	'결제완료', 
	(SELECT CNO FROM customers WHERE CNAME = '오민수')
);


INSERT INTO orderdetail(ORDERNO, PNO, QTY, COST)
VALUES
	(
		3,
		(SELECT PNO FROM products WHERE PNAME = '빼빼로'),
		100,
		2000
	),
	(
		3,
		(SELECT PNO FROM products WHERE PNAME = '코카콜라'),
		50,
		1800
	);
	
SELECT * FROM orders;
SELECT * FROM orderdetail;


-- 문제 9번
UPDATE	products
SET		STOCK = 600
WHERE		PNAME = '빼빼로';

UPDATE	products
SET		STOCK = 500
WHERE		PNAME = '코카콜라';

SELECT * FROM products;


-- 문제 10번
SELECT	O.ORDERDATE
			, C.CNAME
			, O.ADDRESS
			, O.PHONE
			, O.STATUS
			, P.PNAME
			, OD.COST
			, OD.QTY
			, OD.COST * OD.QTY
FROM		orders O
JOIN		customers C ON (O.CNO = C.CNO)
JOIN		orderdetail OD ON (O.ORDERNO = OD.ORDERNO)
JOIN		products P ON (OD.PNO = P.PNO);


-- 문제 11번
SELECT	O.ORDERDATE
			, SUM(OD.COST * OD.QTY)
FROM		orders O
JOIN		orderdetail OD ON (O.ORDERNO = OD.ORDERNO)
GROUP BY	O.ORDERDATE;


-- 문제 12번
INSERT INTO products
VALUES(1007, '목캔디', 3000, 500);

SELECT * FROM products;


-- 문제 13번
INSERT INTO orders(ORDERNO, ORDERDATE, ADDRESS, PHONE, STATUS, CNO)
VALUES (
	4, 
	SYSDATE(), 
	'제주 동광양', 
	'352-4657', 
	'결제완료', 
	(SELECT CNO FROM customers WHERE CNAME = '최진국')
);

INSERT INTO orderdetail(ORDERNO, PNO, QTY, COST)
VALUES (4, (SELECT PNO FROM products WHERE PNAME = '목캔디'), 200, 3000);

UPDATE	products P
JOIN		orderdetail OD ON (P.PNO = OD.PNO)
SET		P.STOCK = P.STOCK - OD.QTY
WHERE		OD.ORDERNO = 4;

SELECT	O.ORDERDATE
			, C.CNAME
			, O.ADDRESS
			, O.PHONE
			, O.STATUS
			, P.PNAME
			, OD.COST
			, OD.QTY
			, OD.COST * OD.QTY
FROM		orders O
JOIN		customers C ON (O.CNO = C.CNO)
JOIN		orderdetail OD ON (O.ORDERNO = OD.ORDERNO)
JOIN		products P ON (OD.PNO = P.PNO);