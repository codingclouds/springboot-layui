package com.haiyu.manager.dto;/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:15 2019/8/7
 * @ Description: 日志查询条件DTO
 * @ Modified By:
 * @ Version    : 1.0$
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 11:15 2019/8/7
 * @ Description: 日志查询条件DTO
 * @ Modified By:
 * @ Version    : 1.0$
 */

@Data
public class LogSearchDTO implements Serializable {

    private String userName;

    private Integer operType;

    private String busi;

    private String startTime;

    private String endTime;

}
