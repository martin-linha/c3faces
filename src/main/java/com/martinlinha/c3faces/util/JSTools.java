package com.martinlinha.c3faces.util;

import com.martinlinha.c3faces.listener.PropertyModifier;
import com.martinlinha.c3faces.model.C3ViewDataSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Martin Linha
 */
public class JSTools {

    /**
     * Returns escaped colon for .js.
     *
     * @param val To be escaped
     * @return Escaped colon for .js
     */
    public static String colonAid(String val) {
        return val.replace(":", "\\\\:");
    }

    /**
     * Returns string which has replace all ":" chars with "$"
     *
     * @param val To be modified
     * @return Modified value
     */
    public static String jsName(String val) {
        return val.replace(":", "$");
    }

    /**
     * Returns all numbers from specified collection in a comma seperated string.
     *
     * @param values To be comma separated
     * @return Integers from specified collection in a comma seperated string
     */
    public static String commaSeparatedNumbers(Collection<? extends Number> values) {
        final StringBuilder sb = new StringBuilder();
        for (Number value : values) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * Returns all string from specified collection in a comma seperated string.
     *
     * @param values To be comma separated
     * @return Strings from specified collection in a comma seperated string
     */
    public static String commaSeparatedStrings(Collection<String> values) {
        final StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * Returns all string from specified collection in a comma seperated and quoted string.
     *
     * @param values To be comma separated and quoted
     * @return Strings from specified collection in a comma seperated and quoted string
     */
    public static String commaSeparatedStringsQuoted(Collection<String> values) {
        final StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(value);
            sb.append("'");
        }
        return sb.toString();
    }

    /**
     * Returns all string from specified collection in a semicolon seperated string.
     *
     * @param values To be semicolon separated
     * @return Strings from specified collection in a semicolon seperated string
     */
    public static String semicolonSeparatedStatements(List<String> values) {
        final StringBuilder sb = new StringBuilder();
        List<String> vals = new ArrayList<>();
        for (String val : values) {
            if (val != null && !val.isEmpty()) {
                vals.add(val);
            }
        }
        for (int i = 0; i < vals.size(); i++) {
            if (vals.isEmpty()) {
                return "";
            }
            if (vals.size() == 1) {
                sb.append(vals.get(i));
                sb.append(";");
                return sb.toString();
            }
            if (i > 0) {
                sb.append("; \n");
            }
            sb.append(vals.get(i));
            if (vals.size() == i + 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public static String semicolonSeparatedStatements(String... values) {
        return semicolonSeparatedStatements(Arrays.asList(values));
    }

    /**
     * Returns all scripts from specified Modifier collection in a semicolon seperated string.
     *
     * @param modifiers Modifiers which will generates their modification script
     * @param parent Javascript name of chart
     * @return Scripts from specified Modifier collection in a semicolon seperated string
     */
    public static String semicolonSeparatedModifierScript(List<PropertyModifier> modifiers, String parent) {
        int timeOut = 0;
        StringBuilder sb = new StringBuilder();
        for (PropertyModifier propertyModifier : modifiers) {
            String script = propertyModifier.getScript(parent, timeOut);
            if (script != null && !script.isEmpty()) {
                sb.append(script);
                timeOut += propertyModifier.totalDuration();
            }
        }

        return sb.toString();
    }

    /**
     * Returns string containing data values from C3ViewDataSet in a columns block corresponding to C3.js syntax.
     *
     * @param series To be added in columns block
     * @return Generated script containing columns block
     */
    public static String columns(Set<C3ViewDataSet> series) {

        if (series.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        List<C3ViewDataSet> seriesList = new ArrayList<>(series);
        for (int i = 0; i < seriesList.size(); i++) {
            C3ViewDataSet d = seriesList.get(i);
            if (d.getDataSet() == null || d.getDataSet().getValues() == null || d.getDataSet().getValues().isEmpty()) {
                continue;
            }
            if (i > 0) {
                sb.append(", \n");
            }
            sb.append("['");
            sb.append(d.getId());
            sb.append("'");
            if (d.getDataSet().getValues().size() > 0) {
                sb.append(", ");
            }
            sb.append(JSTools.commaSeparatedNumbers(d.getDataSet().getValues()));
            sb.append("]");
        }

        return sb.toString();
    }
}
