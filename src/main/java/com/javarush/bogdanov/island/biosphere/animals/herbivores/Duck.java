package com.javarush.bogdanov.island.biosphere.animals.herbivores;

public class Duck extends Herbivores {
    public Duck(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(1, 200, 4, 0.15, gender);
    }
}
