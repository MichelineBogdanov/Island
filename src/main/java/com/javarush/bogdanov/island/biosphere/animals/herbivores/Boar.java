package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Boar extends Herbivores {

    public Boar() {
        super("Кабан", 400, 50, 2, 50);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
