create table t97_notice(
	no number(5) primary key,
	id varchar2(5) not null,
	title varchar2(200) not null,
	content varchar2(4000) not null,
	reg_date date default sysdate,
	read_count number(5) default 0
);

drop table t97_notice purge;

alter table t97_notice
add constraint fk_notice_id foreign key(id)
references t97_member(id);

-- 글번호 시퀀스 생성
create sequence s97_notice_no;

-- 전체목록
select n.no, n.title, n.regDate, m.name
  from t97_notice n
 inner join t97_member m
    on n.id = m.id;		
    
-- 글상세조회
select n.id, n.no, n.title, n.content, n.regDate , m.name
  from t97_notice n
 inner join t97_member m
    on n.id = m.id
 where no = ?;    
 
-- 글등록
insert into t97_notice(no, id, title, content)
values(s97_notice_no.nextval, ?, ?, ?);

-- 글수정
update t97_notice
   set title = ?, content = ?
 where no = ?;
			
-- 글삭제
delete from t97_notice
 where no = ?;			
    