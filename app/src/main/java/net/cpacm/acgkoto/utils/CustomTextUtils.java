package net.cpacm.acgkoto.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;


import java.text.DecimalFormat;

/**
 * 显示文本工具类
 * Created by Lion on 2015/6/25.
 */
public class CustomTextUtils {

    public static String format_0 = "###,##0.00";
    public static String format_1 = "#,###";

    /**
     * 用默认的格式格式化数字
     *
     * @param text 需要格式化的数字
     * @return String
     */
    public static String defFormat(String text) {
        return format(text, format_0);
    }

    /**
     * 按给定格式格式化数字
     *
     * @param text   需要格式化的数字
     * @param format 格式
     * @return String
     */
    public static String format(String text, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.applyPattern(format);
        return decimalFormat.format(Double.valueOf(text));
    }

    /**
     * 对float保留两位小数，返回string
     *
     * @param value
     * @return String
     */
    public static String format(float value) {
        String str = value + "";
        return format(str, format_0);
    }

    /**
     * 获取第一次出现分割符前的字符串
     *
     * @param text  要分割的字符串
     * @param split 分割符
     * @param in    是否包含分隔符
     * @return String
     */
    public static String setSplitBefore(String text, String split, boolean in) {
        int pos = text.indexOf(split);
        if (in) pos++;
        try {
            return text.substring(0, pos);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取第一次出现分割符后的字符串
     *
     * @param text  要分割的字符串
     * @param split 分割符
     * @param in    是否包含分隔符
     * @return String
     */
    public static String setSplitAfter(String text, String split, boolean in) {
        int pos = text.indexOf(split);
        if (!in) pos++;
        try {
            return text.substring(pos, text.length());
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 设置文字颜色
     *
     * @param text  需要设置的文字
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色
     * @param flags 是否包含的标志
     * @return Spannable
     */
    public static Spannable setTextColorSpan(String text, int start, int end, int color, int flags) {
        Spannable spannable = new SpannableString(text);
        try {
            //此处会崩溃
            spannable.setSpan(new ForegroundColorSpan(color), start, end, flags);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return spannable;
    }

    /**
     * 设置文字变化颜色
     *
     * @param text  需要设置的文字
     * @param split 开始位置的字符
     * @param color 颜色
     * @return Spannable
     */
    public static Spannable setTextColorSpan(String text, String split, int color) {
        int pos = text.indexOf(split) + 1;
        return setTextColorSpan(text, pos, text.length(), color, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static Spannable setTextColorSpan(String text, int color, int start, int end) {
        return setTextColorSpan(text, start, end, color, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置字体的相对大小
     *
     * @param text
     * @param proportion
     * @return Spannable
     */
    public static Spannable getRelativeSpannable(String text, float proportion) {
        Spannable spannable = new SpannableString(text);
        spannable.setSpan(new RelativeSizeSpan(proportion), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 设置文字的相对大小，默认为1f
     *
     * @param text   需要设置的文字
     * @param split  分隔字符
     * @param first  第一部分的大小
     * @param second 第二部分的大小
     * @return Spannable
     */
    public static Spannable getRelativeSpannable(String text, String split, float first, float second) {
        return getRelativeSpannable(text, split, first, second, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置文字的相对大小，默认为1f
     *
     * @param text   需要设置的文字
     * @param split  分隔字符
     * @param first  第一部分的大小
     * @param second 第二部分的大小
     * @param flags  是否包含的标志
     * @return Spannable
     */
    public static Spannable getRelativeSpannable(String text, String split, float first, float second, int flags) {
        Spannable spannable = new SpannableString(text);
        int pos = text.indexOf(split);
        if (pos == -1) {
            spannable.setSpan(new RelativeSizeSpan(first), 0, text.length(), flags);
        } else {
            spannable.setSpan(new RelativeSizeSpan(first), 0, pos, flags);
            spannable.setSpan(new RelativeSizeSpan(second), pos, text.length(), flags);
        }
        return spannable;
    }

    /**
     * @param text      要变化的文字
     * @param split     分隔符（第一次出现）
     * @param bigSize   大字的尺寸
     * @param smallSize 小字的尺寸
     * @return Spannable
     */
    public static Spannable getDiffSizeSpannable(String text, String split, int bigSize, int smallSize) {
        return getRelativeSpannable(text, split, bigSize, smallSize, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * @param text      要变化的文字
     * @param split     分隔符（第一次出现）
     * @param bigSize   大字的尺寸
     * @param smallSize 小字的尺寸
     * @param flags     pos位置的字是否包括标志
     * @return Spannable
     */
    public static Spannable getDiffSizeSpannable(String text, String split, int bigSize, int smallSize, int flags) {
        Spannable spannable = new SpannableString(text);
        int pos = -1;
        if (split.equals(""))
            pos = -1;
        else
            pos = text.indexOf(split);
        if (pos == -1) {
            spannable.setSpan(new AbsoluteSizeSpan(bigSize), 0, text.length(), flags);
        } else {
            spannable.setSpan(new AbsoluteSizeSpan(bigSize), 0, pos, flags);
            spannable.setSpan(new AbsoluteSizeSpan(smallSize), pos, text.length(), flags);
        }
        return spannable;
    }

    /**
     * 设置text的点击效果
     * @param listener 点击监听器
     * @param text 文字内容
     * @param start 监听的开始位置
     * @param end 监听的结束位置
     * @return Spannable
     */
    public static Spannable setClickableSpannable(View.OnClickListener listener, String text, int start, int end) {
        Spannable spanableInfo = new SpannableString(text);
        spanableInfo.setSpan(new TextClickable(listener), start, end, Spanned.SPAN_MARK_MARK);
        return spanableInfo;
    }


}