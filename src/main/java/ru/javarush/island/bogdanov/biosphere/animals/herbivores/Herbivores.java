package ru.javarush.island.bogdanov.biosphere.animals.herbivores;

import ru.javarush.island.bogdanov.biosphere.animals.Animals;

public abstract class Herbivores extends Animals {

    public Herbivores(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet, icon);
    }
}
