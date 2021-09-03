package firstclass.jdbc;

import firstclass.bean.User;
import org.junit.Test;

import java.sql.*;

public class JdbcTest {
    //驱动
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //数据库连接的地址
    public static final String DB_URL = "jdbc:mysql:///mybatisend?characterEncoding=UTF-8&amp;serverTimezone=UTC";
    //用户名
    public static final String USERNAME = "root";
    //密码
    public static final String PASSWORD = "020922";

    /**
     * 利用statement进行查询
     */
    @Test
    public void test01(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册mysql驱动
            Class.forName(JDBC_DRIVER);
            //获得一个连接
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //创建一个查询
            System.out.println("创建一个statement...");
            stmt = conn.createStatement();
            String userName = "李小宇";
            String sql = "select * from t_user where real_name = '" + userName +"'";
            ResultSet res = stmt.executeQuery(sql);
            System.out.println(stmt);//不提供打印sql语句的功能
            //从resultset中获取数据并转为为bean
            while (res.next()){
                User user = new User();
                user.setId(res.getString("id"));
                user.setMobile(res.getString("mobile"));
                user.setNote(res.getString("note"));
                user.setSex(res.getByte("sex"));
                user.setRealName(res.getString("real_name"));
                user.setUserName(res.getString("user_name"));
                user.setPositionId(res.getInt("position_id"));
                System.out.println(user);
            }
            //关闭连接
            res.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    /**
     * 利用PreparedStatement进行查询
     */
    @Test
    public void test02(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //注册mysql驱动
            Class.forName(JDBC_DRIVER);
            //获得一个连接
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //创建一个查询
            System.out.println("创建一个prepareStatement...");
            String sql = "select * from t_user where real_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"李小宇");
            ResultSet res = stmt.executeQuery();
            System.out.println(stmt.toString());//打印sql
            //从ResultSet中获取数据并转为为bean
            while (res.next()){
                User user = new User();
                user.setId(res.getString("id"));
                user.setMobile(res.getString("mobile"));
                user.setNote(res.getString("note"));
                user.setSex(res.getByte("sex"));
                user.setRealName(res.getString("real_name"));
                user.setUserName(res.getString("user_name"));
                user.setPositionId(res.getInt("position_id"));
                System.out.println(user);
            }
            //关闭连接
            res.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * 更新[插入，删除同理，都用executeUpdate方法]
     */
    @Test
    public void test03() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //注册mysql驱动
            Class.forName(JDBC_DRIVER);
            //获得一个连接
            conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            //启动手动提交
            conn.setAutoCommit(false);
            //创建一个更新
            String sql = "update t_user set mobile = ? where user_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"18888888888");
            stmt.setString(2,"lison");
            int res = stmt.executeUpdate();
            System.out.println(stmt.toString());
            System.out.println("影响结果的行数：" + res);
            //手动提交事物
            conn.commit();
            //关闭连接
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    /**
     * 删除
     */
    public void test04(){

    }
}
