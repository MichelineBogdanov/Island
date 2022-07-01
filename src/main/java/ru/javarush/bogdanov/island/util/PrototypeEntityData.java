package ru.javarush.bogdanov.island.util;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.exceptions.GameException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class PrototypeEntityData {

    public static List<Biosphere> getAnimalPrototypeList(String fullPackageName) {
        Set<Class<?>> allClassesFromProject = getAllClassesFromProject(fullPackageName);
        List<Biosphere> result = new ArrayList<>();
        for (Class<?> aClass : allClassesFromProject) {
            try {
                Constructor<?> constructor = aClass.getConstructor();
                Biosphere entity = (Biosphere) constructor.newInstance();
                result.add(entity);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Set<Class<?>> getAllClassesFromProject(String fullPackageName) {
        Set<Class<?>> subTypes = new HashSet<>();
        Collection<String> values = new Reflections(fullPackageName, new SubTypesScanner())
                .getStore()
                .get(SubTypesScanner.class.getSimpleName())
                .values();
        for (String className : values) {
            try {
                Class<?> subType = Class.forName(className);
                if (!Modifier.isAbstract(subType.getModifiers())) {
                    subTypes.add(subType);
                }
            } catch (ClassNotFoundException e) {
                throw new GameException("Класс не найден", e);
            }
        }
        return subTypes;
    }

    public static Class<? extends Biosphere> getClassByName(String name, List<Biosphere> listOfPrototypes) {
        Class<? extends Biosphere> result = null;
        for (Biosphere biosphere : listOfPrototypes) {
            if (biosphere.getClass().getSimpleName().equals(name)) {
                result = biosphere.getClass();
            }
        }
        return result;
    }

    public static String getIconByClass(Class<? extends Biosphere> clazz, List<Biosphere> listOfPrototypes) {
        String result = "";
        for (Biosphere biosphere : listOfPrototypes) {
            if (biosphere.getClass().equals(clazz)) {
                result = biosphere.getIcon();
            }
        }
        return result;
    }

}
