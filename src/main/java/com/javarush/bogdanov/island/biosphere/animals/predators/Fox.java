package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.util.Util;

public class Fox extends Predators {

    public Fox() {
        super("Лиса", 8, 30, 2, 2);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
