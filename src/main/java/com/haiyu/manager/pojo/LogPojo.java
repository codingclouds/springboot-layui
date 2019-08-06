package com.haiyu.manager.pojo;

import javax.persistence.Table;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table log
 */
@Table(name = "log")
public class LogPojo {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   用户名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   角色
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.role_name
     *
     * @mbg.generated
     */
    private String roleName;

    /**
     * Database Column Remarks:
     *   用户IP
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     * Database Column Remarks:
     *   业务模块
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.busi
     *
     * @mbg.generated
     */
    private String busi;

    /**
     * Database Column Remarks:
     *   操作类型：1-增；2-查；删-3；改-4；
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.oper_type
     *
     * @mbg.generated
     */
    private Integer operType;

    /**
     * Database Column Remarks:
     *   调用方法
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.method
     *
     * @mbg.generated
     */
    private String method;

    /**
     * Database Column Remarks:
     *   开始时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.start_time
     *
     * @mbg.generated
     */
    private String startTime;

    /**
     * Database Column Remarks:
     *   结束时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.end_time
     *
     * @mbg.generated
     */
    private String endTime;

    /**
     * Database Column Remarks:
     *   状态：0-无效；1-有效；
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log.status_cd
     *
     * @mbg.generated
     */
    private Integer statusCd;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.id
     *
     * @return the value of log.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.id
     *
     * @param id the value for log.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.user_name
     *
     * @return the value of log.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.user_name
     *
     * @param userName the value for log.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.role_name
     *
     * @return the value of log.role_name
     *
     * @mbg.generated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.role_name
     *
     * @param roleName the value for log.role_name
     *
     * @mbg.generated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.ip
     *
     * @return the value of log.ip
     *
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.ip
     *
     * @param ip the value for log.ip
     *
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.busi
     *
     * @return the value of log.busi
     *
     * @mbg.generated
     */
    public String getBusi() {
        return busi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.busi
     *
     * @param busi the value for log.busi
     *
     * @mbg.generated
     */
    public void setBusi(String busi) {
        this.busi = busi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.oper_type
     *
     * @return the value of log.oper_type
     *
     * @mbg.generated
     */
    public Integer getOperType() {
        return operType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.oper_type
     *
     * @param operType the value for log.oper_type
     *
     * @mbg.generated
     */
    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.method
     *
     * @return the value of log.method
     *
     * @mbg.generated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.method
     *
     * @param method the value for log.method
     *
     * @mbg.generated
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.start_time
     *
     * @return the value of log.start_time
     *
     * @mbg.generated
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.start_time
     *
     * @param startTime the value for log.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.end_time
     *
     * @return the value of log.end_time
     *
     * @mbg.generated
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.end_time
     *
     * @param endTime the value for log.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log.status_cd
     *
     * @return the value of log.status_cd
     *
     * @mbg.generated
     */
    public Integer getStatusCd() {
        return statusCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log.status_cd
     *
     * @param statusCd the value for log.status_cd
     *
     * @mbg.generated
     */
    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }
}