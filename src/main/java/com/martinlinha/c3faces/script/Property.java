package com.martinlinha.c3faces.script;

import com.martinlinha.c3faces.listener.ChangeListener;
import com.martinlinha.c3faces.listener.change.Change;
import com.martinlinha.c3faces.listener.change.CumulatibleChange;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to define visual properties of generated chart. Its main product is generated .js script which represents C3.js script.
 *
 * Each instance is composite, ie. it contains other objects of same type.
 *
 * Generated script is composed of names and body, e.g. NAME: { BODY }. If BODY is null or empty, no script will be generated.
 *
 * @author Martin Linha
 */
public abstract class Property {

    private String name;
    private String body;
    private List<ChangeListener> listeners = new ArrayList<>();
    private final List<Property> children = new ArrayList<>();

    public Property() {
    }

    /**
     * Constructs new empty instance with specified collection of listeners.
     *
     * @param listeners Listeners to be setted in initialization
     */
    public Property(List<ChangeListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * Returns generated script including property name
     *
     * @return Generated script including property name
     */
    public final String getScript() {
        return getScript(true);
    }

    /**
     * Generates script for C3.js representing chart's attributes. Same method are called on all composite objects to ensure complete script to be
     * generated.
     *
     * If instance of this class has no body (ie. null or empty), returns empty String.
     *
     * @param includeName True if property name in generated script should be included
     * @return Generated C3.js script
     */
    public final String getScript(boolean includeName) {
        preScriptBuild();

        final StringBuilder sb = new StringBuilder();
        String propName = includeName ? getName() : null;

        if (getChildren() == null || getChildren().isEmpty()) {
            if (getBody() == null || getBody().isEmpty()) {
                return "";
            }
            if (propName != null) {
                return propName + getSeparator() + getPrefix() + getBody() + getSuffix();
            } else {
                return getPrefix() + getBody() + getSuffix();
            }
        }

        StringBuilder sbChildren = new StringBuilder();
        for (Property attr : getChildren()) {
            if (sbChildren.length() > 0) {
                sbChildren.append(", \n");
            }
            sbChildren.append(attr.getScript());
        }

        if (sbChildren.length() == 0) {
            return "";
        }
        if (propName != null) {
            sb.append(propName);
            sb.append(getSeparator());
            sb.append("\n");
        }
        sb.append(getPrefix());
        sb.append(sbChildren.toString());
        sb.append(getSuffix());
        return sb.toString();
    }

    /**
     * Returns prefix representing string appended before body, e.g. name: PREFIX body;
     *
     * @return Prefix appended before script's body
     */
    public abstract String getPrefix();

    /**
     * Returns suffix representing string appended after body, e.g. name: body SUFFIX;
     *
     * @return Suffix appended after script's body
     */
    public abstract String getSuffix();

    /**
     * Returns all composite children of this object.
     *
     * @return All composite children of this object
     */
    public List<Property> getChildren() {
        return children;
    }

    /**
     * Returns name of generated script - e.g. NAME: body;
     *
     * @return Name of generated script
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of generated script - e.g. NAME: body;
     *
     * @param name Name of generated script
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns body of generated script - e.g. name: BODY;
     *
     * @return Body of generated script
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body of generated script - e.g. name: BODY;
     *
     * @param body Body of generated script
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Adds new composite child to instance of this class. If property of same name already exists in collection of this instance, removes it first.
 If removePropsWithEmptyName is true, it also removes all properties with empty name strings.
     *
     * @param property To be added
     * @param removePropsWithEmptyName If true, removes all child properties with empty names
     */
    public void addChild(Property property, boolean removePropsWithEmptyName) {
        if (property == null) {
            return;
        }

        removeDuplicate(property, removePropsWithEmptyName);

        if (containsSomeBody(property)) {
            children.add(property);
        }
    }

    /**
     * Adds new composite child to instance of this class. If property of same name already exists in collection of this instance, removes it first.
     *
     * @param property To be added
     */
    public void addChild(Property property) {
        addChild(property, false);
    }

    /**
     * Adds new composite children to instance of this class from specified collection. If property of same name already exists in collection of this
     * instance, removes it first.
     *
     * @param properties Properties to be added
     */
    public void addChildren(Collection<Property> properties) {
        for (Property property : properties) {
            addChild(property);
        }
    }

    /**
     * Fires new event which notifies all registered listeners. Including object of this change.
     *
     * @param name Name of change
     * @param value Value of change
     */
    public void fire(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new Change(name, value));
        }
    }

    /**
     * Notifies all registered listeners with occurred change with CumulatibleChange object.
     *
     * @param name Name of change
     * @param value Value of change
     */
    public void fireCumulatible(String name, Object value) {
        for (ChangeListener listener : listeners) {
            listener.onChange(new CumulatibleChange(name, value));
        }
    }

    /**
     * Method which is invoked before every script generation. Can be used as a hook in this method phase - typically for addition of some new
     * object's children.
     *
     */
    protected void preScriptBuild() {
    }

    /**
     * Returns seperator between NAME and BODY. Default points to ":".
     *
     * @return Separator between NAME and BODY
     */
    public String getSeparator() {
        return ": ";
    }

    /**
     * Returns all registered listeners
     *
     * @return All registered liteners
     */
    public List<ChangeListener> getListeners() {
        return listeners;
    }

    /**
     * Adds new listener object to listener collection
     *
     * @param listener Listener to be added
     */
    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Adds all listeners of specified collection to registered listeners
     *
     * @param listener Listeners to be added to registered listeners
     */
    public void addListeners(Collection<ChangeListener> listener) {
        listeners.addAll(listener);
    }

    private boolean containsSomeBody(Property property) {
        if (property.getBody() != null && !property.getBody().isEmpty()) {
            return true;
        } else {
            for (Property child : property.getChildren()) {
                if (containsSomeBody(child)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeDuplicate(Property property, boolean removePropsEmptyName) {
        Iterator<Property> i = children.iterator();
        while (i.hasNext()) {
            Property prop = i.next();

            if (removePropsEmptyName && prop.getName() == null && property.getName() == null) {
                i.remove();
            }
            if (prop.getName() != null && prop.getName().equals(property.getName())) {
                i.remove();
            }
        }
    }
}
