package com.lol.common.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * wenshang
 */
public class IntUtil implements Serializable {
    private static final long serialVersionUID = -3345205828566485102L;

    /**
     * 提供精确的加法运算。
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static int add(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2).intValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1
     *            被减数
     * @param value2
     *            减数
     * @return 两个参数的差
     */
    public static int sub(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).intValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1
     *            被乘数
     * @param value2
     *            乘数
     * @return 两个参数的积
     */
    public static int mul(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).intValue();
    }


    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend
     *            被除数
     * @param divisor
     *            除数
     */
    public static int div(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2).intValue();
    }


    public static void main(String[] args){

        System.out.println(div("10","100"));
        System.out.println(add("10","100"));
        System.out.println(sub("10","100"));
        System.out.println(mul("10","100"));

    }
}