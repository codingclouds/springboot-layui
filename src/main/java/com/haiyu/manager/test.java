package com.haiyu.manager;/**
 * @ Author     : wzt.
 * @ Date       : Created in 17:58 2019/8/15
 * @ Description:
 * @ Modified By:
 * @ Version    : $
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * @ Author     : wzt.
 * @ Date       : Created in 17:58 2019/8/15
 * @ Description: 
 * @ Modified By:
 * @ Version    : $
 */
public class test {
    public static void main(String[] args) {
        DecimalFormat df = null;
        df = new DecimalFormat("##0.00");
        String retStr = df.format(28.025);
        String retStr1 = df.format( 28.035*10);

        System.out.println(retStr);
        System.out.println(retStr1);
        double data = 28;
        //利用字符串格式化的方式实现四舍五入,保留1位小数
        String result = String.format("%.2f",data);
        String result1 = String.format("%.2f",0.0255*10);
        String result2= String.format("%.2f",0.015);
        String result3= String.format("%.2f",-0.015);
        //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”。
        System.out.println(result);//输出3.0
        System.out.println(result1);//输出3.0
        System.out.println(result2);//输出3.0
        System.out.println(result3);//输出3.0
    }
}
