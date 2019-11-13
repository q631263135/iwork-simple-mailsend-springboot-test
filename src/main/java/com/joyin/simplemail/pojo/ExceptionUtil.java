package com.joyin.simplemail.pojo;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <br/>
 *
 * @author yangchaozheng
 * @date 2019/11/11 11:25
 */
public class ExceptionUtil {
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
