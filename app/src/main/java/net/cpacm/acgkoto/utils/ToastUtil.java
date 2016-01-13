package net.cpacm.acgkoto.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by cpacm on 2015/8/31 0031.
 */
public class ToastUtil {

    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
            mToast = null;//toast隐藏后，将其置为null
        }
    };

    public static void showShortToast(Context context, String message) {
        mHandler.removeCallbacks(r);
        if(context == null) return;
        if (mToast == null ) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();
    }

    public static void showShortToast(Context context, String message, int gravity) {
        mHandler.removeCallbacks(r);
        if(context == null) return;
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.setGravity(gravity, 0, 0);
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();
    }

    public static void showLongToast(Context context, String message) {
        mHandler.removeCallbacks(r);
        if(context == null) return;
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        }
        mHandler.postDelayed(r, 2000);//延迟1秒隐藏toast
        mToast.show();
    }

    public static void showLongToast(Context context, String message, int gravity) {
        mHandler.removeCallbacks(r);
        if(context == null) return;
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            mToast.setGravity(gravity, 0, 0);
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();
    }
}
