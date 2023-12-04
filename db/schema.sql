create schema jspboard default character set utf8 collate utf8_unicode_ci;
use jspboard;

-- article
CREATE Table article (
                         id bigint(20) not null auto_increment,
                         createDate datetime not null,
                         updateDate datetime not null,
                         title varchar(200) not null,
                         content longtext not null,
                         primary key(id)
);
ALTER TABLE article ADD COLUMN userId bigint(20) not null;
insert into article(createDate, updateDate,title, content)
values (now(),now(),'제목1','내용1');

-- user
CREATE TABLE user (
                      id bigint(20) not null auto_increment,
                      loginId varchar(20) not null UNIQUE,
                      loginPw varchar(100) not null,
                      username varchar(20) not null,
                      email varchar(100) not null UNIQUE,
                      createDate datetime not null,
                      updateDate datetime not null,
                      primary key(id)
);
INSERT INTO user(loginid, loginPw, username, email, createDate, updateDate)
values('test','test','test','test@test',now(),now());

select * from article;

-- 게시글의 두배씩 늘어나는 쿼리 (페이징 조회시 사용함)
-- INSERT INTO article(createDate, updateDate,title, content)
-- SELECT now(),now(), CONCAT('제목-', FLOOR(RAND()*100)),CONCAT('내용-',FLOOR(RAND()*100))
-- FROM article;

-- while문
DROP procedure IF EXISTS `loopInsert`;
DELIMITER $$
CREATE PROCEDURE loopInsert()
BEGIN
	DECLARE i INT DEFAULT 1;
    WHILE (i<=1000)
    DO
		INSERT INTO article(createDate, updateDate, title, content, userId)
        VALUES(now(),now(), CONCAT('제목-', i), CONCAT('내용-',i), (i % 10)+1);
	SET i=i+1;
END WHILE;
END $$
DELIMITER ;
CALL loopInsert();


SELECT RAND() * 100; -- 랜덤
SELECT CONCAT('제목-', FLOOR(RAND()*100));
SELECT ROUND(123.125, 2); -- 소수점에서 인자 이후로 반올림
SELECT TRUNCATE(123.125, 2); -- 제거
SELECT ceil(123.125); -- 올림
SELECT FLOOR(123.125); -- 버림