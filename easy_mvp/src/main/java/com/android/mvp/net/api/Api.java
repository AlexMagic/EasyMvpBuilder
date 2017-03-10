package com.android.mvp.net.api;

import com.android.mvp.net.okhttp.RequestParams;
import com.android.mvp.net.okhttp.ResponseData;
import com.android.mvp.utils.Constants;
import com.android.mvp.utils.Logger;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by chenkaining on 2016/8/9.
 */
public abstract class Api {

    public RequestParams params;
    ResponseData responseData;
    public int timeout = Constants.REQ_TIMEOUT;

    public ResponseData handleResponseData( RequestParams params,Subscriber subscriber){
        responseData = getResponseData();
        if (!responseData.isResponseNull()) {

            if (responseData.isSuccess()) {
                return responseData;
            } else {
                int code = responseData.getCode();
                String msg = responseData.getMessage();

                if (code == 504) {
                    if (subscriber != null) {
                        if(Constants.DEBUG) {
                            Logger.e("onException " + "line 224: ERROR_RESPONSE_TIMEOUT   network error time out" + " code = " +
                                responseData.getCode() + "message = " + responseData.getMessage());
                        }
                        subscriber.onError(new Throwable(""+code));
                    }

                } else {
                    if (subscriber != null) {
                        if(Constants.DEBUG) {
                            Logger.e("onException " + "line 232: code = " + code + " msg = " + msg);
                        }
                        subscriber.onError(new Throwable(code+""));
                    }
                }
            }
        } else {
            if (responseData.isTimeout()) {
                if (subscriber != null) {
                    if(Constants.DEBUG) {
                        Logger.e("onException " + "line 243: ERROR_RESPONSE_TIMEOUT22   network error time out");
                    }
                    subscriber.onError(new Throwable("-1400"));
                }
            } else {

                if (subscriber != null) {
                    if(Constants.DEBUG) {
                        Logger.e("onException " + "line 254: ERROR_RESPONSE_UNKNOWN http exception"+ " code = " +
                            responseData.getCode() + "message = " + responseData.getMessage());
                    }
                    subscriber.onError(new Throwable("-1300"));
                }
            }
        }

        return responseData;
    }

    protected abstract ResponseData getResponseData();

    public Observable<ResponseData> observableResponseData(){
        return Observable.create(new Observable.OnSubscribe<ResponseData>() {
            @Override public void call(Subscriber<? super ResponseData> subscriber) {

                ResponseData s = handleResponseData(params, subscriber);

                if(s!=null){
                    subscriber.onNext(s);
                    subscriber.onCompleted();
                }else{
                    subscriber.onCompleted();
                }
            }
        });
    }

}
