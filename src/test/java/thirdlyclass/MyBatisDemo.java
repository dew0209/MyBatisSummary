package thirdlyclass;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import thirdlyclass.bean.TPosition;
import thirdlyclass.bean.TUser;
import thirdlyclass.mapper.TUserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MyBatisDemo {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream in = Resources.getResourceAsStream("thirdlyclass/mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        in.close();
    }
    /**
     * 动态sql
     */
    //if用于select，并于where结合
    @Test
    public void test01() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        String email = null;
        Byte sex = 1;
        List<TUser> tUsers = mapper.selectIfandWhereOper(email, sex);
        List<TUser> tUsers1 = mapper.selectIfOper(email, sex);
        System.out.println(tUsers.size());
        System.out.println(tUsers1.size());
    }
    /**
     * if用于update，并与set配合
     */
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser tUser = new TUser();
        tUser.setId(3);
        tUser.setUserName("cindy");
        tUser.setEmail("xx00@163.com");
        tUser.setMobile("18688888888");
        tUser.setSex((byte) 2);
        tUser.setNote("cindy's note");
        System.out.println(mapper.updateIfAndSetOper(tUser));
    }
    /**
     * if用于insert，并于trim配合
     */
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser user = new TUser();
        user.setUserName("mark");
        user.setRealName("毛毛");
        System.out.println(mapper.insertSelective(user));
    }
    /**
     * foreach用于in查询
     */
    @Test
    public void test04(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        String[] names = new String[]{"lison","james"};
        List<TUser> tUsers = mapper.selectForeach4In(names);
        System.out.println(tUsers.size());
    }
    /**
     * foreach用于批量插入
     */
    @Test
    public void test05(){
        // 2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.获取对应mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

        TUser user1 = new TUser();
        user1.setUserName("king");
        user1.setRealName("李小京");
        user1.setEmail("li@qq.com");
        user1.setMobile("18754548787");
        user1.setNote("king's note");
        user1.setSex((byte)1);
        TUser user2 = new TUser();
        user2.setUserName("deer");
        user2.setRealName("陈大林");
        user2.setEmail("chen@qq.com");
        user2.setMobile("18723138787");
        user2.setNote("deer's note");
        user2.setSex((byte)1);
        int i = mapper.insertForeach4Batch(Arrays.asList(user1,user2));
        System.out.println(i);
        sqlSession.commit();
    }
    /**
     * 测试ExecutorType.BATCH
     */
    @Test
    public void test06(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUser user = new TUser();
        user.setUserName("mark");
        user.setRealName("毛毛");
        user.setEmail("xxoo@163.com");
        user.setMobile("18695988747");
        user.setNote("mark's note");
        user.setSex((byte) 1);
        TPosition positon1 = new TPosition();
        positon1.setId(1);
        user.setPosition(positon1);
        System.out.println(mapper.insertSelective(user));

        TUser user1 = new TUser();
        user1.setId(3);
        user1.setUserName("cindy");
        user1.setRealName("王美丽");
        user1.setEmail("xxoo@163.com");
        user1.setMobile("18695988747");
        user1.setNote("cindy's note");
        user1.setSex((byte) 2);
        user.setPosition(positon1);
        System.out.println(mapper.updateIfAndSetOper(user1));

        sqlSession.commit();
    }
}
