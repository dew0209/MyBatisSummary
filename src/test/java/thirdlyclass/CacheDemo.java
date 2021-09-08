package thirdlyclass;

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
import java.io.Reader;
import java.util.List;

public class CacheDemo {
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException {
        String resource = "thirdlyclass/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        in.close();
    }

    /**
     * 测似一级缓存
     */
    @Test
    public void test01(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        TUserMapper mapper = sqlSession1.getMapper(TUserMapper.class);
        String email = "qq.com";
        Byte sex = 1;
        List<TUser> tUsers1 = mapper.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers1.size());
        System.out.println("==================");
        System.out.println("增删改会清空一级缓存和二级缓存");
        TUser tUser = new TUser();
        tUser.setRealName("test");
        tUser.setRealName("testRealName");
        tUser.setEmail("myemail");
        mapper.insert1(tUser);
        List<TUser> tUsers2 = mapper.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers2.size());
        sqlSession1.close();
        System.out.println("关闭1sqlsession");
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        TUserMapper mapper1 = sqlSession2.getMapper(TUserMapper.class);
        List<TUser> tUsers = mapper1.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers.size());
    }
    /**
     * 测试二级缓存
     * 二级缓存必须要求bean实现Serializable接口进行序列化
     */
    @Test
    public void test02(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        TUserMapper mapper = sqlSession1.getMapper(TUserMapper.class);
        String email = "qq.com";
        Byte sex = 1;
        List<TUser> tUsers1 = mapper.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers1.size());
        System.out.println("==================");
        System.out.println("增删改会清空一级缓存和二级缓存");
        TUser tUser = new TUser();
        tUser.setRealName("test");
        tUser.setRealName("testRealName");
        tUser.setEmail("myemail");
        mapper.insert1(tUser);
        List<TUser> tUsers2 = mapper.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers2.size());
        sqlSession1.close();
        System.out.println("关闭1sqlsession");
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        TUserMapper mapper1 = sqlSession2.getMapper(TUserMapper.class);
        List<TUser> tUsers = mapper1.selectByEmailAndSex2(email, sex);
        System.out.println(tUsers.size());
    }
}
