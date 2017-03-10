package com.android.mvp.net.okhttp;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.android.mvp.utils.Constants;
import com.android.mvp.utils.ContentType;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestParams {
    public static final String REQUEST_SERIAL_FLAG = "_t";
    private DataType dataType;
    public ConnType connType = ConnType.POST;
    protected ConcurrentHashMap<String, String> urlParams;
    protected ConcurrentHashMap<String, FileWrapper> fileParams;
    protected JsonObject mJsonBody;

    public RequestParams(DataType dataType) {
        this.dataType = dataType;
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    public RequestParams(DataType dataType,ConnType connType) {
        this.dataType = dataType;
        this.connType = connType;
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    public RequestParams(){
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }

    public RequestParams(ConnType connType) {
        this.connType = connType;
        urlParams = new ConcurrentHashMap<String, String>();
        fileParams = new ConcurrentHashMap<String, FileWrapper>();
    }
    public DataType getDataType() {
        return this.dataType;
    }

    /**
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        if (Util.checkString(key) && Util.checkString(value)) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, int value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, float value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, double value) {
        put(key, String.valueOf(value));
    }

    public void put(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    public void put(JsonObject jsonBody) {
        mJsonBody = jsonBody;
    }

    /**
     * @param key
     * @param file
     */
    public void put(String key, File file) {
        if (file == null || !file.exists() || file.length() == 0) {
            return;
        }

        try {
            boolean isPng = file.getName().lastIndexOf("png") > 0 || file.getName().lastIndexOf("PNG") > 0;
            if (isPng) {
                put(key, new HttpFileInputStream(new FileInputStream(file), file.getName(), file.length()), ContentType.PNG);
            }

            boolean isJpg = file.getName().lastIndexOf("jpg") > 0 || file.getName().lastIndexOf("JPG") > 0;
            if (isJpg) {
                put(key, new HttpFileInputStream(new FileInputStream(file), file.getName(), file.length()), ContentType.JPEG);
            }

            if (!isPng && !isJpg) {
                put(key, new HttpFileInputStream(new FileInputStream(file), file.getName(), file.length()), ContentType.TEXT);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void put(String key, HttpFileInputStream httpFileInputStream) {
        put(key, httpFileInputStream, ContentType.PNG);
    }

    public void put(String key, HttpFileInputStream stream, ContentType contentType) {
        if (Util.checkString(key) && stream != null) {
            fileParams.put(key, new FileWrapper(stream.getInputStream(), stream.getName(), contentType, stream.getFileSize()));
        }
    }

    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public ContentType contentType;
        private long fileSize;

        public FileWrapper(InputStream inputStream, String fileName, ContentType contentType, long fileSize) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
            this.fileSize = fileSize;
        }

        public String getFileName() {
            if (fileName != null) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }

    public void clearMap() {
        urlParams.clear();
        fileParams.clear();
        mJsonBody = null;
    }

    public String toJSON() {
        String rsStr = null;
        if (mJsonBody != null) {
            rsStr = mJsonBody.toString();
        } else {
            rsStr = new JSONObject(urlParams).toString();
        }
        return rsStr;
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }


    private String toNormalRequestJson() {
        String paramsJson = toJSON();
        clearMap();
        return toJSON();
    }

    public RequestBody getRequestBody() {
        RequestBody body = null;
        if (dataType == DataType.BODY) {

            MediaType mediaTypeJSON
                    = MediaType.parse("application/json; charset=" + Constants.ENCODING);
            body = RequestBody.create(mediaTypeJSON, toNormalRequestJson());
        } else if (dataType == DataType.MULTIPAR) {
            boolean hasData = false;

            MultipartBuilder builder = new MultipartBuilder();
            builder.type(MultipartBuilder.FORM);
            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
                hasData = true;
            }

            for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
                FileWrapper file = entry.getValue();
                if (file.inputStream != null) {
                    hasData = true;
                    //builder.addFormDataPart(entry.getKey(), file.getFileName(), new IORequestBody(file.contentType, file.fileSize, file.inputStream));
                }
            }
            if (hasData) {
                body = builder.build();
            }
        } else if (dataType == DataType.JSON) {
            MediaType mediaTypeJSON
                    = MediaType.parse("application/json; charset=" + Constants.ENCODING);
            body = RequestBody.create(mediaTypeJSON, toJSON());

        }else {
            FormEncodingBuilder builder = new FormEncodingBuilder();
            boolean hasData = false;
            for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
                hasData = true;
            }
            if (hasData) {
                body = builder.build();
            }
        }

        return body;
    }

    public String getListParams(){
        String res = "";

        if(urlParams!=null && urlParams.size()>0) {
            for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                if (!TextUtils.isEmpty(entry.getValue())) {
                    res += "&" + entry.getKey() + "=" + entry.getValue();
                }
            }
        }

        if(res.length()>0){
            return "?"+res.substring(1);
        }

        return res;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }

    public enum ConnType{
        POST,GET;
    }
}
