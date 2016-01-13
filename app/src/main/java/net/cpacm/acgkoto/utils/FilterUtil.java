package net.cpacm.acgkoto.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串过滤
 */
public class FilterUtil {

    /**
     * 判断手机格式是否正确,13、14、15（4除外）、17、18全号段
     * 判断手机是否为11位
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {
        if (mobiles == null || mobiles.equals(""))
            return false;
        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 手机号码隐藏中间四个数字
     *
     * @param phone
     * @return
     */
    public static String phoneFormate(String phone) {
        if (phone == null)
            return "***********";
        String temp = phone;
        String str = temp.substring(0, 3)+"****"+temp.substring(7,temp.length());
        return str;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean isBankCard(String cardId) {
        if (cardId == null) return false;
        if (cardId.length() == 16 || cardId.length() == 19) {
            char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
            return cardId.charAt(cardId.length() - 1) == bit;
        } else {
            return false;
        }
    }

    public static String NameFormat(String name) {
        if (name == null || name.equals(""))
            return null;
        String temp;
        if (name.length() == 1) return name;
        if (name.length() == 2) {
            temp = name.substring(0, 1);
            return temp + " *";
        }
        if (name.length() > 2) {
            int len = name.length() - 2;
            String formatName = name.substring(0, 1);
            for(int i=0;i<len;i++){
                formatName+=" *";
            }
            formatName+=" ";
            formatName+= name.substring(name.length()-1, name.length());
            return formatName;
        }
        return null;
    }

    /**
     * 从不含校验位的银行卡卡号采用Luhn校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("Bank card code must be number!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhnSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhnSum += k;
        }
        return (luhnSum % 10 == 0) ? '0' : (char) ((10 - luhnSum % 10) + '0');
    }
}
