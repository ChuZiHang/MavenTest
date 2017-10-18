package com.lol.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求参数工具类
 * 
 * @author cunxing
 *
 */
public class ParamUtils {

    public static boolean isDevelop = true;

    /**
     * 获得一个String 类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        参数名
     * @return 如果为空，返回null;
     */
    public static String getParameter(HttpServletRequest request, String paramName) {
        return getParameter(request, paramName, "");
    }

    public static String[] getParameterValues(HttpServletRequest request, String paramName) {
        return request.getParameterValues(paramName);
    }

    /**
     * 获得一个String 类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        参数名
     * @param defaultStr
     *        默认值
     * @return 如果从request取到的值为null或"",返回defaultStr,否则返回取到的值;
     */
    public static String getParameter(HttpServletRequest request, String paramName, String defaultStr) {
        String temp = request.getParameter(paramName);
        if(temp != null) {
            if(temp.equals("")) {
                temp = defaultStr;
            }
        } else {
            temp = defaultStr;
        }
        if(!temp.equals("")) {
            if(sqlValidate(temp)) {
                temp = defaultStr;
            }
        }
        return temp;
    }

    /**
     * 获取表单参数的数值。
     * 
     * param escapeHTMLTags 不允许使用htmltag。
     */
    public static String getEscapeHTMLParameter(HttpServletRequest request, String paramName) {
        return escapeHTMLTags(ParamUtils.getParameter(request, paramName, ""));
    }

    /**
     * 清除类似html的tag
     * 
     * @param input
     * @return
     */
    public static final String escapeHTMLTags(String input) {
        // Check if the string is null or zero length -- if so, return
        // what was sent in.
        if(input == null || input.length() == 0) {
            // return input;
            input = "";
        }
        // Use a StringBuffer in lieu of String concatenation -- it is
        // much more efficient this way.
        StringBuilder buf = new StringBuilder(input.length());
        char ch = ' ';
        for(int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if(ch == '<') {
                buf.append("&lt;");
            } else if(ch == '>') {
                buf.append("&gt;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    /**
     * 获得一个boolean类型的参数。.
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * @return True 如果参数是1，则为真，其他情况为false。
     */
    public static boolean getBooleanParameter(HttpServletRequest request, String paramName) {
        String temp = getParameter(request, paramName);
        if(temp != null && temp.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得一个返回值为int的boolean类型的参数。
     * 
     */
    public static int getIntBooleanParameter(HttpServletRequest request, String paramName) {
        int returnInt = 0;
        if(ParamUtils.getBooleanParameter(request, paramName)) {
            returnInt = 1;
        }
        return returnInt;
    }

    /**
     * 获得一个Double类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * @return int数值是从参数中获得，如果为空，则为一个默认值。
     * 
     */
    public static double getDoubleParameter(HttpServletRequest request, String paramName, double defaultNum) {
        String temp = getParameter(request, paramName);
        if(temp != null && !temp.equals("")) {
            double num = defaultNum;
            try {
                num = Double.parseDouble(temp);
            } catch(Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * 获得一个Double类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * 
     */
    public static double getDoubleParameter(HttpServletRequest request, String paramName) {
        return getDoubleParameter(request, paramName, 0.00);
    }

    /**
     * 获得一个int类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * @return int数值是从参数中获得，如果为空，则为一个默认值。
     * 
     */
    public static int getIntParameter(HttpServletRequest request, String paramName, int defaultNum) {
        String temp = getParameter(request, paramName);
        if(temp != null && !temp.equals("")) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch(Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * 获得一个int类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * 
     */
    public static int getIntParameter(HttpServletRequest request, String paramName) {
        return getIntParameter(request, paramName, 0);
    }

    /**
     * 获得一个日期类型。要求必须满足yyyy|MM|dd|HH|mm|ss的格式。 其中|可为任意字符。
     * 
     * @param request
     * @param paramName
     * @return
     */
    public static Date getDateParameter(HttpServletRequest request, String paramName) {
        return getDateParameter(request, paramName, null);
    }

    /**
     * 获得一个日期类型。要求必须满足yyyy|MM|dd|HH|mm|ss的格式。 其中|可为任意字符。
     * 
     * @param request
     * @param paramName
     * @return
     */
    public static Date getDateParameter(HttpServletRequest request, String paramName, Date defaultDate) {
        String temp = getParameter(request, paramName);
        if(temp != null && !temp.equals("")) {
            Date num = defaultDate;
            try {
                num = parseDate(temp);
            } catch(Exception ignored) {
            }
            return num;
        } else {
            return defaultDate;
        }
    }

    /**
     * 获得一个Long类型的参数。
     * 
     * @paramName request The HttpServletRequest object, known as "request" in a JSP page.
     * @paramName paramName 你想获得的参数名
     * @paramName defaultNum 默认值
     * @return 返回指定的long值
     */
    public static long getLongParameter(HttpServletRequest request, String paramName, long defaultNum) {
        String temp = getParameter(request, paramName);
        if(temp != null && !temp.equals("")) {
            try {
                defaultNum = Long.parseLong(temp);
            } catch(Exception ignored) {
               // System.out.println("ParamUtils's method:getLongParameter accured error:" + ignored);
            }
        }
        return defaultNum;
    }

    /**
     * 获得一个int类型的参数。
     * 
     * @param request
     *        The HttpServletRequest object, known as "request" in a JSP page.
     * @param paramName
     *        你想获得参数。
     * 
     */
    public static long getLongParameter(HttpServletRequest request, String paramName) {
        return getLongParameter(request, paramName, 0);
    }

    /**
     * 获取refererPage
     * 
     * @param request
     * @return
     */
    public static String getRefererURL(HttpServletRequest request, boolean echoHost) {
        String referer = request.getHeader("referer");
        if(referer == null || referer.equals(""))
            return "";
        if(!echoHost) {
            referer = referer.substring(referer.indexOf("://") + 3, referer.length());
            referer = referer.substring(referer.indexOf("/"), referer.length());
        }
        return referer;
    }

    /**
     * 获得自己的url地址
     * 
     * @param request
     * @return
     */
    public static String getSelfURL(HttpServletRequest request, boolean echoHost, boolean echoQueryString) {
        StringBuffer selfurl = new StringBuffer();
        if(echoHost) {
            selfurl.append("http://");
            selfurl.append(request.getServerName());
            if(request.getServerPort() != 80) {
                selfurl.append(":" + request.getServerPort());
            }
        }
        selfurl.append(request.getRequestURI());
        if(request.getQueryString() != null && echoQueryString) {
            selfurl.append("?").append(request.getQueryString());
        }
        return selfurl.toString();
    }

    /**
     * encodeurl
     * 
     * @param param
     * @param encode
     * @return
     */
    public static String encodeParam(String param, String encode) {
        String s = null;
        try {
            s = URLEncoder.encode(param, encode);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * encodeurl，默认用utf-8
     * 
     * @param param
     * @return
     */
    public static String encodeParam(String param) {
        String s = null;
        try {
            s = URLEncoder.encode(param, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * decode参数
     * 
     * @param param
     * @param encode
     * @return
     */
    public static String decodeParam(String param, String encode) {
        String s = null;
        try {
            s = URLDecoder.decode(param, encode);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * decode参数，默认使用utf-8
     * 
     * @param param
     * @return
     */
    public static String decodeParam(String param) {
        String s = null;
        try {
            s = URLDecoder.decode(param, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 解析日期的方法。要求必须满足yyyy|MM|dd|HH|mm|ss的格式。 其中|可为任意字符。
     * 
     * @param text
     * @return
     */
    public static Date parseDate(String text) {
        int yy = 0, MM = 0, dd = 0, HH = 0, mm = 0, ss = 0;
        if(text.length() >= 4) {
            yy = Integer.parseInt(text.substring(0, 4));
        }
        if(text.length() >= 7) {
            MM = Integer.parseInt(text.substring(5, 7));
        }
        if(text.length() >= 10) {
            dd = Integer.parseInt(text.substring(8, 10));
        }
        if(text.length() >= 13) {
            HH = Integer.parseInt(text.substring(11, 13));
        }
        if(text.length() >= 16) {
            mm = Integer.parseInt(text.substring(14, 16));
        }
        if(text.length() >= 19) {
            ss = Integer.parseInt(text.substring(17, 19));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(yy, MM - 1, dd, HH, mm, ss);
        if(yy > 0)
            return calendar.getTime();
        else
            return null;
    }

    /**
     * 防止sql攻击
     * 
     * @param str
     * @return
     */
    public static boolean sqlValidate(String str) {
        str = str.toLowerCase();// 统一转为小写
        String badStr =
            "'|exec|execute|insert|select|delete|update|drop|mid|master|truncate|"
                + "char|declare|--|net user|xp_|exec|execute|insert|or|" + "|group_concat|column_name|"
                + "information_schema.columns|table_schema|";// 过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for(int i = 0; i < badStrs.length; i++) {
            if(badStrs[i].length() > 0 && str.equals(badStrs[i])) {
                return true;
            }
        }
        return false;
    }

    public static void showParams(HttpServletRequest request, String tag) {
        if(!isDevelop) {
            return;
        }
        try {
            request.setCharacterEncoding("utf-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        long show = System.currentTimeMillis();
       // System.out.println("######参数开始####" + tag + "###############" + show);
        String paramName = null;
        Enumeration<?> enumeration = request.getParameterNames();
        while(enumeration.hasMoreElements()) {
            paramName = (String)enumeration.nextElement();
          //  System.out.println("==显示===参数：" + paramName + "===>>" + request.getParameter(paramName));
        }
       // System.out.println("######参数结束####" + tag + "#################" + show);
    }
    
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length());
        }
        return ip;
    }

    public static void main(String[] args) {
        String date = "16-2-50.0-100";
        // sqlValidate(date);
        System.out.println(sqlValidate(date));
    }
}
