create table user(
    --用户id
    uid int primary key not null ,
    --用户qq号
    qqNumber varchar(20),
    --用户购买数量
    ucount int,
    --用户购买时间
    utime datetime
)