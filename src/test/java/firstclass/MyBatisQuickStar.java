package firstclass;

import firstclass.bean.User;
import firstclass.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisQuickStar {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init(){
        InputStream in;
        try {
            in = Resources.getResourceAsStream("firstclass/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User res = mapper.getUserByPrimaryKey(1);
        System.out.println(res);
    }
}
