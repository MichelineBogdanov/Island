package ru.javarush.bogdanov.island.field;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Getter
@Setter
public class Cell {

    private List<Cell> neighbourCell = new ArrayList<>();
    @Getter
    private Map<String, Set<Biosphere>> cellAnimalCollection;
    @Getter
    private final Lock lock = new ReentrantLock(true);

    public Cell() {
    }

    public Set<Biosphere> getCellSpeciesCollection(String type) {
        return this.cellAnimalCollection.get(type);
    }

    public Set<Biosphere> getCellSpeciesGenderCollection(String type, boolean gender) {
        return this.getCellSpeciesCollection(type)
                .stream()
                .filter(g -> g.isGender() == gender)
                .collect(Collectors.toSet());
    }

    public void findNeighbourCells(Field field, int row, int col) {
        if (row > 0) neighbourCell.add(field.getCellFromField(row - 1, col));
        if (col > 0) neighbourCell.add(field.getCellFromField(row, col - 1));
        if (row < field.getField().length - 1) neighbourCell.add(field.getCellFromField(row + 1, col));
        if (col < field.getField()[0].length - 1) neighbourCell.add(field.getCellFromField(row, col + 1));
    }

}
