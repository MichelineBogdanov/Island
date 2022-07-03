package ru.javarush.island.bogdanov.biosphere.animals.predators;

import ru.javarush.island.bogdanov.biosphere.animals.Animals;

public abstract class Predators extends Animals {

    public Predators(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet, icon);
    }
}
