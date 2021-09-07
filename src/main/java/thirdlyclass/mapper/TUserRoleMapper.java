package thirdlyclass.mapper;

import secondclass.bean.TUserRoleKey;

public interface TUserRoleMapper {
    public int deleteByPrimaryKey(TUserRoleKey key);
    public int insert(TUserRoleKey record);
    public int insertSelective(TUserRoleKey record);
}
