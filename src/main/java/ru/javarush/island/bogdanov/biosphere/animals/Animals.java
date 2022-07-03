package ru.javarush.island.bogdanov.biosphere.animals;

import ru.javarush.island.bogdanov.biosphere.Biosphere;
import ru.javarush.island.bogdanov.biosphere.actions.AbleToEat;
import ru.javarush.island.bogdanov.biosphere.actions.Movable;
import ru.javarush.island.bogdanov.constants.Constants;
import ru.javarush.island.bogdanov.field.Cell;
import ru.javarush.island.bogdanov.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Animals extends Biosphere implements AbleToEat, Movable {

    public Animals(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet, icon);
    }

    @Override
    public void eat(Cell currentCell) {
        double eatenFood = 0;
        if (this.isAlive()) {
            /*while (eatenFood < this.getMaxDiet()) {
                double food = this.safeFindFood(currentCell);
                if (food != 0) {
                    eatenFood += food;
                } else {
                    break;
                }
            }*/
            this.safeSetWeight(currentCell, this.getWeight() * 0.9);
            if (this.getWeight() < this.getMaxWeight() / 5) {
                safeDie(currentCell);
            }
        } else {
            safeDie(currentCell);
        }
    }

    @Override
    public boolean move(Cell currentCell) {
        int countStep = this
                .getMaxSpeed();
        Cell destinationCell = currentCell.getNextCell(countStep);
        return safeMove(currentCell, destinationCell);
    }

    //При выполнении задач (скорее всего в методе eat(), а конкретно в safeFindFood()) возникают
    //"сломанные" животные, которые не едят и не худеют, и у которых не устанавливается флаг мертв.
    //(непонятно выполняются ли у них остальные методы)
    //Они просто остаются в списке.
    //
    //Возможно это из за того, что при проходе цикла метода eat() в наш поток в какой то промежуточный этап между циклами
    //или переходами к другим методам ячейку захватывает другой поток и вносит в наш изменения
    //надо попробовать перенести полное возможное насыщение животного в метод safeFindFood()
    private double safeFindFood(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            //получаем список позиций жертв (из константы матрицы вероятностей поедания), отсортированный по убыванию вероятности поедания
            List<Integer> chanceRateAnimalTarget = this.getChanceRateAnimalTarget();
            //получаем позицию (из константы списка животных) нашего животного, который собирается поесть
            int position = Constants.ANIMAL_NAMES.indexOf(this.getClass().getSimpleName());
            //проходим по этому списке позици
            for (Integer integer : chanceRateAnimalTarget) {
                //получаем имя жертвы
                String targetName = Constants.ANIMAL_NAMES.get(integer);
                //получаем сет жертв в ячейке
                Set<Biosphere> targetSet = currentCell.getCellAnimalCollection().get(targetName);
                if (targetSet.size() == 0) {
                    continue;
                }
                //проходимся по нему
                for (Biosphere target : targetSet) {
                    //проверяем сможем ли скушать жертву
                    if (Util.getRandomNumber(100) < Constants.CHANCE_TO_EAT[position][integer] && target.isAlive() && target.getWeight() != 0) {
                        //тут вичисляем вес съеденного и устанавливаем нашему животному то, что он скушал (в зависимости от максимального дневного
                        //рациона, максимального веса и так далее
                        double maxWeight = this.getMaxWeight();
                        double maxDiet = this.getMaxDiet();
                        double thisWeight = this.getWeight();
                        double targetWeight = target.getWeight();
                        if (targetWeight < maxDiet) {
                            this.safeSetWeight(currentCell, Math.min(thisWeight + targetWeight, maxWeight));
                        } else {
                            this.safeSetWeight(currentCell, Math.min(thisWeight + maxDiet, maxWeight));
                        }
                        //тут устанавливаем жертве статус мертвеца, чтобы потом в задаче у этого животного его просто убить, а не выполнять его операции
                        if (target instanceof Animals) {
                            target.safeSetAlive(currentCell, false);
                        } else {
                            target.safeSetWeight(currentCell, 0);
                        }
                        //System.out.println(this.getName() + this.getId() + " съел " + targetName + target.getId());
                        return this.getWeight() - thisWeight;
                    }
                }
            }
            return 0;
        } finally {
            currentCell.getLock().unlock();
        }
    }

    private void safeDie(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            //нужны ли локи, если мы итак залочили ячейку во всех методах, в которых используем этот метод?
            //есл ииспользовать syncronized в сигнатуре метода, какие будут преимущества и возможные проблемы?
            currentCell.getCellAnimalCollection().get(this.getClass().getSimpleName()).remove(this);
        } finally {
            currentCell.getLock().unlock();
        }
    }

    public List<Integer> getChanceRateAnimalTarget() {
        Map<Integer, Integer> targetRageMap = new HashMap<>();
        int position = Constants.ANIMAL_NAMES.indexOf(this.getClass().getSimpleName());
        int[] foodMap = Constants.CHANCE_TO_EAT[position];
        for (int i = 0; i < foodMap.length; i++) {
            if (foodMap[i] > 0) {
                targetRageMap.put(i, foodMap[i]);
            }
        }
        return targetRageMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
