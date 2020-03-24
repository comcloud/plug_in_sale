create table user(
    --用户id
    uid int primary key not null,
    --用户qq号
    qqNumber varchar(20),
    --用户购买数量
    ucount int,
    --用户购买时间
<<<<<<< HEAD
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
=======
    utime varchar(20)
)


declare @num int
set @num = 0
begin
while @num < 1000
    insert into goods(pwd,status) values(1234567+num,1)
    set @num = @num + 1
end
>>>>>>> 91039f7622718f570394c1607b7a8defacbec5bb
