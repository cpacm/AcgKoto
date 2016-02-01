package net.cpacm.core.http;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Retrofit网络框架
 * Auther: cpacm
 * Date: 2016/2/1 0001
 */
public class RetrofitManager {

    private static RetrofitManager retrofitManager = null;
    private Retrofit retrofit;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpUtil.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }



}
