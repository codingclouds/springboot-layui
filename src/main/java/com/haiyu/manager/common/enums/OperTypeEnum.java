package com.haiyu.manager.common.enums;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 10:44 2019/8/5
 * @ Description: 操作类型枚举类
 * @ Modified By:
 * @ Version    : 1.0$
 */
public enum OperTypeEnum {

    //增、查、删、改
    ADD("add",1),
    QUERY("query",2),
    DEL("del",3),
    UPDATE("update",4);

    private String oper;
    private int code;

    private OperTypeEnum(String oper, int code) {
        this.oper = oper;
        this.code = code;
    }

    public String getOper() {
        return oper;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return  this.oper+"_"+this.code;
    }
}
