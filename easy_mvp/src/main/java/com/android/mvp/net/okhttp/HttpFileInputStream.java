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
 * SignatureModel.java ,Created by: wangxiunian ,2015-05-05 17:47:30 ,lastModified:2015-05-05 17:47:30
 */

package com.android.mvp.net.okhttp;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 */
public class HttpFileInputStream implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputStream inputStream;
    private String name;
    private long fileSize;

    public HttpFileInputStream(InputStream inputStream, String name, long fileSize) {
        this.inputStream = inputStream;
        this.name = name;
        this.fileSize = fileSize;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
