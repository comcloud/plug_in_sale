create table user(
    --用户id
    uid int primary key not null,
    --用户qq号
    qqNumber varchar(20),
    --用户购买数量
    ucount int,
    --用户购买时间
    utime datetime
)
--循环插入数据
DELIMITER $$
CREATE PROCEDURE pre()
BEGIN
DECLARE i INT;
SET i=1;
WHILE i<1000 DO
    INSERT INTO goods(pwd,STATUS) VALUES(1234568+i,1);
SET i=i+1;
END WHILE;
END
$$
CALL pre();

DELETE FROM goods

SELECT * FROM goods;