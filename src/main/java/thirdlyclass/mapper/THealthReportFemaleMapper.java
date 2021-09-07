package thirdlyclass.mapper;

import thirdlyclass.bean.HealthReport;
import thirdlyclass.bean.THealthReportFemale;

import java.util.List;

public interface THealthReportFemaleMapper {
    public List<THealthReportFemale> selectByUserId(Integer userId);
    public int deleteByPrimaryKey(Integer id);
    public int insert(THealthReportFemale record);
    public int insertSelective(THealthReportFemale record);
    public THealthReportFemale selectByPrimaryKey(Integer id);
    public int updateByPrimaryKeySelective(THealthReportFemale record);
    public int updateByPrimaryKey(THealthReportFemale record);
}
