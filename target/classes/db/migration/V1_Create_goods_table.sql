create table goods(
--     商品主键
    gid int primary key not null ,
--     密码
    pwd varchar(50),
--     此密码的状态，0为已经被使用，1表示未被使用
    status int(3)
)