show databases;
use webdb;
select version(), current_date(), now() from dual;

-- 수학 함수도 사용할 수 있다.(사칙 연산도 된다)
select sin(pi() / 4), 1+2*3-4/5 from dual;

-- 대소문자 구분 안한다.
sELECT VERSION(), current_dAtE(), Now() froM dUAl;

-- table 생성: DDL
create table pet(
   name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
show tables;
desc pet;

-- table 삭제: DDL
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('성탄이','김상준','dog','m','2019-12-25',null);

-- select: DML(R)
select * from pet;

-- update: DML(U)
update pet set name='성타니' where name='성탄이';

-- delete : DML(D)
delete from pet where name='성타니';

-- load data
load data local infile '/Users/jay/pet.txt' into table pet;

select * from pet;

select owner from pet where name='Bowser';

-- 문2) 1998년 이후에 태어난 애들은?
select * from pet where birth >= '1998-01-01';

-- 문3) 종이 뱀이거나 새인 애들은?
select * from pet where species = 'snake' or species = 'bird';

-- 문4) order by ~ [asc]
select name, birth order by birth asc;

-- 문5) order by ~ [asc]
select name, birth order by birth desc;

select name, birth, death where death is not null;

select name from pet where name like '%b';
select name from pet where name like '%fy';
select name from pet where name like '%w%';

-- 예8) 집계 : count
