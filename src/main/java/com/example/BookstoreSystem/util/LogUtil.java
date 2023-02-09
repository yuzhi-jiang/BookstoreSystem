package com.example.BookstoreSystem.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName LogUtil.java
 * @Description TODO
 * @createTime 2022年05月21日 11:06:00
 */
public class LogUtil {
    private static final String TAG = "BookstoreSystem";
    private static final Log log = LogFactory.get();

    public static void debug(Object msg, Object... args) {
        log.debug((String) msg.toString(), args);
    }

    public static void info(Object msg, Object... args) {

        log.info((String)msg.toString(), args);
    }

    public static void warn(Object msg, Object... args) {
        log.warn((String)msg.toString(), args);
    }

    public static void error(Object msg, Object... args) {
        log.error((String)msg.toString(), args);
    }

}
