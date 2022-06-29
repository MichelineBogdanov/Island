package ru.javarush.bogdanov.island.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    private static final Random random = new Random();

    public static boolean getRandomGender() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static double getRandomWeight(double maxWeight) {
        return ThreadLocalRandom.current().nextDouble(maxWeight);
    }

    public static int getRandomNumber(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

}
