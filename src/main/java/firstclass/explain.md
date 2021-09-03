```xml
传统JDBC存在弊端
	工作量大，操作数据库至少要5步
	业务代码和技术代码耦合
	连接资源手动关闭，带来了隐患
ORM：
	什么是ORM:object relational mapping,数据库的表月简单Java对象(POJO)的映射模型，它主要解决数据库和POJO对象的相互映射
	ORM带来的好处：
		更加贴合面向对象的编程语意
		技术和业务解耦，Java程序员无需对数据库相关的知识深入了解
		不用手动去释放资源
MyBatis:半自动ORM框架
[Java]<--->[pojo]<--->[Mapper接口/SQL映射规则]<--->[数据库]
mybatis的快速开始
	加入MyBatis的依赖
  添加MyBatis的配置文件
  场景介绍
  编写实体类，mapper接口以及mapper.xml文件
	
```

```sql
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(60) DEFAULT NULL COMMENT '用户名称',
  `real_name` VARCHAR(60) DEFAULT NULL COMMENT '真实名称',
  `sex` TINYINT(3) DEFAULT NULL COMMENT '姓名',
  `mobile` VARCHAR(20) DEFAULT NULL COMMENT '电话',
  `email` VARCHAR(60) DEFAULT NULL COMMENT '邮箱',
  `note` VARCHAR(200) DEFAULT NULL COMMENT '备注',
  `position_id` INT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
INSERT INTO `t_user` VALUES ('1', 'lison', '李小宇', '1', '186995587411', 'lison@qq.com', 'lison的备注', '1');
INSERT INTO `t_user` VALUES ('2', 'james', '陈大雷', '1', '17365987455', 'james@qq.com', 'james的备注', '2');
INSERT INTO `t_user` VALUES ('3', 'cindy', '王美丽', '2', '18695988747', 'xxoo@163.com', 'cindy\'s note', '1');
INSERT INTO `t_user` VALUES ('32', 'mark', '毛毛', '1', '18695988747', 'xxoo@163.com', 'mark\'s note', '1');
```

```xml
SqlSessionFactoryBuilder:读取配置信息，创建SqlSessionFactory。建造者模式。方法级别声明周期。
SqlSessionFactory：创建SqlSession，工厂单例模式，存在于程序的整个生命周期。
SqlSession：代表一次数据库连接，可以直接发送SQL执行，也可以通过调用Mapper访问数据库；线程不安全，要保证线程独享（方法级）
SQL Mapper：由一个Java接口和XML文件组成，包含了要执行的SQL语句和结果集映射规则。方法级别生命周期。
```

配置[settings]

```xml
enviorment元素是配置一个数据源的开始，属性id是它唯一标识
  transactionManager元素配置数据事物。[jdbc|manages|自定义]
  dataSource元素配置数据源连接信息。[UNPOOLED|POOLED|JNDI|自定义数据源]

```

