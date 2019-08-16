package com.haiyu.manager.controller.system;

import com.haiyu.manager.dto.LogSearchDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.LogService;
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
 * @ Date       : Created in 10:56 2019/8/7
 * @ Description: 日志管理
 * @ Modified By:
 * @ Version    : 1.0$
 */
@ApiIgnore
@Controller
@RequestMapping("manage")
public class ManageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

    @RequestMapping("/manage")
    public String manage() {
        return "/manage/manage";
    }


}
