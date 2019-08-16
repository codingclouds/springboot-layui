package com.haiyu.manager.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 15:40 2019/8/1
 * @ Description:
 * @ Modified By:
 * @ Version    : $
 */
@ApiIgnore
@Controller
@RequestMapping("new")
public class NewController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/toutiaoNews")
    public String toutiaoNews() {
        return "/new/toutiaoNews";
    }
}
