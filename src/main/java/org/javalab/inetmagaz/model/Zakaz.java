package org.javalab.inetmagaz.model;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;

/**
 * Created by timur on 11.05.2014.
 */
public class Zakaz {
   private int id;
    private String userlogin;
    private int count;
    private Status status;
    private int itemid;

    public String toString(){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        Item item=itemDao.getItem(itemid);
        String st="";
        switch (status){
            case Accepted: st="assepted";break;
            case sended:st="sended";break;
            case done:st="done";break;
            case canceld:st="canceld";break;
            case ready:st="ready";break;

        }
        return "zakaz:"+item.getName()+" "+item.getCoast()+" "+st;
    }

    public Zakaz(String userlogin, int count, Status status, int itemid) {
        this.userlogin = userlogin;
        this.count = count;
        this.status = status;
        this.itemid = itemid;
    }

    public Zakaz(int id, String userlogin, int itemid, int count, Status status) {
        this.id=id;
        this.userlogin = userlogin;
        this.itemid = itemid;
        this.count = count;
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
