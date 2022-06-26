package ru.javarush.bogdanov.island.runners;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Field;
import ru.javarush.bogdanov.island.game.Game;
import ru.javarush.bogdanov.island.util.PrototypeEntityData;

public class ConsoleRunner {
    public static void main(String[] args) {
        Game game = new Game();
        Field field = game.fieldCreator.initField();
        game.viewer.showStatistics(field);
        System.out.println(field.getField()[0][0]);
        System.out.println(PrototypeEntityData.getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME).size());
        System.out.println(PrototypeEntityData.getAllClassesFromProject(Constants.BIOSPHERE_PACKAGE_NAME).size());
        for (Biosphere biosphere : PrototypeEntityData.getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME)) {
            System.out.println(biosphere);
        }
    }
}
