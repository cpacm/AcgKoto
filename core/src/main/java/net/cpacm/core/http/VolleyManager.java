package net.cpacm.core.http;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import net.cpacm.core.GlobalApplication;
import net.cpacm.core.R;
import net.cpacm.core.dao.BaseDao;
import net.cpacm.core.dao.CodeUtil;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * volley框架管理类<br/>
 * volley singleton
 *
 * @Auther: cpacm
 * @Date: 2015/10/23 0023-上午 9:50
 */
public class VolleyManager {

    private static VolleyManager volleyManager = null;

    private LinkedBlockingQueue<BaseDao> requestWaitQueue;//缓存正在发生的请求

    /**
     * 网络请求队列<br/>
     * network queue
     */
    private RequestQueue requestQueue;

    //单例模式
    private VolleyManager() {
        Context context = GlobalApplication.getInstance().getApplicationContext();
        requestQueue = Volley.newRequestQueue(context.getApplicationContext(), new OkHttpStack());
        requestQueue.start();
        requestWaitQueue = new LinkedBlockingQueue<>();
    }

    /**
     * get volley manager singleton
     *
     * @return
     */
    public static VolleyManager getInstance() {
        if (volleyManager == null) {
            synchronized (VolleyManager.class) {
                if (volleyManager == null) {
                    volleyManager = new VolleyManager();
                }
            }
        }
        return volleyManager;
    }

    public void addRequest(BaseDao baseDao) {
        getRequestQueue().add(baseDao.getRequest());
    }

    public void releaseRequests() {
        while (requestWaitQueue.peek() != null) {
            getRequestQueue().add(requestWaitQueue.poll().getRequest());
        }
    }

    public void clearRequests() {
        requestWaitQueue.clear();
    }

    public void refreshToken(final BaseDao baseDao) {
        addRequest(baseDao);//将当前请求缓存
    }

    /**
     * get volley queue.<br/>
     * All requests will add to this queue
     *
     * @return RequestQueue
     * @see RequestQueue
     */
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

}
