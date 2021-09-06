package secondclass;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import secondclass.bean.EmailSexBean;
import secondclass.bean.TUser;
import secondclass.mapper.TUserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MyBatisDemo {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        String resource = "secondclass/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    /**
     * 自动映射
     * 前提：SQL列名和JavaBean的属性是一致的
     * 自动映射由autoMappingBehavior控制，将
     * {@code}{
     *     <settings>
     *         <setting name="autoMappingBehavior" value="NONE"/>
     *      </settings>
     *  }
     *  autoMappingBehavior三种取值：
     *      NONE 取消自动映射
     *      PARTIAL 只会自动映射，没有定义嵌套结果集映射的结果集
     *      FULL 会自动映射任意复杂的结果集，无论是否嵌套[使用FULL，性能会下降，一般使用PARTIAL即可]
     */
    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = mapper.selectByPrimaryKey(1);
        System.out.println(tUser);
    }

    /**
     * 多参数查询
     *  使用map传递参数
     *  使用注解方式传递参数
     *      参数注解@Param，例如：{@code}{
     *          public List<TUser> selectByEmailAndSex2(@Param("email")String email,@Param("sex")Byte sex);
     *      }
     *      对应的xml文件即可无需定义参数类型
     *  使用JavaBean传递参数
     *      在参数过多的情况下，MyBatis允许组织一个JavaBean，通过简单的setter和getter方法设置参数，这样就可以提高我们的可读性。
     *  推荐：
     *      1.使用map传递参数。因为map导致业务丧失可读性，从而导致后续扩展和维护的困难，我们应该在实际的应用中果断废弃这样的传递参数的方式
     *      2.使用@Param注解传递多个参数，这种方式的使用受到参数个数的影响。推荐5个以下使用注解，以上封装成bean
     *      3.当参数个数多于5个时，建议采用JavaBean的方式
     */
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        String email = "qq.com";
        Byte sex = 1;
        //第一种方式使用map
        HashMap<String, Object> params = new HashMap<>();
        params.put("email",email);
        params.put("sex",sex);
        List<TUser> list1 = mapper.selectByEmailAndSex1(params);
        System.out.println("获得到的长度为：" + list1.size());
        for(TUser user : list1){
            System.out.println(user);
        }
        //第二种方式直接使用参数
        List<TUser> tUsers = mapper.selectByEmailAndSex2(email, sex);
        System.out.println("得到的长度为：" + tUsers.size());
        for (TUser user : tUsers){
            System.out.println(user);
        }
        //第三种方式用对象
        EmailSexBean esb = new EmailSexBean();
        esb.setSex(sex);
        esb.setEmail(email);
        List<TUser> tUsers1 = mapper.selectByEmailAndSex3(esb);
        System.out.println("得到的长度为：" + tUsers1.size());
        for(TUser user : tUsers1){
            System.out.println(user);
        }
    }

    /**
     * 测试插入数据自动生成id
     *  主键回填：
     *      首先使用keyProperty指定哪个是主键字段，同时字段useGeneratedKeys属性告诉MyBatis这个之间是否使用数据库内置策略生成
     *    不能处理复杂的要求[如取消主键自增该怎么办]
     */
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser user1 = new TUser();
        user1.setUserName("test1");
        user1.setRealName("realname1");
        user1.setEmail("myemail");
        mapper.insert1(user1);
        System.out.println(user1);
        sqlSession.commit();
    }
    /**
     * 测试插入数据自动生成id
     *    实际上，主键的生成规则更加复杂，当我们需要更具一些特殊关系设置主键id的值。就可以使用
     *          @{<code
     *                  <selectKey keyProperty="id" order="AFTER" resultType="int">
     *                      select LAST_INSERT_ID()
     *                  </selectKey>
     *           }
     *    这样我们就能定义自己的规则来生成主键了，MyBatis的灵活性也得以体现
     */
    @Test
    public void test04(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser user1 = new TUser();
        user1.setUserName("test2");
        user1.setRealName("realname2");
        user1.setEmail("myemail2");
        mapper.insert2(user1);
        System.out.println(user1);
        sqlSession.commit();
    }

    /**
     * 参数#和$区别测试
     * @code{
     *     select ${inCol}
     *     from ${tableName} a
     *     where a.sex = #{sex}
     *     order by ${orderStr}
     * }
     * 会查出数据
     * @code{
     *      select #{inCol}
     *      from ${tableName} a
     *      where a.sex = #{sex}
     *      order by ${orderStr}
     * }
     * 也会查出三条数据，但是为null
     *
     * 参数：
     *   向sql语句中传递的可变参数
     *      预编译#{}：将传入的数据都当成一个字符串，会对传入的数据加上一个单引号。能够很大程度防止sql注入
     *      传值${}：传入的数据直接显式生成在sql中，无法防止sql注入
     *      表名，选取的列是动态的，order by和in操作，可以考虑使用$
     */
    @Test
    public void test05(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        String inCol = "id,user_name,real_name,sex,mobile,email,note";
        String tableName = "t_user";
        Byte sex = 1;
        String orderStr = "sex,user_name";
        List<TUser> tUsers = mapper.selectBySymbol(tableName, inCol, orderStr, sex);
        System.out.println(tUsers.size());
        for(TUser user : tUsers){
            System.out.println(user);
        }
    }
}
