package com.javarush.bogdanov.island.biosphere.animals;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import com.javarush.bogdanov.island.biosphere.animals.actions.AbleToEat;
import com.javarush.bogdanov.island.biosphere.animals.actions.Fertile;
import com.javarush.bogdanov.island.biosphere.animals.actions.Movable;

public abstract class Animals extends Biosphere implements AbleToEat, Fertile, Movable {

    public double weight;
    public int populationOnCell;
    public int speed;
    public double diet;
    // true - мужской, false - женский
    public boolean gender;

    public Animals(double weight, int populationOnCell, int speed, double diet, boolean gender) {
        this.weight = weight;
        this.populationOnCell = populationOnCell;
        this.speed = speed;
        this.diet = diet;
        this.gender = gender;
    }

    @Override
    public void eat(Biosphere food) {

    }

    @Override
    public void multiple() {

    }

    @Override
    public void move() {

    }
}
