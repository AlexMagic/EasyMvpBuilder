package com.android.mvp.interactor;

import com.android.mvp.model.BaseResponse;
import com.android.mvp.net.api.ApiDefaultResponseImpl;
import com.android.mvp.net.okhttp.RequestParams;
import com.android.mvp.net.okhttp.ResponseData;
import com.android.mvp.utils.ResponeDataUtil;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by chenkaining on 2016/8/19.
 */
public class DefaultUseCase<T extends BaseResponse> extends UseCase{

    private ApiDefaultResponseImpl apiDefaultResponse;

    public DefaultUseCase(String method , RequestParams params){
        apiDefaultResponse = new ApiDefaultResponseImpl(method , params);
    }

    public DefaultUseCase(RequestParams params){
        apiDefaultResponse = new ApiDefaultResponseImpl(params);
    }

    @Override protected Observable buildUseCaseObservable(final Class clz) {
        return apiDefaultResponse.observableResponseData()
                .map(new Func1<ResponseData,T>() {
                    @Override
                    public T call(ResponseData responseData) {
                        if(responseData != null){
                            return (T) ResponeDataUtil.parseResultJson(
                                    responseData.getResponse(), clz);
                        }
                        return null;
                    }
                });
    }

}
