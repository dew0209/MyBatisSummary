package thirdlyclass.mapper;

import thirdlyclass.bean.TPosition;

public interface TPositionMapper {
    public int deleteByPrimaryKey(Integer id);
    public int insert(TPosition record);
    public int insertSelective(TPosition record);
    public TPosition selectByPrimaryKey(Integer id);
    public int updateByPrimaryKeySelective(TPosition record);
    public int updateByPrimaryKey(TPosition record);
}
