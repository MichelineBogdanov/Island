package ru.javarush.island.bogdanov.biosphere.plants;

import ru.javarush.island.bogdanov.field.Cell;
import ru.javarush.island.bogdanov.biosphere.Biosphere;
import ru.javarush.island.bogdanov.biosphere.actions.Growable;

public class Plant extends Biosphere implements Growable {

    public Plant() {
        super("Растение", 1, 200, 0, 0, "\uD83C\uDF33");
    }

    @Override
    public void grow(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            if (this.getWeight() < this.getMaxWeight()) {

                this.safeSetWeight(currentCell,this.getWeight() + (this.getMaxWeight() - this.getWeight()) * 0.5);
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }
}
