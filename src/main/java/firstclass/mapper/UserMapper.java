package firstclass.mapper;

import firstclass.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 根据id查询记录
     * @param id 对应数据库记录的id
     * @return 对应记录的对象
     */
    public User getUserByPrimaryKey(@Param("id") Integer id);
}
