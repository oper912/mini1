-- 테이블 생성
create table t97_library(
    lib_no number(5) primary key,
    title varchar2(200) not null,
    content varchar2(4000),
    reg_date DATE default sysdate,
    read_count number(5) default 0,
    recomment_count number(5) default 0,
    id varchar2(5) not null
);

create table t97_attachment(
    no number(5) primary key,
    file_org_name varchar2(100) not nudll,
    file_system_name varchar2(100) not null,
    file_path varchar2(200) not null,
    file_size number(11) not null,
    lib_no number(5) not null
);
    
create table t97_recommend(
	lib_no number(5) not null,
	id varchar2(5) not null
);

select * from tab;
select * from t97_recommend;
select * from t97_library;
select * from T97_ATTACHMENT;

delete 
  from t97_library
 where lib_no = 2;

drop table t97_recommend purge;
drop table t97_library purge;
drop table t97_attachment purge;
-- 시퀀스 생성
create sequence s97_library_no;      -- 라이브러리테이블 시퀀스
create sequence s97_attachment_no;   -- 파일테이블 시퀀스

-- 자료실 글 입력
insert into tb_library(lib_no, title, content, id)
values(s97_library_no.nextval, '오버워치설치파일', '재밌게하세요', 'abc');

insert into tb_library(lib_no, title, content, id)
values(s97_library_no.nextval, '오버워치설치파일5', '재밌게하세요5', 'abc5');

insert into tb_attachment(no, file_path, file_size, file_name, lib_no)
values(s97_attachment_no.nextval, 'data/mini/', 1000, 'op.zip', 1);

insert into tb_attachment(no, file_path, file_size, file_name, lib_no)
values(s97_attachment_no.nextval, 'data/mini/', 1000, 'op2.zip', 1);

insert into tb_recommend(lib_no, id)
values(1,'abc');

s97_library_no
--------------------------

select * from tab;

drop sequence s97_library_no;
drop sequence s97_attachment_no;

select *
  from tb_attachment
 where lib_no = 1
 order by no;
 
update tb_board
   set title = '1', content = '1'
 where lib_no = 1;

delete
  from tb_library
 where lib_no = 1;
 
insert into tb_recommend(lib_no, id)
values (1, 'abc');

select lib_no, id
  from tb_recommend
 where lib_no = 1 and id = 'abc';
 
select lib_no, title, id, reg_date, read_count, recomment_count from tb_library;

-- 페이징하는법
select b.*
  from (
        select rownum rnum , a.*
          from (select *
                  from tb_library
                 order by lib_no) a
        )b
  where rnum between 1 and 5;
  
update t97_library
   set recomment_count = recomment_count+1
 where lib_no = 1
 
select S97_LIBRARY_NO.CURRVAL
  from dual;

select s97_library_no.currval
  from dual;
  
select s97_library_no.nextval, s97_library_no.currval
  from dual;
  
select l.*, m.name
  from t97_library l
  inner join t97_member m
     on l.id = m.id
  where name like '%홍%';
  
  select * from tab;
  
create table s_member(
    id varchar2(10),
    password varchar2(10)
);

insert into s_member(id, password)
values('oper912', 1234);

select * from s_member;