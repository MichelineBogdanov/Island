package ru.javarush.bogdanov.island.util;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.javarush.bogdanov.island.constants.Constants;
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

    public static Class<? extends Biosphere> getClassByName(String name) {
        Class<? extends Biosphere> result = null;
        List<Biosphere> animalPrototypeList = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);
        for (Biosphere biosphere : animalPrototypeList) {
            if (biosphere.getClass().getSimpleName().equals(name)) {
                result = biosphere.getClass();
            }
        }
        return result;
    }

    public static String getNameByClass(Class<? extends Biosphere> clazz) {
        String result = "";
        List<Biosphere> animalPrototypeList = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);
        for (Biosphere biosphere : animalPrototypeList) {
            if (biosphere.getClass().equals(clazz)) {
                result = biosphere.getName();
            }
        }
        return result;
    }

}
