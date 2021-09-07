select元素

```xml
id：它和Mapper的命名空间组合起来是唯一的，提供给MyBatis调用
parameterType：你可以给出类的全命名，也可以给出类的别名。
parameterMap：即将废弃的元素，不再讨论
resultType：定义类的全路径，在允许自动匹配的情况下，结果集将通过JavaBean的规范映射，或定义为						int,double,float等参数，也可以使用别名，但是要符合规范，不能和resultmap同时使用。
resultMap：它是映射集的引用，将执行强大的映射功能，我们可以使用resultMap或者resultType其中的一个。
flushCache：它的作用是再调用sql之后，是否要求MyBatis清空之前查询的本地缓存和二级缓存。
useCache：启动二级缓存的开关，是否要求MyBatis将此次结果缓存。
timeout：设置超时参数，等超时的时候将抛出异常，单位为秒。
fetchSize：获得记录的总条数设定
statementType：告诉MyBatis使用哪个JDBC的Statement工作。
resultSetType：这是对JDBC的resultSet接口而言。FORWARD_ONLY:游标允许向前访问，										 SCROLL_SECSITIVE:双向滚动，但不及时更新，就是如果数据库中的数据修改过，并不在resultset中反应出来
							 SCROLL_INSENSITIVE:双向滚动，并及时跟踪数据库的更新，以便更改resultSet中的数据
databaseId：提供多种数据库的支持
[没看懂]resultOrdered：这个设置仅适用于嵌套结果集select语句。如果为true，就是假设包含了										 嵌套结果集或者是分组了。当返回一个主结果行的时候，就不能对前面结果											 集的引用。这就确保了在获取嵌套的结果集的时候不至于导致内存不够用。
resultSets:适合于多个结果集的情况，它将列出执行SQL后每个结果集的名称，每个名称之间用逗号分隔。
```

insert元素

```xml
id:它和Mapper的命名空间组合起来是唯一的，作为唯一标识提供给MyBatis调用。
parameterType：你可以提供给出类的全命名，也可以是是一个别名。
parameterMap：即将废弃
flushCache：它的作用是在调用sql后，是否要求MyBatis清空之前查询的本地缓存和二级缓存。
timeout：设置超时参数，等超时的时候将抛出异常，单位为秒。
statementType：告诉MyBatis使用哪个JDBC的Statement工作。
keyProperty：表示以哪个列作为属性的主键。不能和keyColumn同时使用[这个好像不对，好像可以一起使用，测试没有两个其中之一也是，不知道可是机器问题]
useGeneratedKeys：使用JDBC来获取数据库内部生成的主键。
keyColunm：指明第几列是主键，不能和keyColunm同时使用，只接受整形参数。[有点问题]
databaseId：提供多种数据库的支持
lang：自定义语言，可使用第三方语言。
```

resultMap元素

```xml
		<resultMap>
        <constructor>
            <idArg />
            <arg />
        </constructor>
        <id />
        <result />
        <association />
        <discriminator />
            <case />
        </discriminator>
    </resultMap>
```

constructor元素用于配置构造方法。

