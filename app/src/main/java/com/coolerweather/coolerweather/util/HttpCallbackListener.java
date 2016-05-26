package com.coolerweather.coolerweather.util;

/**
 * Created by guo on 2016/5/25.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
