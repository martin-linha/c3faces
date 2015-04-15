package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.constants.ChartType;
import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.listener.PropertyModifier;
import com.martinlinha.c3faces.script.property.Data;
import com.martinlinha.c3faces.script.Property;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Martin Linha
 */
public class ComponentProperties implements Serializable {

    private final Map<String, Property> properties = new HashMap<>();

    public Data getComponentData() {
        Data data = (Data) getProperty("data");
        return data;
    }

    public boolean addProperty(Property property) {
        boolean added = false;
        if (!properties.containsKey(property.getName())) {
            properties.put(property.getName(), property);
            added = true;
        }
        return added;
    }

    public void addProperties(Collection<Property> props) {
        for (Property prop : props) {
            addProperty(prop);
        }
    }

    public Property getProperty(String key) {
        return properties.get(key);
    }

    public List<Property> getProperties() {
        return new ArrayList<>(properties.values());
    }

    public List<PropertyModifier> getPropertyModifiers() {
        List<PropertyModifier> modifiers = new ArrayList<>();
        for (Property prop : getProperties()) {

            for (ChangeListener listener : prop.getListeners()) {
                if (listener instanceof PropertyModifier) {
                    if (!modifiers.contains((PropertyModifier) listener)) {
                        modifiers.add((PropertyModifier) listener);
                    }
                }
            }
        }

        return modifiers;
    }

    public void resetListeners() {
//        for (Property prop : getProperties()) {
//            prop.getListeners().clear();
//        }

        for (Property prop : getProperties()) {
            for (ChangeListener listener : prop.getListeners()) {
                if (listener instanceof PropertyModifier) {
                    PropertyModifier mod = (PropertyModifier) listener;
                    mod.reset();
                }
            }
        }
    }

    public void clearProperties() {
        properties.clear();
    }

    public void setChartType(ChartType type) {
        getComponentData().setChartType(type);
    }

    public ChartType getChartType() {
        return getComponentData().getChartType();
    }
}
