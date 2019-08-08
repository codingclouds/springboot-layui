package com.haiyu.manager.service.impl;/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:46 2019/8/8
 * @ Description: 日志业务实现类
 * @ Modified By:
 * @ Version    : 1.0$
 */

import com.github.pagehelper.PageHelper;
import com.haiyu.manager.dao.LogMapper;
import com.haiyu.manager.dto.LogDTO;
import com.haiyu.manager.dto.LogSearchDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:46 2019/8/8
 * @ Description: 日志业务实现类
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageDataResult getLogList(LogSearchDTO logSearchDTO, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        Integer total = logMapper.getLogListCount(logSearchDTO);
        PageHelper.startPage(pageNum, pageSize);
        List<LogDTO> logDTOList = logMapper.getLogList(logSearchDTO.getOperType(),logSearchDTO.getUserName(),logSearchDTO.getBusi(),logSearchDTO.getStartTime(),logSearchDTO.getEndTime(),(pageNum-1)*pageSize,pageSize);

        if(logDTOList.size() != 0){
            pageDataResult.setList(logDTOList);
            pageDataResult.setTotals(total);
        }

        return pageDataResult;
    }
}
