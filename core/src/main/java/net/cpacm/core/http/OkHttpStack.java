package net.cpacm.core.http;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 使用okHttp代替原来的HurlStack</br>
 * 解决Volley部分的偶发性问题——volley.NoConnectionError</br>
 *
 * @Auther: cpacm
 * @Date: 2015/12/5 0005-下午 7:20
 */
public class OkHttpStack extends HurlStack {

    private OkUrlFactory mFactory;

    public OkHttpStack() {
        this(new OkHttpClient());
    }

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        mFactory = new OkUrlFactory(client);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return mFactory.open(url);
    }

}
