package com.martinlinha.c3faces.script.property;

import com.martinlinha.c3faces.script.ArrayBlock;
import com.martinlinha.c3faces.script.ObjectBlock;
import com.martinlinha.c3faces.script.ValueBlock;
import com.martinlinha.c3faces.util.JSTools;
import java.util.Set;

/**
 * Through this class is possible to define chart's visual properties, specifically Axis visual properties.
 *
 *
 * @see http://c3js.org/reference.html for attrs info
 * @author Martin Linha
 */
public class Axis extends ObjectBlock {

    public static String NAME = "axis";

    private Boolean rotated;
    private Boolean showX;
    private Axis.Type typeX;
    private Set<String> categoriesX;
    private Boolean localtimeX;
    private Integer heightX;
    private Boolean showY;
    private Axis.Type typeY;
    private Set<String> categoriesY;
    private Boolean localtimeY;
    private Integer heightY;

    public enum Type {

        TIMESERIES, CATEGORY, INDEXED
    }

    public Axis() {
    }

    public Axis(Boolean rotated, Boolean showX, Type typeX, Set<String> categoriesX, Boolean localtimeX, Integer heightX, Boolean showY, Type typeY, Set<String> categoriesY, Boolean localtimeY, Integer heightY) {
        this.rotated = rotated;
        this.showX = showX;
        this.typeX = typeX;
        this.categoriesX = categoriesX;
        this.localtimeX = localtimeX;
        this.heightX = heightX;
        this.showY = showY;
        this.typeY = typeY;
        this.categoriesY = categoriesY;
        this.localtimeY = localtimeY;
        this.heightY = heightY;
    }

    @Override
    protected void preScriptBuild() {
        if (rotated != null) {
            addChild(new ValueBlock("rotated", rotated));
        }

        if (showX != null || typeX != null || categoriesX != null || localtimeX != null) {
            ObjectBlock obj = new ObjectBlock();
            obj.setName("x");

            if (showX != null) {
                obj.addChild(new ValueBlock("show", showX));
            }
            if (typeX != null) {
                obj.addChild(new ValueBlock("type", typeX.name().toLowerCase()));
            }
            if (categoriesX != null) {
                obj.addChild(new ArrayBlock("categories", JSTools.commaSeparatedStringsQuoted(categoriesX)));
            }
            if (localtimeX != null) {
                obj.addChild(new ValueBlock("localtime", localtimeX));
            }
            if (heightX != null) {
                obj.addChild(new ValueBlock("height", heightX));
            }
            addChild(obj);
        }

        if (showY != null || typeY != null || categoriesY != null || localtimeY != null) {
            ObjectBlock obj = new ObjectBlock();
            obj.setName("y");

            if (showY != null) {
                obj.addChild(new ValueBlock("show", showY));
            }
            if (typeY != null) {
                obj.addChild(new ValueBlock("type", typeY.name().toLowerCase()));
            }
            if (categoriesY != null) {
                obj.addChild(new ArrayBlock("categories", JSTools.commaSeparatedStringsQuoted(categoriesY)));
            }
            if (localtimeY != null) {
                obj.addChild(new ValueBlock("localtime", localtimeY));
            }
            if (heightY != null) {
                obj.addChild(new ValueBlock("height", heightY));
            }
            addChild(obj);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Boolean getRotated() {
        return rotated;
    }

    public void setRotated(Boolean rotated) {
        this.rotated = rotated;
    }

    public Boolean getShowX() {
        return showX;
    }

    public void setShowX(Boolean showX) {
        this.showX = showX;
    }

    public Type getTypeX() {
        return typeX;
    }

    public void setTypeX(Type typeX) {
        this.typeX = typeX;
    }

    public Set<String> getCategoriesX() {
        return categoriesX;
    }

    public void setCategoriesX(Set<String> categoriesX) {
        this.categoriesX = categoriesX;
    }

    public Boolean getLocaltimeX() {
        return localtimeX;
    }

    public void setLocaltimeX(Boolean localtimeX) {
        this.localtimeX = localtimeX;
    }

    public Integer getHeightX() {
        return heightX;
    }

    public void setHeightX(Integer heightX) {
        this.heightX = heightX;
    }

    public Boolean getShowY() {
        return showY;
    }

    public void setShowY(Boolean showY) {
        this.showY = showY;
    }

    public Type getTypeY() {
        return typeY;
    }

    public void setTypeY(Type typeY) {
        this.typeY = typeY;
    }

    public Set<String> getCategoriesY() {
        return categoriesY;
    }

    public void setCategoriesY(Set<String> categoriesY) {
        this.categoriesY = categoriesY;
    }

    public Boolean getLocaltimeY() {
        return localtimeY;
    }

    public void setLocaltimeY(Boolean localtimeY) {
        this.localtimeY = localtimeY;
    }

    public Integer getHeightY() {
        return heightY;
    }

    public void setHeightY(Integer heightY) {
        this.heightY = heightY;
    }
}
