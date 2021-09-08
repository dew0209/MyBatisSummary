动态sql

|          元素           |         作用          |                    备注                    |
| :---------------------: | :-------------------: | :----------------------------------------: |
|           if            |       判断语句        |               单条件分支判断               |
| choose，when，otherwise | 相当于java的case when |               多条件分支判断               |
|    trim，where，set     |       辅助元素        |            用于处理sql拼装问题             |
|         foreach         |       循环语句        | 在in语句等列举条件常用，常用于实现批量操作 |

批量操作

```xml
通过foreach动态拼装sql语句
使用batch类型的excutor
```

关联查询

```xml
association 一对一关系
collection	一对多关系
discriminator	鉴别器映射
关联方式：
	嵌套结果：使用嵌套结果映射来处理重复的联合结果的子集
	嵌套查询：通过执行另外一个SQL映射语句来返回预期的复杂类型
一对一：
	association标签 嵌套结果方式 常用属性
		property：对应实体类中的属性名
		javaType：属性对应的Java类型
		resultMap：可以直接使用现有的resultMap，而不需要在这里配置映射关系
		columnPrefix：查询列的前缀，配置前缀后，在子标签配置result的column时可以被省略
	tips：resultMap可以通过使用extend实现继承关系，简化很多配置工作量
				关联的表查询的类添加前缀是编程的好习惯
				通过添加完整的命名空间，可以引用其它xml为念的resultMap
	association标签 嵌套查询方式 常用属性
		select：另一个映射查询的id，MyBatis会额外执行这个查询嵌套对象的结果
		column：列名（或别名），将主查询中列的结果作为嵌套查询的参数
		fetchType：数据加载方式，可选值为lazy和eager，分别为延迟加载和及时加载，这个配置会覆盖全局的lazyLoadingEnabled配置。
	tips：“N + 1”查询问题
		概括的讲，N + 1查询问题可以是这样引起的：
			你执行了一个单独的SQL语句来获取结果列表（就是“+1”）
			对返回的每条记录，你执行了一个查询语句来为每个加载细节（就是“N”）
		这个问题会导致成百上千的SQL语句被执行。这通常不是被期望的。
		解决方法：使用“fetchType=lazy”并且全局setting进行改善

一对多
collection支持的属性以及属性的作用和association完全相同
mybatis会根据id标签，进行字段的合并，合理配置好id标签可以提高处理的效率
tips：如果要配置一个相当复杂的映射，一定要从基础映射开始配置，每增加一些配置就进行对应的测试，在循序渐进的过程中更加容易发现和解决问题。

discriminator鉴别器映射
	在特定的情况下使用不同的bean进行关联，鉴别器元素就是被设计来处理这个情况的。它的表现很像Java语言中的switch语句
	dicrimonator标签常用的两个属性如下：
		column：该属性用于设置要进行鉴别比较值的列
		javaTYpe：该属性用于指定列的类型，保证使用相同的Java类型来比较值。
	dicrimonator标签可以有1个或多个case标签，case标签包含以下三个属性。
		value：该值为discriminator指定column用来匹配的值
		resultMap：当column的值和value的值匹配时，可以配置使用resultMap指定的映射。
		resultType ： 当 column 的值和 value 的值匹配时，用于配置使用 resultType指定的映射

多对多
	由两个一对多实现

缓存：[避免频繁的数据库交互]
	一级缓存(默认开启)
		一级缓存存在于 SqlSession 的生命周期中，在同一个 SqlSession 中查询时， MyBatis 会把执行的方法和参数通过算法生成缓存的键值，将键值和查询结果存入一个 Map对象中。如果同一个 SqlSession 中执行的方法和参数完全一致，那么通过算法会生成相同的键值，当 Map 缓存对象中己经存在该键值时，则会返回缓存中的对象
		任何的 INSERT 、UPDATE 、 DELETE 操作都会清空一级缓存
	二级缓存
		二级缓存存在于 SqlSessionFactory 的生命周期中，可以理解为跨sqlSession；缓存是以namespace为单位的，不同namespace下的操作互不影响。
		setting参数 cacheEnabled，这个参数是二级缓存的全局开关，默认值是 true，如果把这个参数设置为false，即使有后面的二级缓存配置，也不会生效。
		tips：使用二级缓存容易出现脏读，建议避免使用二级缓存，在业务层使用可控制的缓存代替更好；
					bean需要实现序列化接口
```

