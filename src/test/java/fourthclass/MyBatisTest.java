package fourthclass;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import thirdlyclass.bean.TUser;
import thirdlyclass.mapper.TUserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("thirdlyclass/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    /**
     * 1对1
     */
    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = mapper.selectUserPosition1();
        //嵌套结果
        for(TUser tUser : tUsers){
            System.out.println(tUser);
        }
        System.out.println("========================");
        //嵌套查询
        List<TUser> tUsers1 = mapper.selectUserPosition2();
        for(TUser tUser : tUsers1){
            System.out.println(tUser);
        }
    }
    /**
     * 一对多
     */
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        //嵌套结果
        List<TUser> tUsers = mapper.selectUserJobs1();
        //嵌套查询
        List<TUser> tUsers1 = mapper.selectUserJobs2();

        for (TUser tUser : tUsers) {
            System.out.println(tUser);
        }
        System.out.println("=================");
        for (TUser tUser : tUsers1) {
            System.out.println(tUser);
        }
    }
    /**
     * 鉴别器
     */
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        List<TUser> tUsers = mapper.selectUserHealthReport();
        for (TUser tUser : tUsers) {
            System.out.println(tUser);
        }
    }
    @Test
    // 多对多
    public void test04() {
        // 2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取对应mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        // 4.执行查询语句并返回结果
        // ----------------------
        List<TUser> list = mapper.selectUserRole();
        for (TUser tUser : list) {
            System.out.println(tUser);
        }
    }
    /**
     * example类
     */
    @Test
    public void test05() {

    }
}
