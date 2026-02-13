package ibs124.gundi.util;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AppUtils {

    public static final Map<String, String> mapConstants(Class<?> clazz) {
        return Stream
                .of(clazz.getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> Modifier.isFinal(f.getModifiers()))
                .filter(f -> f.getType().equals(String.class))
                .collect(Collectors.toUnmodifiableMap(
                        field -> field.getName().toLowerCase(),
                        field -> {
                            try {
                                return (String) field.get(null);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }));
    }
}
