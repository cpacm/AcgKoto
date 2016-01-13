package net.cpacm.core.dao;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import net.cpacm.core.GlobalApplication;
import net.cpacm.core.R;
import net.cpacm.core.http.VolleyCode;
import net.cpacm.core.http.VolleyHttpListener;
import net.cpacm.core.http.VolleyManager;
import net.cpacm.core.utils.ACGLogger;

import java.util.Map;

/**
 * 请求数据的父类，同时用来处理token过期时的情况
 *
 * @Auther: cpacm
 * @Date: 2015/11/3 0003-下午 4:01
 */
public abstract class BaseDao<T> extends ApiResponse<T> {


    /**
     * 请求编号，用于识别请求<br/>
     * request code
     */
    private VolleyCode volleyCode;

    /**
     * 请求成功回调监听器<br/>
     */
    private VolleyHttpListener listener;

    /**
     * 请求地址<br/>
     */
    private String url;

    /**
     * 请求成功后统一处理，当token过期时使用刷新token进行刷新，当刷新token过期时，需要用户重新登录<br/>
     */
    public void onSuccess() {
        if (getCode() == CodeUtil.COMPLETE) {
            if (listener != null)
                listener.onHttpResponse(volleyCode, this);
        } else {
            if (listener != null)
                listener.onHttpResponseFail(volleyCode, getMessage());
        }
    }

    /**
     * 请求失败时，返回服务器连接失败的信息<br/>
     */
    public void onFail() {
        if (listener != null)
            listener.onHttpResponseFail(volleyCode, GlobalApplication.getInstance().getString(R.string.http_fail));
    }

    /**
     * 通过设置好的参数生成一个request请求，默认StringRequest<br/>
     *
     * @return
     */
    public Request getRequest() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                parseData(json);
                onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ACGLogger.v("错误" + volleyError.toString());
                onFail();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return getUploadMap();
            }
        };
        request.setTag(url);
        return request;
    }

    public void setListener(VolleyHttpListener listener) {
        this.listener = listener;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVolleyCode(VolleyCode code) {
        this.volleyCode = code;
    }

    public VolleyCode getVolleyCode() {
        return volleyCode;
    }

    public String getUrl() {
        return url;
    }

    public VolleyHttpListener getListener() {
        return listener;
    }

    /**
     * 请求摧毁时结束监听<br/>
     */
    public void destroy() {
        if (url != null)
            VolleyManager.getInstance().getRequestQueue().cancelAll(url);
    }

    /**
     * 需要传入的参数表<br/>
     * access_token一定要在这里放入，防止刷新token后的变动<br/>
     *
     * @return
     */
    public abstract Map<String, String> getUploadMap();


    /**
     * 对返回的数据进行处理<br/>
     *
     * @param json
     */
    public abstract void parseData(String json);
}
