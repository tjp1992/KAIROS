DROP TABLE PLAYLIST;
DROP TABLE LIKELIST;
DROP TABLE SONG_REPORT;
DROP TABLE WEB_USER;
DROP TABLE DEL_WEB_USER;
DROP TABLE SONG;
DROP TABLE DELETED_SONG;
DROP TABLE PURCHASE_LOG;
DROP TABLE VOUCHER;
DROP TABLE ALBUM;
DROP TABLE LICENSED_ARTIST;
DROP TABLE INQ_ANS;
DROP TABLE INQUIRY;
DROP TABLE NOTICE;

-----------------------------------------
-- 회원 테이블 생성
-----------------------------------------
CREATE TABLE WEB_USER(
	USER_ID VARCHAR2(20) PRIMARY KEY,
    USER_PW VARCHAR2(20) NOT NULL,
    USER_NAME VARCHAR2(60) NOT NULL,
    USER_NICKNAME VARCHAR2(100) NOT NULL,
    PHONE VARCHAR2(11) NOT NULL,
    EMAIL VARCHAR2(40) NOT NULL,
    ADDRESS VARCHAR2(300) NOT NULL,
    EXPIRED_DATE DATE,
    CON_AGREE NUMBER
);

CREATE TABLE DEL_WEB_USER(
	USER_ID VARCHAR2(20) NOT NULL,
    USER_PW VARCHAR2(20) NOT NULL,
    USER_NAME VARCHAR2(60) NOT NULL,
    USER_NICKNAME VARCHAR2(100) NOT NULL,
    PHONE VARCHAR2(11) NOT NULL,
    EMAIL VARCHAR2(40) NOT NULL,
    ADDRESS VARCHAR2(300) NOT NULL,
    EXPIRED_DATE DATE ,
    CON_AGREE NUMBER
);
insert into web_user values ('pjyub1379','@@pkyu0662','박종엽','엽1','01022222222','pjyub1379@naver.com','렛잇고',sysdate+30,'1');
insert into web_user values ('pjyub1379','@@pkyu0662','박종엽','엽1','01022222222','pjyub1379@naver.com','렛잇고',sysdate,'1');
select * from DEL_web_user;
select * from web_user;
update web_user set user_id='pjyub1379',user_pw='@@pkyu0662',user_name='박종협' where user_id='pjyub1379';
select * from web_user where user_name='박종엽' and email='pjyub1379@naver.com';
delete from web_user where user_id='pjyub1379';

CREATE OR REPLACE TRIGGER USER_DEL_TRG
AFTER DELETE  -- 트리거 동작 시점(SELECT시점에는 쓸 수 없다.)
ON WEB_USER
FOR EACH ROW --행마다 실행이 되냐? 안되냐?
BEGIN
    INSERT INTO DEL_WEB_USER VALUES(
    :OLD.USER_ID,:OLD.USER_PW,:OLD.USER_NAME,:OLD.USER_NICKNAME,:OLD.PHONE,:OLD.EMAIL,:OLD.ADDRESS,
    :OLD.EXPIRED_DATE,:OLD.CON_AGREE
    );
END;
/
commit;
----------------------------------------
-- 앨범 테이블 생성
----------------------------------------

CREATE TABLE ALBUM(
    ALBUM_NO NUMBER PRIMARY KEY,
    ALBUM_OWNER VARCHAR2(300) NOT NULL,
    ALBUM_NAME VARCHAR2(600) NOT NULL,
    ALBUM_PATH VARCHAR2(300)    
);

----------------------------------------
-- ALBUM SEQUENCE 생성
----------------------------------------
DROP SEQUENCE SEQ_ALBUM_NO;
CREATE SEQUENCE SEQ_ALBUM_NO;

----------------------------------------
-- 공식 아티스트 테이블 생성
---------------------------------------
CREATE TABLE LICENSED_ARTIST(
    LCN_ARTIST_NAME VARCHAR2(300) PRIMARY KEY,
    LCN_COMPANY VARCHAR2(100),
    LCN_AGENT_NAME VARCHAR2(60),
    LCN_AGENT_PHONE VARCHAR2(11)    
);

----------------------------------------
-- 노래 테이블 생성
----------------------------------------
CREATE TABLE SONG (
    SONG_NO NUMBER PRIMARY KEY,
    SONG_TITLE VARCHAR2(600) NOT NULL,
    SONG_ARTIST VARCHAR2(300) NOT NULL,
    SONG_GENRE VARCHAR2(60) NOT NULL,
    ALBUM_NO REFERENCES ALBUM(ALBUM_NO) ON DELETE CASCADE,
    PLAY_COUNT NUMBER NOT NULL,
    LIKE_COUNT NUMBER NOT NULL,
    FILENAME VARCHAR2(300) NOT NULL,
    FILEPATH VARCHAR2(300) NOT NULL,
    LICENSED NUMBER NOT NULL
);

----------------------------------------
-- 노래 테이블 시퀀스 생성
----------------------------------------
DROP SEQUENCE SEQ_SONG_NO;
CREATE SEQUENCE SEQ_SONG_NO;

----------------------------------------
-- 삭제 노래 테이블 생성
----------------------------------------
CREATE TABLE DELETED_SONG (
    SONG_NO NUMBER PRIMARY KEY,
    SONG_TITLE VARCHAR2(600) NOT NULL,
    SONG_ARTIST VARCHAR2(300) NOT NULL,
    SONG_GENRE VARCHAR2(60) NOT NULL,
    ALBUM_NO REFERENCES ALBUM(ALBUM_NO) ON DELETE CASCADE,
    PLAY_COUNT NUMBER NOT NULL,
    LIKE_COUNT NUMBER NOT NULL,
    FILENAME VARCHAR2(300) NOT NULL,
    FILEPATH VARCHAR2(300) NOT NULL,
    LICENSED NUMBER NOT NULL
);

----------------------------------------
-- 음원 신고 테이블 작성
----------------------------------------
CREATE TABLE SONG_REPORT(
    REPORT_NO NUMBER PRIMARY KEY,
    SONG_NO NUMBER REFERENCES SONG(SONG_NO),
    REPORT_CONTENT VARCHAR2(900) NOT NULL,
    REPORT_DATE DATE DEFAULT SYSDATE NOT NULL,
    REPORT_CHECKED NUMBER NOT NULL
);

----------------------------------------
-- 음원 신고 SEQUENCE 작성
----------------------------------------
DROP SEQUENCE SEQ_REP_NO;
CREATE SEQUENCE SEQ_REP_NO;

----------------------------------------
-- 플레이리스트 테이블 작성
----------------------------------------
CREATE TABLE PLAYLIST(
    USER_ID VARCHAR2(20) REFERENCES WEB_USER(USER_ID) ON DELETE CASCADE,
    LISTED_SONG_NO NUMBER REFERENCES SONG(SONG_NO) ON DELETE CASCADE,
    ORDER_NO NUMBER
);

----------------------------------------
-- 좋아요 리스트 테이블 작성
----------------------------------------
CREATE TABLE LIKELIST(
    USER_ID VARCHAR2(20) REFERENCES WEB_USER(USER_ID) ON DELETE CASCADE,
    LIKED_SONG_NO NUMBER REFERENCES SONG(SONG_NO) ON DELETE CASCADE
);

----------------------------------------
-- 이용권 테이블 작성
----------------------------------------
CREATE TABLE VOUCHER(
    VOUCHER_NO NUMBER PRIMARY KEY,
    VOUCHER_NAME VARCHAR2(120) NOT NULL,
    VOUCHER_AVAIL_DATE NUMBER NOT NULL
);

DROP SEQUENCE SEQ_VOU_NO;
CREATE SEQUENCE SEQ_VOU_NO;

----------------------------------------
-- 구매내역 테이블 작성
----------------------------------------
CREATE TABLE PURCHASE_LOG(
    PURCHASE_NO VARCHAR2(30) PRIMARY KEY,
    VOUCHER_NO NUMBER REFERENCES VOUCHER(VOUCHER_NO),
    USER_ID VARCHAR2(20) NOT NULL,
    PURCHASE_DATE DATE DEFAULT SYSDATE NOT NULL,
    BEGIN_DATE DATE NOT NULL,
    EXPIRED_DATE DATE NOT NULL
);

DROP SEQUENCE SEQ_PUR_NO;
CREATE SEQUENCE SEQ_PUR_NO;

----------------------------------------
-- 고객문의 테이블 작성
----------------------------------------
CREATE TABLE INQUIRY(
    INQ_NO NUMBER PRIMARY KEY,
    USER_ID VARCHAR2(20) NOT NULL,
    INQ_TITLE VARCHAR2(100) NOT NULL,
    INQ_CONTENT VARCHAR2(4000) NOT NULL,
    INQ_DATE DATE DEFAULT SYSDATE NOT NULL,
    INQ_ANS_NO NUMBER DEFAULT 0,
    INQ_FILENAME VARCHAR2(300),
    INQ_FILEPATH VARCHAR2(300)
);
select * from inquiry where inq_no=1;
select*from(select rownum as rnum, n.* from (select*from inquiry where user_id='user01' order by inq_no desc)n)where rnum between 1 and 10;
select count(*)as cnt from inquiry where user_id='user01';
ALTER TABLE INQUIRY
MODIFY INQ_ANS_NO DEFAULT 0;
select*from inquiry;
DROP SEQUENCE SEQ_INQ_NO;
CREATE SEQUENCE SEQ_INQ_NO;
delete from INQUIRY where user_id='user01';
COMMIT;
----------------------------------------
-- 문의 답변 테이블 작성
----------------------------------------
CREATE TABLE INQ_ANS (
    INQ_ANS_NO NUMBER PRIMARY KEY,
    INQ_NO NUMBER REFERENCES INQUIRY(INQ_NO) ON DELETE CASCADE,
    INQ_ANS_TITLE VARCHAR2(100) NOT NULL,
    INQ_ANS_CONTENT VARCHAR2(4000) NOT NULL,
    INQ_ANS_DATE DATE DEFAULT SYSDATE    
);

DROP SEQUENCE SEQ_ANS_NO;
CREATE SEQUENCE SEQ_ANS_NO;

----------------------------------------
-- 공지사항 테이블 작성
----------------------------------------
CREATE TABLE NOTICE(
    NOTICE_NO NUMBER PRIMARY KEY,
    NOTICE_TITLE VARCHAR2(100) NOT NULL,
    NOTICE_CONTENT VARCHAR2(4000) NOT NULL,
    NOTICE_DATE DATE DEFAULT SYSDATE NOT NULL,
    NOTICE_FILENAME VARCHAR2(300),
    NOTICE_FILEPATH VARCHAR2(300)
);



DROP SEQUENCE SEQ_NOT_NO;
CREATE SEQUENCE SEQ_NOT_NO;

COMMIT;
