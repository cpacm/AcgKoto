package net.cpacm.acgkoto.utils;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * textview的文字监听器
 *
 * @Auther: cpacm
 * @Date: 2016/1/12 0012-上午 10:20
 */
public class TextClickable extends ClickableSpan{
    private final View.OnClickListener mListener;

    public TextClickable(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.RED);       //设置文件颜色
        ds.setUnderlineText(false);      //设置下划线
    }

    @Override
    public void onClick(View widget) {
        mListener.onClick(widget);
    }
}