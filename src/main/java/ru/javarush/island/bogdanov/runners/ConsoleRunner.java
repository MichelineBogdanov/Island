package ru.javarush.island.bogdanov.runners;

import ru.javarush.island.bogdanov.creators.FieldCreator;
import ru.javarush.island.bogdanov.field.Field;
import ru.javarush.island.bogdanov.game.Game;
import ru.javarush.island.bogdanov.viewer.Viewer;
import ru.javarush.island.bogdanov.workers.GameWorker;

public class ConsoleRunner {
    public static void main(String[] args) {
        FieldCreator fieldCreator = new FieldCreator();
        Field field = fieldCreator.initField();
        Viewer viewer = new Viewer(field);
        Game game = new Game(viewer, field);
        GameWorker gameWorker = new GameWorker(game);
        new Thread(gameWorker).start();

    }
}
