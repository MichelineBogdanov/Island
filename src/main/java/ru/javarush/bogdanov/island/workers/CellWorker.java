package ru.javarush.bogdanov.island.workers;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.field.Cell;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CellWorker implements Runnable {

    private final Queue<Task> tasks = new ConcurrentLinkedQueue<>();
    private final Cell cell;

    public CellWorker(Cell cell) {
        this.cell = cell;
    }


    @Override
    public void run() {
        Set<Biosphere> allAnimalsOnCellCollection = cell.getAllAnimalsOnCellCollection();
        cell.getLock().lock();
        try {
            allAnimalsOnCellCollection.forEach(o -> tasks.add(new Task(o, cell)));
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::doTask);
        tasks.clear();
    }
}
