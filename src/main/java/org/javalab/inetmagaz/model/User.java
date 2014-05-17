package org.javalab.inetmagaz.model;

/**
 * Created by timur on 24.03.2014.
 */
public class User {
    private  int id;
    private  String login;
    private Usertypes type;
    public User(int td,String login,Usertypes type){
        this.id=id;
        this.login=login;
        this.type=type;
    }

    public Usertypes getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

}
