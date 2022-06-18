package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.util.Util;

public class Bear extends Predators {

    public Bear() {
        super("Медведь", 500, 5, 2, 80);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
