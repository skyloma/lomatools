package lomasky.ma.xui;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author wangli
 */
  class StringUtil {

    /**
     * 判断是否为空 为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");

    }

    /**

     *   返回一个显示的数据(PS:为空返回"")
     */
    public static String returnShow(String str) {
        if (str != null && !str.equals("null"))
            return str;
        else
            return "";
    }


    public static String valueOf(Object object) {
        if (object != null) {
            return String.valueOf(object)
                    ;
        } else {
            return "";
        }

    }













    //判断http
    public static boolean isHttp(String addr) {

        String rexp = "(\"http://(([a-zA-z0-9]|-){1,}\\\\.){1,}[a-zA-z0-9]{1,}-*\")";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        return mat.find();
    }
}
