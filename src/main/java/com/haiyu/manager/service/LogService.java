package com.haiyu.manager.service;/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:45 2019/8/8
 * @ Description: 日志业务类
 * @ Modified By:
 * @ Version    : 1.0$
 */

import com.haiyu.manager.dto.LogSearchDTO;
import com.haiyu.manager.response.PageDataResult;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:45 2019/8/8
 * @ Description: 日志业务类
 * @ Modified By:
 * @ Version    : 1.0$
 */
public interface LogService {

    public PageDataResult getLogList(LogSearchDTO logSearchDTO, Integer pageNum, Integer pageSize);

}
