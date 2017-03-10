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
 * StringUtil.java ,Created by: pengjianbo ,2015-03-14 10:46:54 ,lastModified:2015-03-14 10:46:54
 */

package com.android.mvp.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {

    public static boolean checkString(String str) {
        return (null != str && !TextUtils.isEmpty(str) && !str
                .equalsIgnoreCase("null"));
    }



    public static String replaceAllBlank(String s) {
        if (checkString(s)) {
            s = s.replaceAll(" ", "");
        }
        return s;
    }


}
