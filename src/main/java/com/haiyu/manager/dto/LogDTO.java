package com.haiyu.manager.dto;/**
 * @ Author     : wzt.
 * @ Date       : Created in 14:46 2019/8/8
 * @ Description: 日志实体类
 * @ Modified By:
 * @ Version    : 1.0$
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 14:46 2019/8/8
 * @ Description: 日志实体类
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Data
public class LogDTO implements Serializable {

    private String userName;

    private String roleName;

    private String ip;

    private String busi;

    private Integer operType;

    private String method;

    private String startTime;

    private String endTime;

    private String reqData;

    private String respData;
}
