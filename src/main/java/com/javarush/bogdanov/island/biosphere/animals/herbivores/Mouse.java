package com.javarush.bogdanov.island.biosphere.animals.herbivores;

public class Mouse extends Herbivores {
    public Mouse(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(0.05, 500, 1, 0.01, gender);
    }
}
