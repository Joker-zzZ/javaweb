drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint auto_increment,
   username             varchar(255),
   userpwd              varchar(255),
   realname             varchar(255),
   primary key (id)
);
insert into t_user(username,userpwd,realname) values('zs','qwe','张三');
insert into t_user(username,userpwd,realname) values('ls','123','李四');