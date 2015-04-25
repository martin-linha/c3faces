package com.martinlinha.c3faces.component;

import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.listener.PropertyModifier;
import com.martinlinha.c3faces.script.Modifier;
import com.martinlinha.c3faces.script.Property;
import com.martinlinha.c3faces.script.modifier.Colors;
import com.martinlinha.c3faces.script.modifier.Load;
import com.martinlinha.c3faces.script.modifier.Names;
import com.martinlinha.c3faces.script.modifier.Transform;
import com.martinlinha.c3faces.script.modifier.TransformTypes;
import com.martinlinha.c3faces.script.property.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which encapsulates chart's visual properties of type com.martinlinha.c3faces.script.Property. C3Chart objects keeps instances of this class
 * in StateHelper to keep properties for enough time.
 *
 * @author Martin Linha
 */
public class ComponentProperties implements Serializable {

    private final Map<String, Property> properties = new HashMap<>();
    private boolean lastDataChanged;

    /**
     * Returns chart's com.martinlinha.c3faces.script.property.Data object.
     *
     * @return Chart's Data object.
     */
    public Data getComponentData() {
        Data data = (Data) getProperty(Data.NAME);
        return data;
    }

    /**
     * Method used to add new property.
     *
     * WARNING! Will not be added if property of same name (property.getName()) is already contained in collection.
     *
     * @param property Object to be added
     * @return True if property is added.
     */
    public boolean addProperty(Property property) {
        boolean added = false;
        if (!properties.containsKey(property.getName())) {
            properties.put(property.getName(), property);
            added = true;
        }
        return added;
    }

    boolean addProperty(Data data) {
        Data oldData = (Data) properties.get(Data.NAME);
        properties.put(Data.NAME, data);

        boolean addedNew = oldData == null ? true : data != oldData;

        if (addedNew) {
            Modifier dataModifier = new Load();
            dataModifier.addModifier(new Names());
            dataModifier.addModifier(new Colors());
            dataModifier.addModifier(new Transform());
            dataModifier.addModifier(new TransformTypes());
            data.addListener(dataModifier);
        }
        System.out.println("DATA CHANGED: " + addedNew);
        return addedNew;
    }

    /**
     * Adds all properties from collection.
     *
     * WARNING! Properties with name (property#getName()) of another property already contained in collection of this class will not be added.
     *
     * @param props Collection of properties to be added
     */
    public void addProperties(Collection<Property> props) {
        if (props == null) {
            return;
        }
        for (Property prop : props) {
            addProperty(prop);
        }
    }

    /**
     * Returns property from map by property name.
     *
     * @param key Property name to be returned
     * @return Property of specified name
     */
    public Property getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Returns read-only properties collection. To add new property, use ComponentProperties#addProperty().
     *
     * WARNING! Changes to this list won't affect chart's properties.
     *
     * @return Read-only list of all properties
     */
    public List<Property> getProperties() {
        return new ArrayList<>(properties.values());
    }

    /**
     * Returns list of all objects com.martinlinha.c3faces.listener.PropertyModifier associated to all of the properties.
     *
     * @return List of all registered PropertyModifiers to all properties
     */
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

    /**
     * Will call PropertyModifier#reset() on all registered objects of type com.martinlinha.c3faces.listener.PropertyModifier.
     *
     */
    public void resetListeners() {

        for (Property prop : getProperties()) {
            for (ChangeListener listener : prop.getListeners()) {
                if (listener instanceof PropertyModifier) {
                    PropertyModifier mod = (PropertyModifier) listener;
                    mod.reset();
                }
            }
        }
    }

    /**
     * Sets chart type specified in Data.
     *
     * @param type Chart type (conforming to C3.js)
     */
    public void setChartType(String type) {
        getComponentData().setChartType(type);
    }

    /**
     * Returns chart type specified in Data.
     *
     * @return type Chart type (conforming to C3.js)
     */
    public String getChartType() {
        return getComponentData().getChartType();
    }

    /**
     * Method with only friendly access used to clear map of properties.
     *
     */
    void clearProperties() {
        properties.clear();
    }
}
