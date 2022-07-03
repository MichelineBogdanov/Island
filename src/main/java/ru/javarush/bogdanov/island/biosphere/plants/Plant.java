package ru.javarush.bogdanov.island.biosphere.plants;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.actions.Growable;
import ru.javarush.bogdanov.island.field.Cell;

public class Plant extends Biosphere implements Growable {

    public Plant() {
        super("Растение", 1, 200, 0, 0, "\uD83C\uDF33");
    }

    @Override
    public void grow(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            if (this.getWeight() < this.getMaxWeight()) {

                this.safeSetWeight(this.getWeight() + (this.getMaxWeight() - this.getWeight()) * 0.5);
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }
}
