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
 * 代码所有权属于深圳盒子支付信息技术有限公司
 * ContentType.java ,Created by: pengjianbo ,2015-05-25 19:04:10 ,lastModified:2015-05-25 19:04:10
 */

package com.android.mvp.utils;

public enum ContentType {

    TEXT("text/plain; charset=UTF-8"),
    PNG("image/png; charset=UTF-8"),
    JPEG("image/jpeg; charset=UTF-8");

    private String contentType;

    private ContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
