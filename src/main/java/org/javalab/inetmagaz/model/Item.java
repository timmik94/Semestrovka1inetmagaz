package org.javalab.inetmagaz.model;

/**
 * Created by timur on 24.03.2014.
 */
public class Item {
    @Override
    public String toString() {
        return "Item " +
                "id=" + id +
                ", " + name +  ", coast=" + coast + "  "+ text ;
    }

    public Item(int id, String name, int coast, String text) {
        this.id = id;
        this.name = name;
        this.coast = coast;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoast() {
        return coast;
    }

    public String getText() {
        return text;
    }

    private int id;
    private String name;
    private int coast;
    private String text;
}
