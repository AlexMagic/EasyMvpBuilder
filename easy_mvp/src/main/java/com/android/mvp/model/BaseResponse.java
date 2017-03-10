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
 * BaseResponse.java ,Created by: pengjianbo ,2015-05-20 16:30:45 ,lastModified:2015-05-20 16:30:45
 */

package com.android.mvp.model;

import java.io.Serializable;

/**
 *
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private int resultCode;
    private String errorCode;
    private String errorDesc;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

}

