package cd27;

import java.io.UnsupportedEncodingException;

/**
 * Created by ChenDeng
 * 2018-10-06 18:02
 */
public final class StringReverse {
    private StringReverse() {
        throw new AssertionError();
    }
    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
    
    public static String changeEncoding(String originStr) throws UnsupportedEncodingException {
        return new String(originStr.getBytes("GB2312"), "ISO-8859-1");
    }
    
}
