package net.cpacm.acgkoto.ui.common;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cpacm.acgkoto.R;

/**
 * 基础fragment<br/>
 * onAttach->onCreate->onCreateView->onDestroy->onDetach<br/>
 *
 * @Auther: cpacm
 * @Date: 2015/10/22 0022-下午 4:36
 */
public abstract class BaseFragment extends Fragment {

    private AbstractAppActivity fenActivity;
    private FragmentManager fragmentManager;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.fenActivity = (AbstractAppActivity) activity;
        fragmentManager = getFragmentManager();
    }

    /**
     * 切换fragment
     * @param fragment 目标fragment
     * @param flag true表示可返回到此页面
     */
    public void switchFragment(BaseFragment fragment, boolean flag) {
        if (getActivity().isFinishing()) return;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(
                R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_pop_left_enter,
                R.anim.fragment_slide_pop_left_exit);
        ft.replace(getReplaceFragmentId(), fragment);
        if (flag) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * activity跳转方法
     * @param activity 要跳转的activity
     * @param isFinish 是否finish自身所在的activity
     */
    public void jumpToActivity(Class activity, boolean isFinish) {
        jumpToActivity(activity, isFinish, new Intent());
    }

    public void jumpToActivity(Class activity, boolean isFinish, Intent intent) {
        intent.setClass(this.getActivity(), activity);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_slide_left, R.anim.activity_slide_right);
        if (isFinish) {
            getActivity().finish();
        }
    }

    public AbstractAppActivity getFenActivity() {
        return fenActivity;
    }


    public int getReplaceFragmentId() {
        return getFenActivity().getReplaceFragmentId();
    }

}
