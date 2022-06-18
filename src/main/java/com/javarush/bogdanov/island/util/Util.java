package com.javarush.bogdanov.island.util;

import java.util.Random;

public class Util {

    private static final Random random = new Random();

    public static boolean getRandomGender() {
        return random.nextBoolean();
    }

    public static double getRandomWeight(double maxWeight) {
        return random.nextDouble(maxWeight);
    }

}
