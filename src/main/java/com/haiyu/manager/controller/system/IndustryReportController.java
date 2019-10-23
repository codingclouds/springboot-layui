package com.haiyu.manager.controller.system;

import com.haiyu.manager.dto.LogSearchDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ Author     : wzt.
 * @ Description: 行业智库/报告
 * @ Date       : Created in 19-10-23
 * @ Modified By:
 * @ Version    : v1.0
 */
@Controller
@RequestMapping("mkt")
public class IndustryReportController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

    @ApiIgnore
    @RequestMapping("/industry_trends")
    public String industryTrends() {
        return "/mkt/industry_trends";
    }

    @ApiIgnore
    @RequestMapping("/industry_collect")
    public String industryCollect() {
        return "/mkt/industry_collect";
    }

    @ApiIgnore
    @RequestMapping("/industry/first_level_industry")
    public String industryFirstLevel() {
        return "/mkt/industry/first_level_industry";
    }
    @ApiIgnore
    @RequestMapping("/industry/second_level_industry")
    public String industrySecondLevel() {
        return "/mkt/industry/second_level_industry";
    }
    /**
     *
     * 功能描述: 分页查询操作日志列表
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/21 11:10
     */
//    @Log(busi = BusiType.LOG_MANAGE,operType = OperType.QUERY,record = false)
    @ApiOperation(value = "分页查询日志（条件）",notes = "")
    @PostMapping(value = "/getLogList")
    @ResponseBody
    public PageDataResult getLogList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ LogSearchDTO logSearch) {
        logger.info("分页查询操作日志列表！搜索条件：logSearch：" + logSearch + ",pageNum:" + pageNum + ",每页记录数量 pageSize:" + pageSize);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = logService.getLogList(logSearch, pageNum ,pageSize);
            logger.info("操作日志列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("操作日志列表查询异常！", e);
        }
        return pdr;
    }
}
