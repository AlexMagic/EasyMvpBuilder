/*
 * Copyright (c) 2011-2015. ShenZhen iBOXPAY Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBoxPay Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBoxpay inc.
 */

package com.android.mvp.utils;

public class Log {

    private final static int LOG_LEVEL_VERBOSE = 0;
    private final static int LOG_LEVEL_DEBUG = 1;
    private final static int LOG_LEVEL_INFO = 2;
    private final static int LOG_LEVEL_WARNING = 3;
    private final static int LOG_LEVEL_ERROR = 4;

    private static int LOG_SHOW_LEVEL = Constants.DEBUG ? LOG_LEVEL_VERBOSE : LOG_LEVEL_ERROR;
    public static void setDebugMode(int level) {
        LOG_SHOW_LEVEL = level;
    }

    protected static final String TAG = "MiniCashBoxLog";

    /**
     * Send a VERBOSE log message.
     *
     * @param msg The message you would like logged.
     */
    public static void v(String msg) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_VERBOSE) {
            android.util.Log.v(TAG, buildMessage(msg));
        }
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void v(String msg, Throwable thr) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_VERBOSE) {
            android.util.Log.v(TAG, buildMessage(msg), thr);
        }

    }

    /**
     * Send a DEBUG log message.
     *
     * @param msg The message you would like logged.
     */
    public static void d(String msg) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_DEBUG) {
            android.util.Log.d(TAG, buildMessage(msg));
        }

    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void d(String msg, Throwable thr) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_DEBUG) {
            android.util.Log.d(TAG, buildMessage(msg), thr);
        }

    }

    /**
     * Send an INFO log message.
     *
     * @param msg The message you would like logged.
     */
    public static void i(String msg) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_INFO) {
            android.util.Log.i(TAG, buildMessage(msg));
        }

    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void i(String msg, Throwable thr) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_INFO) {
            android.util.Log.i(TAG, buildMessage(msg), thr);
        }

    }

    /**
     * Send a WARN log message.
     *
     * @param msg The message you would like logged.
     */
    public static void w(String msg) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_WARNING) {
            android.util.Log.w(TAG, buildMessage(msg));
        }
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void w(String msg, Throwable thr) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_WARNING) {
            android.util.Log.w(TAG, buildMessage(msg), thr);
        }
    }

    /**
     * Send an ERROR log message.
     *
     * @param msg The message you would like logged.
     */
    public static void e(String msg) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_ERROR) {
            android.util.Log.e(TAG, buildMessage(msg));
        }
    }


    /**
     * Send an ERROR log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void e(String msg, Throwable thr) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_ERROR) {
            android.util.Log.e(TAG, buildMessage(msg), thr);
        }
    }

    public static void printShorts(short[] shorts) {
        if (LOG_SHOW_LEVEL <= LOG_LEVEL_DEBUG) {
            StringBuffer sb = new StringBuffer();
            for (short s : shorts) {
                sb.append(s).append(",");
            }
            d(sb.toString());
        }
    }

    /**
     * Building Message.
     *
     * @param msg The message you would like logged.
     * @return Message String
     */
    protected static String buildMessage(String msg) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];

        return new StringBuilder().append(caller.getClassName()).append(".").append(
                caller.getMethodName()).append("(): ").append(msg).toString();
    }
}

