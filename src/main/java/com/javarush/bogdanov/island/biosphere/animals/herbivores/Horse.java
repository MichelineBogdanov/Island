package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Horse extends Herbivores {

    public Horse() {
        super("Лошадь", 400, 20, 4, 60);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
