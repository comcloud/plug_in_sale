create table user(
    --用户id
    uid int primary key not null ,
    --用户qq号
    qqNumber varchar(20),
    --用户购买数量
    ucount int,
    --用户购买时间
    utime varchar(20)
)


declare @num int
set @num = 0
begin
while @num < 1000
    insert into goods(pwd,status) values(1234567+num,1)
    set @num = @num + 1
end