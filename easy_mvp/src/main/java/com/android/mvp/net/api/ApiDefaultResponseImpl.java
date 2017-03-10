package com.android.mvp.net.api;

import android.util.Log;

import com.android.mvp.model.BaseResponse;
import com.android.mvp.net.okhttp.RequestParams;
import com.android.mvp.net.okhttp.ResponseData;
import com.android.mvp.utils.Constants;

/**
 * Created by chenkaining on 2016/8/19.
 */
public class ApiDefaultResponseImpl extends Api{

    //public RequestParams params;

    private String base_url = "https://api.douban.com/v2";

    public String method = "";

    public RequestParams.ConnType type = RequestParams.ConnType.POST;

    public ApiDefaultResponseImpl(String method,RequestParams params){
        this.method = method;
        this.params = params;
    }

    public ApiDefaultResponseImpl(RequestParams params){
        this.params = params;
    }


    @Override protected ResponseData getResponseData() {
        Log.d("Test",params.connType.name());
        if(params.connType.name().equals(RequestParams.ConnType.GET.name())){
            return ApiConnection.createGet(base_url+method,params).startConnect();
        }
        return ApiConnection.createPOST(base_url+method,params,timeout).startConnect();
    }


    public void setType(RequestParams.ConnType type) {
        this.type = type;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
