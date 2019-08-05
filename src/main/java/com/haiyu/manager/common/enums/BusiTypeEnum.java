package com.haiyu.manager.common.enums;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:52 2019/8/5
 * @ Description: 业务类型枚举类
 * @ Modified By:
 * @ Version    : 1.0$
 */
public enum BusiTypeEnum {

    //管理员模块、角色模块、新闻模块、权限模块
    ADMIN("admin"),
    ROLE("role"),
    NEWS("news"),
    PREMISSION("premission");

    private String val;

    private BusiTypeEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }
}
