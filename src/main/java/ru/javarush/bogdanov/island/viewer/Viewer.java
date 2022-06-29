package ru.javarush.bogdanov.island.viewer;

import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Field;

import java.util.List;

public class Viewer {

    public void showField(Field field) {
        List<String[]> listOfMaxSpeciesOnCellCount = field.getListOfMaxSpeciesOnCellCount();
        int count = 0;
        System.out.println("-".repeat(83));
        for (int line = 0; line < Constants.ISLAND_ROWS; line++) {
            for (int i = 0; i < 3; i++) {
                for (int col = 0; col < Constants.ISLAND_COLUMNS; col++) {
                    System.out.printf("|%-20s", listOfMaxSpeciesOnCellCount.get(line + col + count)[i]);
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println("-".repeat(83));
            count++;
        }
    }

    public void showStatistic(Field field) {

    }




}


