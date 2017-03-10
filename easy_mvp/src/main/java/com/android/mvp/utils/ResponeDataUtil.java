package com.android.mvp.utils;

import com.google.gson.Gson;
import com.android.mvp.model.BaseResponse;

/**
 * Created by chenkaining on 2016/8/10.
 */
public class ResponeDataUtil {

    public static BaseResponse parseResultJson(String result, Class<?> cls){
        if (!StringUtil.checkString(result) || !new JsonValidator().validate(result)) {
            if(Constants.DEBUG) {
                Logger.e("onException " + "line :297 ERROR_RESPONSE_NULL result empty result = " + result);
            }
            return null;
        }

        if (Constants.DEBUG) {
            Logger.d("result=" + JsonFormatUtil.formatJson(result));
        }
        Gson gson = new Gson();
        try {
            Object obj = gson.fromJson(result,cls);

            BaseResponse response;
            if (obj instanceof BaseResponse) {
                response = (BaseResponse) obj;
            } else {
                response = gson.fromJson(result, BaseResponse.class);
            }

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

}
