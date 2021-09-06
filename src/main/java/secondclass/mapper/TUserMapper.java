package secondclass.mapper;

import org.apache.ibatis.annotations.Param;
import secondclass.bean.EmailSexBean;
import secondclass.bean.TUser;

import java.util.List;
import java.util.Map;

public interface TUserMapper {
    public int deleteByPrimaryKey(Integer id);
    public int insert1(TUser record);
    public int insert2(TUser record);
    public int insertSelective(TUser record);
    public TUser selectByPrimaryKey(Integer id);
    public int updateByPrimaryKeySelective(TUser record);
    public int updateByPrimaryKey(TUser record);

    public List<TUser> selectUserJobs1();
    public List<TUser> selectUserJob2();
    public List<TUser> selectByEmailAndSex1(Map<String,Object> param);
    public List<TUser> selectByEmailAndSex2(@Param("email")String email,@Param("sex")Byte sex);
    public List<TUser> selectByEmailAndSex3(EmailSexBean esb);
    public List<TUser> selectBySymbol(@Param("tableName") String tableName,
                               @Param("inCol") String incol,
                               @Param("orderStr") String orderStr,
                               @Param("sex") Byte sex);
    public List<TUser> selectIfOper(@Param("email") String email,@Param("sex") Byte sex);
    public List<TUser> selectIfandWhereOper(@Param("email") String email,@Param("sex") Byte sex);
    public int updateIfOper(TUser record);
    public int updateIdAndSetOper(TUser record);
    public int insertIfOper(TUser record);
    public List<TUser> selectForeach4In(String[] names);
    public int insertForeach4Batch(List<TUser> users);
}
