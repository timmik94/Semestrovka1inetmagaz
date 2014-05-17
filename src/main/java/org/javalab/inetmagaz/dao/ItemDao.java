package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.*;

import java.util.ArrayList;

/**
 * Created by timur on 10.04.2014.
 */
public interface ItemDao {
    public Item getItem(int id);
    public void removeItem(int id);
    public void updateItem(int id,int coast,String name,String text);
    public java.util.List<Item> getall();
    public void insertitem(String name,int coast,String text);

}
