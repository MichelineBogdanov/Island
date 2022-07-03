package ru.javarush.island.bogdanov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static boolean getRandomGender() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static double getRandomWeight(double maxWeight) {
        return ThreadLocalRandom.current().nextDouble(maxWeight);
    }

    public static int getRandomNumber(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
