package com.haiyu.manager.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @Title: UserSearchDTO
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/21 11:19
 */
@Data
public class UserSearchDTO implements Serializable {
    private String sysUserName;

    private String userPhone;

    private String startTime;

    private String endTime;

}
