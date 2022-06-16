package com.javarush.bogdanov.island.biosphere.animals.herbivores;

public class Horse extends Herbivores {
    public Horse(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(400, 20, 4, 60, gender);
    }
}
