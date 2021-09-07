package thirdlyclass.mapper;

import thirdlyclass.bean.THealthReportMale;

import java.util.List;

public interface THealthReportMaleMapper {
    public List<THealthReportMale> selectByUserId(Integer id);
    public int deleteByPrimary(Integer id);
    public int insert(THealthReportMale record);
    public int insertSelective(THealthReportMale record);
    public THealthReportMale selectByPrimaryKey(Integer id);
    public int updateByPrimaryKeySelective(THealthReportMale record);
    public int updateByPrimaryKey(THealthReportMale record);
}
