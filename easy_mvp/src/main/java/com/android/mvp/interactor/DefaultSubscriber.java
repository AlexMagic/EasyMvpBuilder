package com.android.mvp.interactor;

import android.content.Context;

import com.android.mvp.model.BaseResponse;
import com.android.mvp.net.okhttp.DataType;
import com.android.mvp.net.okhttp.ResponseData;
import com.android.mvp.utils.Constants;
import com.android.mvp.utils.Logger;

import rx.Subscriber;

/**
 * Created by chenkaining on 2016/8/8.
 */
public class DefaultSubscriber<T extends BaseResponse> extends Subscriber<T>{


    private Context context;

    public DefaultSubscriber(){

    }

    public DefaultSubscriber(Context context){
        this.context = context;
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    private void _onNext(T t){
        handleResponse(t);
    }

    private  void handleResponse(T response){

        BaseResponse baseResponse = null;

        if(response instanceof BaseResponse) baseResponse = (BaseResponse)response;

        if (baseResponse != null) {

            int resultCode = 0;

            switch (resultCode) {
                case 0:
                    onSuccess(response);
                    break;
                default:
                    onFailed(response);
                    break;
            }

        } else {
            onError(new Throwable("网络异常"));
        }
    }

    public void onSuccess(T t) {

    }


    public void onFailed(T t) {

    }

    public void onTimeOut(T response) {

    }

}
