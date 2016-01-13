package net.cpacm.core.http;

import com.android.volley.VolleyError;

/**
 * volley网络请求监听器
 */
public interface VolleyHttpListener {
    /**
     * http传输成功
     */
    void onHttpResponse(VolleyCode tag, Object obj);

    /**
     * http传输失败
     */
    void onHttpResponseFail(VolleyCode tag, String message);

}
