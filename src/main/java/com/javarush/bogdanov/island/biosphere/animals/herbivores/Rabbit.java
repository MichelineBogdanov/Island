package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Rabbit extends Herbivores {

    public Rabbit() {
        super("Кролик", 2, 150, 2, 0.45);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
