package secondclass.mapper;

import secondclass.bean.TJobHistory;

import java.util.List;

public interface TJobHistoryMapper {
    public int deleteByPrimaryKey(Integer id);
    public int insert(TJobHistory record);
    public int insertSelective(TJobHistory record);
    public TJobHistory selectByPrimaryKey(Integer id);
    public int updateByPrimaryKeySelective(TJobHistory record);
    public int updateByPrimaryKey(TJobHistory record);
    public List<TJobHistory> selectByUserId(int userId);
}
