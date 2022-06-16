package com.javarush.bogdanov.island.biosphere.animals.herbivores;

public class Rabbit extends Herbivores {
    public Rabbit(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(2, 150, 2, 0.45, gender);
    }
}
