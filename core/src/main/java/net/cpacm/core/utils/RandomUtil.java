package net.cpacm.core.utils;

import java.util.Random;

/**
 * Created by cpacm on 2015/7/17.
 */
public class RandomUtil {

    public static int getIntRandom(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static float getFloatRandom() {
        Random random = new Random();
        return random.nextFloat();
    }

    public static boolean getBooleanRandom() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
