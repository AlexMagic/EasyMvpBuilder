package com.android.mvp.net.api;

import android.text.TextUtils;

import com.android.mvp.net.okhttp.DataType;
import com.android.mvp.net.okhttp.RequestParams;
import com.android.mvp.net.okhttp.ResponseData;
import com.android.mvp.utils.Constants;
import com.android.mvp.utils.Log;
import com.android.mvp.utils.Logger;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenkaining on 2016/8/9.
 */
public class ApiConnection {

    private String url;
    private RequestParams params;


    private int timeout;
    private Headers headers;

    private HashMap<String ,String > header_map = null;

    private ApiConnection(String url,RequestParams params,int timeout){
        this.url = url;
        this.params = params;
        this.timeout = timeout;

        headers = getHeaders(params.getDataType());
    }

    public static ApiConnection createPOST(String url,RequestParams params,int timeout){
        if(params==null){
            params = new RequestParams(DataType.BODY);
        }
        return new ApiConnection(url,params,timeout);
    }

    public static ApiConnection createGet(String url,RequestParams params){
        url += params.getListParams();
        return new ApiConnection(url,params,Constants.REQ_TIMEOUT);
    }



    public ResponseData startConnect(){
        Log.d("startConnect -- ");
        OkHttpClient client = new OkHttpClient();

        client.setConnectTimeout(timeout, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(timeout, TimeUnit.MILLISECONDS);
        client.setReadTimeout(timeout, TimeUnit.MILLISECONDS);
        client.setRetryOnConnectionFailure(false);
        client.setFollowRedirects(true);

        ResponseData responseData = new ResponseData();
        RequestBody body = params.getRequestBody();
        Request.Builder builder = new Request.Builder();
        builder.url(url).headers(headers);
        if(params.connType == RequestParams.ConnType.POST) {
            if (body != null) {
                builder.post(body);
            }
        }else if(params.connType == RequestParams.ConnType.GET){
            builder.get();
        }
//
        Request request = builder.build();
        if (Constants.DEBUG) {
            Logger.d("url="
                + url
                + "\n params="
                + params.toJSON());
        }

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            if (e instanceof SocketTimeoutException) {
                responseData.setTimeout(true);
            } else if (e instanceof InterruptedIOException && TextUtils.equals(e.getMessage(),
                "timeout")) {
                //to see at okio.AsyncTimeout.exit(AsyncTimeout.java:249)
                responseData.setTimeout(true);
            }
        }

        if (response != null) {
            responseData.setResponseNull(false);
            responseData.setCode(response.code());
            responseData.setMessage(response.message());

            responseData.setSuccess(response.isSuccessful());
            String respBody = "";
            try {
                respBody = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            responseData.setResponse(respBody);
            responseData.setHeaders(response.headers());
        } else {
            responseData.setResponseNull(true);
        }
        params.clearMap();
        return responseData;
    }


    private Headers getHeaders(DataType dataType) {
        HashMap<String, String> headerMap = getHeader_map();
        headerMap.put("charset", "UTF-8");


        Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (!Util.checkString(entry.getValue()) || !Util.checkString(entry.getKey())) {
                iterator.remove();
            }
        }

        Headers headers = Headers.of(headerMap);
        return headers;
    }

    public HashMap<String, String> getHeader_map() {
        if(header_map == null){
            return new HashMap<String,String>();
        }
        return header_map;
    }

    public void setHeader(String key, String value){
        if(header_map==null){
            header_map = new HashMap<>();
        }

        header_map.put(key,value);
    }

}
