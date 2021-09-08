package generator;

import generator.bean.TUser;
import generator.bean.TUserExample;
import generator.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorTest {

    @Test
    public void test01(){
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String genCfg = "generatorConfig.xml";
        File configFile = new File(getClass().getClassLoader().getResource(genCfg).getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private SqlSessionFactory sqlSessionFactory;
    @Before
    public void init() throws IOException{
        String resource = "generator/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        in.close();
    }

    /**
     * Example类的使用
     */
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
        TUserExample tUserExample = new TUserExample();
        TUserExample.Criteria criteria = tUserExample.createCriteria();
        criteria.andEmailIsNotNull();
        List<TUser> tUsers = mapper.selectByExample(tUserExample);
        for (TUser tUser : tUsers) {
            System.out.println(tUser);
        }
    }
}
