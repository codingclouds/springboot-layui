package com.haiyu.manager.dao;

import com.haiyu.manager.dto.LogDTO;
import com.haiyu.manager.dto.LogSearchDTO;
import com.haiyu.manager.pojo.LogPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log
     *
     * @mbg.generated
     */
    int insert(LogPojo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log
     *
     * @mbg.generated
     */
    LogPojo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log
     *
     * @mbg.generated
     */
    List<LogPojo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LogPojo record);


    Integer getLogListCount(LogSearchDTO logSearchDTO);

    List<LogDTO> getLogList(LogSearchDTO logSearchDTO);


}