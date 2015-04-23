package com.martinlinha.c3faces.util;

/**
 * Class contains useful static methods for UI components.
 *
 * @author Martin Linha
 */
public class ComponentUtil {

    /**
     * Tries to parse specified object to boolean. If object is null, returns null.
     *
     * @param o Object to be parsed
     * @return Parsed object or null
     */
    public static Boolean parseBoolean(Object o) {
        if (o == null) {
            return null;
        }
        return Boolean.parseBoolean((String) o);
    }

    /**
     * Tries to parse specified object to integer. If object is null, returns null.
     *
     * @param o Object to be parsed
     * @return Parsed object or null
     */
    public static Integer parseInteger(Object o) {
        if (o == null) {
            return null;
        }
        return Integer.parseInt((String) o);
    }

    /**
     * Tries to parse specified object to double. If object is null, returns null.
     *
     * @param o Object to be parsed
     * @return Parsed object or null
     */
    public static Double parseDouble(Object o) {
        if (o == null) {
            return null;
        }
        return Double.parseDouble((String) o);
    }

    /**
     * Tries to find Enum of specified name in specified class. If name's null, returns null.
     *
     * @param <E>
     * @param clazz
     * @param name
     * @return Parsed object or null
     */
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
