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

    public static String colonAid(String val) {
        return val.replace(":", "\\\\:");
    }

    public static String jsName(String val) {
        return val.replace(":", "$");
    }

    public static String commaSeparatedInts(Collection<Integer> values) {
        final StringBuilder sb = new StringBuilder();
        for (Integer value : values) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(value);
        }
        return sb.toString();
    }

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

    public static String columns(Set<C3ViewDataSet> series) {

        final StringBuilder sb = new StringBuilder();
        List<C3ViewDataSet> seriesList = new ArrayList<>(series);
        for (int i = 0; i < seriesList.size(); i++) {
            C3ViewDataSet d = seriesList.get(i);
            if (d.getDataSet().getValues().isEmpty()) {
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
            sb.append(JSTools.commaSeparatedInts(d.getDataSet().getValues()));
            sb.append("]");
        }
        
        return sb.toString();
    }
}
