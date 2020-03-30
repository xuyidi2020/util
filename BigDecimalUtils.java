package com.bgy.tcppt.util;

/***
 *      ┌─┐       ┌─┐
 *   ┌──┘ ┴───────┘ ┴──┐
 *   │                 │
 *   │       ───       │
 *   │  ─┬┘       └┬─  │
 *   │                 │
 *   │       ─┴─       │
 *   │                 │
 *   └───┐         ┌───┘
 *       │         │
 *       │         │
 *       │         │
 *       │         └──────────────┐
 *       │                        │
 *       │                        ├─┐
 *       │                        ┌─┘
 *       │                        │
 *       └─┐  ┐  ┌───────┬──┐  ┌──┘
 *         │ ─┤ ─┤       │ ─┤ ─┤
 *         └──┴──┘       └──┴──┘
 *                神兽保佑
 *               代码无BUG!
 */

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal计算工具类
 * author xusha
 */
public final class BigDecimalUtils {

    public final static BigDecimal TEN_THOUSAND = new BigDecimal("10000");
    public final static BigDecimal ONE_HUNDRED = new BigDecimal("100");
    public final static BigDecimal ZERO = new BigDecimal("0");
    public final static BigDecimal ONE = new BigDecimal("1");

    /**
     * 多个BigDecimal 相加
     * @param param
     * @return
     */
    public static BigDecimal add(Object...param){
        //如果所有的参数都为空，返回空
        boolean isNull = true ;
        for(Object dec :param){
            if(dec != null){
                isNull = false ;
                break;
            }
        }
        if(isNull){
            return null ;
        }

        BigDecimal result = new BigDecimal("0");
        for(Object dec :param){
            if(dec!=null && StringUtils.isNotBlank(dec.toString())){
                result = result.add(getBigDecimal(dec));
            }
        }
        return result;
    }

    /**
     * 第一个参数减去后面的参数
     * @param reduction
     * @param minuend
     * @return
     */
    public static BigDecimal subtract(BigDecimal reduction,BigDecimal...minuend){
        reduction = reduction==null?new BigDecimal("0"):reduction;

        for(BigDecimal dec:minuend){
            if(dec!=null){
                reduction = reduction.subtract(dec);
            }
        }
        return reduction;
    }

    /**
     * author xusha
     * 将数字字符转换成百分比
     * @param param 转换参数
     * @param digit 保留位数 null 小数位数不做处理
     * @return
     */
    public static String conversionPercentage(String param,Integer digit){
        String result = null;
        BigDecimal paramPercentage = new BigDecimal(param);
        if(digit!=null){

            return paramPercentage.multiply(new BigDecimal("100")).setScale(digit)+"";
        }else{
            return paramPercentage.multiply(new BigDecimal("100"))+"";

        }
    }



    public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null && StringUtils.isNotBlank(value.toString())) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }

    /**
     * 乘法
     * author xusha
     * @param bonusValue
     * @param bigDecimal
     * @return
     */
    public static BigDecimal multiply(Object bonusValue, BigDecimal bigDecimal) {
        BigDecimal bigDecimal1 = getBigDecimal(bonusValue);
        return  bigDecimal1.multiply(bigDecimal);
    }

    /**
     * 除法
     * author
     * @param bonusValue
     * @param divisor
     * digits 小数位数
     * @return
     */
    public static BigDecimal division(Object bonusValue, BigDecimal divisor,int digits) {
        if(bonusValue == null){
            return null;
        }
        BigDecimal bigDecimal1 = getBigDecimal(bonusValue);
        return bigDecimal1.divide(divisor,digits,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 小数位截取
     * author
     * @param bonusValue
     * @param divisor
     * digits 小数位数
     * @return
     */
    public static BigDecimal digitsInterception(Object bonusValue,int digits ) {
        if(bonusValue == null){
            return null;
        }
        BigDecimal bigDecimal1 = getBigDecimal(bonusValue);
        return bigDecimal1.divide(new BigDecimal("1"),digits,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 返回百分占比
     * @param percentage
     * @return
     */
    public static String getPercentage(Object percentage){
        if(percentage == null){
            return null;
        }
        BigDecimal multiply = multiply(percentage, ONE_HUNDRED).stripTrailingZeros();
        return multiply.toPlainString()+"%";
    }

    public static void main(String[] args) {
        System.out.println(getPercentage(12));
    }
}
