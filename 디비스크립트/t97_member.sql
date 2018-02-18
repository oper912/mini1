-- 회원정보 테이블 생성
create table t97_member(
	id varchar2(5) primary key,
	email varchar2(40) not null,
	password varchar2(20) not null,
	name varchar2(30) not null,
	phone_no varchar2(11) not null,
	pw_hint varchar2(40) not null,
	join_date date default sysdate,
	drop_date date,
	admin number(1) default 0,
	constraint uk_member_email unique(email)
);

-- 회원id용 시퀀스 생성
create sequence s97_member_id;


-- 회원가입
insert into t97_member(id, email, password, name, phone_no, pw_hint)
values(CONCAT('M',TO_CHAR(LPAD(s97_member_id.nextval,4,0))), 'aaa@naver.com', '1111', '홍길동', '01011112222', '비번힌트1');

insert into t97_member(id, email, password, name, phone_no, pw_hint)
values(CONCAT('M',TO_CHAR(LPAD(s97_member_id.nextval,4,0))), 'bbb@naver.com', '1234', '김철수', '01012345678', '비번힌트2');


-- 로그인
select id, admin
  from t97_member 
 where email = 'aaa@naver.com' and password = '1111' and drop_date is null;

-- 회원정보수정
update t97_member
   set name = ?, phone_no = ?, password = ?, pw_hint = ?
 where id = ?
 
 
 
--  
 
select * from t97_member;
select * from t97_library;

select m.name
  from t97_library l inner join t97_member m
    on l.id = m.id;