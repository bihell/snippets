账号密码 root

use zuche;
CREATE TABLE `users`
(
  `id`       int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255)     NOT NULL,
  `age`      int(10)          NOT NULL,
  `phone`    bigint           NOT NULL,
  `email`    varchar(255)     NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
insert into users
values (1, '赵', 23, 158, '3658561548@qq.com');
insert into users
values (2, '钱', 27, 136, '3658561548@126.com');
insert into users
values (3, '孙', 31, 159, '3658561548@163.com');
insert into users
values (4, '李', 35, 130, '3658561548@sina.com');


create table userdetails
(
  id      int auto_increment
    primary key,
  uid     int          null,
  address varchar(255) null,
  city    varchar(50)  null,
  marry   tinyint(1)   null
);


-- 创建useraccount表

CREATE TABLE `useraccount` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `username` varchar(255) NOT NULL,
   `age` int(10) NOT NULL,
   `phone` bigint NOT NULL,
   `email` varchar(255) NOT NULL,
   `account` varchar(100) NOT NULL UNIQUE,
   `pwd` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
insert into `useraccount` values(1,'赵(dev)',23,158,'3658561548@qq.com','test001','test001');
insert into `useraccount` values(2,'钱(dev)',27,136,'3658561548@126.com','test002','test002');
insert into `useraccount` values(3,'孙(dev)',31,159,'3658561548@163.com','test003','test003');
insert into `useraccount` values(4,'李(dev)',35,130,'3658561548@sina.com','test004','test004');
select * from `useraccount`;