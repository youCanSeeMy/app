//管理员表
create table f_admin
(id varchar(40) primary  key,
name varchar(40),
password varchar(8)
);
insert into f_admin(replace(uuid(),'-',''),'root','root');
//菜单表
create table f_menu(
id varchar(40) primary key,
title varchar(40),
iconCls varchar(40),
parent_id varchar(40),
url varchar(40));
select p.id,p.title,p.iconCls,p.parent_id,p.url,c.id,c.title,c.iconCls,c.parent_id,c.url
from f_menu p left outer join f_menu c on p.id = c.parent_id where p.parent_id is null;
insert into f_menu values (replace(uuid(),'-',''),'轮播图管理','icon-ok','','login.jsp');
//轮播图表
create table f_banner(
id varchar(40) primary key,
title varchar(40),
imgPath varchar(40),
describle varchar(40),
status varchar(2),
date Date);
//专辑表
create table f_ablum(id varchar(40) primary key,title varchar(40),coverImg varchar(40),count int,score double,author varchar(40),broadCast varchar(40),brief varchar(40),publishDate date);
//章节表
create table f_chapter(id varchar(40) primary key,title varchar(40),size varchar(10),duration int,downPath varchar(40),uploadDate date);
//用户表
create table f_user(id varchar(40) primary key,phoneNum varchar(40),username varchar(8),password varchar(40),salt varchar(4),dharmaName varchar(20),province varchar(10),city varchar(10),sex  varchar(3),sign varchar(2),headPic varchar(100),status varchar(3),date date);
select Datediff(sysdate(),date) as day from f_user;
select province,count(*) from f_user group by province;
select count(*),province from f_user where sex='男' group by province;















































//用户表
create table f_user
(id varchar(40) primary key,
mobile int,
password varchar(6),
nickname varchar(8),
state int,
regTime date,
salt varchar(4),
photo varchar(50),
signature varchar(20)
);