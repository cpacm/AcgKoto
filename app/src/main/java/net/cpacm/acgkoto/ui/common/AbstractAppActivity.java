package net.cpacm.acgkoto.ui.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import net.cpacm.core.GlobalApplication;
import net.cpacm.acgkoto.R;


/**
 * @Auther: cpacm
 * @Date: 2015/10/22 0022-下午 2:41
 */
public abstract class AbstractAppActivity extends AppCompatActivity {

    private GlobalApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        app = (GlobalApplication) getApplication();
        app.addActivity(this);
        setContentView(R.layout.activity);
    }

    /**
     * 友盟统计分析
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.removeActivity(this);
    }

    /**
     * jump to another activity
     *
     * @param activity
     * @param isFinish
     */
    public void jumpToActivity(Class activity, boolean isFinish) {
        jumpToActivity(activity, isFinish, new Intent());
    }

    public void jumpToActivity(Class activity, boolean isFinish, Intent intent) {
        intent.setClass(this, activity);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_slide_left, R.anim.activity_slide_right);
        if (isFinish) {
            finish();
        }
    }

    /**
     * 获取需要替换的Fragement的ID
     *
     * @return
     */
    public int getReplaceFragmentId() {
        return R.id.main_fragment;
    }


    /**
     * 全局通用，点击隐藏输入框<br/>
     * click hide softInput
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定是否需要隐藏，编辑框内不隐藏
     * judge whether hide softInput
     *
     * @param v
     * @param ev
     * @return
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     *
     * @param token
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
