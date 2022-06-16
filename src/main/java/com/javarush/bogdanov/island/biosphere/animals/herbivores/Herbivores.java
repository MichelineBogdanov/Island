package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.biosphere.animals.Animals;

public abstract class Herbivores extends Animals {

    public Herbivores(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(weight, populationOnCell, speed, diet, gender);
    }
}
