package ru.javarush.bogdanov.island.viewer;

import ru.javarush.bogdanov.island.field.Field;

public class Viewer {

    public void showStatistics(Field field) {
        for (int row = 0; row < field.getField().length; row++) {
            System.out.println("------------------------------------------------------------------------------");
            for (int col = 0; col < field.getField()[0].length; col++) {
                System.out.printf("|%s\t\t\t", field.getField()[row][col].getSpeciesWithMaxCount()[0]);
                if (col == field.getField()[row][col].getSpeciesWithMaxCount().length - 1) {
                    System.out.println();
                }
            }
            for (int col = 0; col < field.getField()[1].length; col++) {
                System.out.printf("|%s\t\t\t", field.getField()[row][col].getSpeciesWithMaxCount()[1]);
                if (col == field.getField()[row][col].getSpeciesWithMaxCount().length - 1) {
                    System.out.println();
                }
            }
            for (int col = 0; col < field.getField()[2].length; col++) {
                System.out.printf("|%s\t\t\t", field.getField()[row][col].getSpeciesWithMaxCount()[2]);
                if (col == field.getField()[row][col].getSpeciesWithMaxCount().length - 1) {
                    System.out.println();
                }
            }
        }
    }

}


