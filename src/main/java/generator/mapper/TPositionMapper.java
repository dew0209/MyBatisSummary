package generator.mapper;

import generator.bean.TPosition;
import generator.bean.TPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    long countByExample(TPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int deleteByExample(TPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int insert(TPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int insertSelective(TPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    List<TPosition> selectByExample(TPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    TPosition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TPosition record, @Param("example") TPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TPosition record, @Param("example") TPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_position
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TPosition record);
}