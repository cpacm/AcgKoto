package net.cpacm.acgkoto.ui.common;

import android.app.Fragment;

import com.caitu99.ifen.umeng.UMAnalyticsManager;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * 友盟统计分析
 * Created by Lion on 2015/7/15.
 */
public abstract class AnalyticsFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        UMAnalyticsManager.getInstance(getActivity()).onPageStart(getFragmentName());
    }

    @Override
    public void onPause() {
        super.onPause();
        UMAnalyticsManager.getInstance(getActivity()).onPageEnd(getFragmentName());
    }

    /**
     * 获取当前Fragement的类名
     *
     * @return
     */
    protected String getFragmentName() {
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());
        return className;
    }

    protected void onAnalyticEvent(String eventId) {
        UMAnalyticsManager.getInstance(getActivity()).onEvent(eventId);
    }

    protected void onAnalyticEvent(String eventId, HashMap<String, String> map) {
        UMAnalyticsManager.getInstance(getActivity()).onEvent(eventId, map);
    }

    /**
     * 获取友盟统计分析需要的map
     *
     * @param type
     * @return
     */
    protected HashMap<String, String> getEventMap(String type) {
        HashMap<String, String> map = new HashMap<>();
        map.put(UMAnalyticsManager.TYPE, type);
        return map;
    }
}
