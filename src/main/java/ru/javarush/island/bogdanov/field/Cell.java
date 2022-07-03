package ru.javarush.island.bogdanov.field;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.bogdanov.biosphere.Biosphere;
import ru.javarush.island.bogdanov.biosphere.animals.herbivores.Herbivores;
import ru.javarush.island.bogdanov.biosphere.animals.predators.Predators;
import ru.javarush.island.bogdanov.biosphere.plants.Plant;
import ru.javarush.island.bogdanov.constants.Constants;
import ru.javarush.island.bogdanov.util.Util;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static ru.javarush.island.bogdanov.util.PrototypeEntityData.*;

@Getter
@Setter
public class Cell {

    private List<Cell> neighbourCell = new ArrayList<>();
    @Getter
    private Map<String, Set<Biosphere>> cellAnimalCollection = new HashMap<>();
    @Getter
    private final Lock lock = new ReentrantLock(true);
    @Getter
    private final List<Biosphere> listOfPrototypes = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);

    public Cell() {
    }

    public Set<Biosphere> getCellSpeciesCollection(String type) {
        return this.cellAnimalCollection.get(type);
    }

    public Set<Biosphere> getAllAnimalsOnCellCollection() {
        return this.cellAnimalCollection
                .entrySet()
                .stream()
                .flatMap(o -> o.getValue().stream())
                .collect(Collectors.toSet());
    }

    public String[] getSpeciesWithMaxCount() {
        String[] result = new String[3];
        Map<String, Integer> collect = this.cellAnimalCollection.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        (entry) -> entry.getValue().size()));
        result[0] = getMaxCount(collect, Plant.class);
        result[1] = getMaxCount(collect, Predators.class);
        result[2] = getMaxCount(collect, Herbivores.class);
        return result;
    }

    private String getMaxCount(Map<String, Integer> collect, Class<? extends Biosphere> clazz) {
        String result = "\uD83D\uDC80";
        int max = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : collect.entrySet()) {
            Class<? extends Biosphere> checkedClass = getClassByName(stringIntegerEntry.getKey(), listOfPrototypes);
            if (clazz.isAssignableFrom(checkedClass)) {
                if (stringIntegerEntry.getValue() > max) {
                    max = stringIntegerEntry.getValue();
                    result = getIconByClass(checkedClass, listOfPrototypes) + " - " + stringIntegerEntry.getValue();
                }
            } else if (checkedClass.getSimpleName().equals(clazz.getSimpleName())) {
                result = getIconByClass(checkedClass, listOfPrototypes) + " - " + max;
            }
        }
        return result;
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

    public Cell getNextCell(int countStep) {
        Set<Cell> visitedCells = new HashSet<>();
        Cell currentCell = this;
        while (visitedCells.size() < countStep) {
            List<Cell> cells = currentCell
                    .neighbourCell
                    .stream()
                    .filter(cell -> !visitedCells.contains(cell))
                    .toList();
            int countDirections = cells.size();
            if (countDirections > 0) {
                int index = Util.random(0, countDirections);
                currentCell = cells.get(cells.size() - 1 - index);
                visitedCells.add(currentCell);
            } else {
                break;
            }
        }
        return currentCell;
    }

    @Override
    public String toString() {
        return "cellAnimalCollection=" + cellAnimalCollection +
                '}';
    }
}
