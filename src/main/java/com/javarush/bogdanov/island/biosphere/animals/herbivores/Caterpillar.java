package com.javarush.bogdanov.island.biosphere.animals.herbivores;

public class Caterpillar extends Herbivores {
    public Caterpillar(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(0.01, 1000, 0, 0, gender);
    }
}
