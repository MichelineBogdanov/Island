package ru.javarush.bogdanov.island.util;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

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

    private static Set<Class<?>> getAllClassesFromProject(String fullPackageName) {
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
                throw new RuntimeException("Этого не может быть:)", e);
            }
        }
        return subTypes;
    }

}
