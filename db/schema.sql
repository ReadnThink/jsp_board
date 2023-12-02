create schema jspboard default character set utf8 collate utf8_unicode_ci;
use jspboard;

CREATE Table article (
                         id bigint(20) not null auto_increment,
                         createDate datetime not null,
                         updateDate datetime not null,
                         title varchar(200) not null,
                         content longtext not null,
                         primary key(id)
);

insert into article(createDate, updateDate,title, content)
values (now(),now(),'제목1','내용1');

insert into article(createDate, updateDate,title, content)
values (now(),now(),'제목2','내용2');

insert into article(createDate, updateDate,title, content)
values (now(),now(),'제목3','내용3');

select * from article;