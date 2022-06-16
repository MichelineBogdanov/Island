package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.biosphere.animals.Animals;

public abstract class Predators extends Animals {
    public Predators(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        super(weight, populationOnCell, speed, diet, gender);
    }
}
