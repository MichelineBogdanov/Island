package ru.javarush.bogdanov.island.game;

import ru.javarush.bogdanov.island.creators.FieldCreator;
import ru.javarush.bogdanov.island.viewer.Viewer;
import ru.javarush.bogdanov.island.workers.AnimalActionWorker;
import ru.javarush.bogdanov.island.workers.MapWorker;

public class Game {

    public FieldCreator field = new FieldCreator();
    public Viewer viewer = new Viewer();
    public AnimalActionWorker animalActionWorker = new AnimalActionWorker();
    public MapWorker mapWorker = new MapWorker();

}
