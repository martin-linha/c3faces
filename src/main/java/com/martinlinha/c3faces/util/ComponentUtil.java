package com.martinlinha.c3faces.util;

/**
 *
 * @author Martin Linha
 */
public class ComponentUtil {

    public static Boolean parseBoolean(Object o) {
        if (o == null) {
            return null;
        }
        return Boolean.parseBoolean((String) o);
    }

    public static Integer parseInteger(Object o) {
        if (o == null) {
            return null;
        }
        return Integer.parseInt((String) o);
    }

    public static Double parseDouble(Object o) {
        if (o == null) {
            return null;
        }
        return Double.parseDouble((String) o);
    }

    public static <E extends Enum<E>> E findEnum(Class<E> clazz, String name) {
        if (name == null) {
            return null;
        }
        try {
            return Enum.valueOf(clazz, name.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
