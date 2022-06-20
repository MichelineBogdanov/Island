package com.javarush.bogdanov.island.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final int ISLAND_WIDTH = 4;
    public static final int ISLAND_HEIGHT = 4;
    public static final Map<String, String> chanceToEatList = new HashMap<>(){{
        put("Волк", "0 0 0 0 0 10 15 60 80 60 70 15 10 40 0 0");
        put("Удав", "0 0 15 0 0 0 0 20 40 0 0 0 0 10 0 0");
        put("Лиса", "0 0 0 0 0 0 0 70 90 0 0 0 0 60 40 0");
        put("Медведь", "0 80 0 0 0 40 80 80 90 70 70 50 20 10 0 0");
        put("Орел", "0 0 10 0 0 0 0 90 90 0 0 0 0 80 0 0");
        put("Лошадь", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
        put("Олень", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
        put("Кролик", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1000");
        put("Мышь", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 90 100");
        put("Коза", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
        put("Овца", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
        put("Кабан", "0 0 0 0 0 0 0 0 50 0 0 0 0 0 90 100");
        put("Буйвол", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
        put("Утка", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 90 100");
        put("Гусеница", "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 100");
    }};

}
