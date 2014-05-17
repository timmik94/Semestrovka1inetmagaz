package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.*;

/**
 * Created by timur on 10.04.2014.
 */
public interface UserDao {
    public User getUser(String login,String password);
    public User getUser(String login);
    public boolean insertUser(String login,String password,boolean admin);
    public boolean havelogin(String login);

}
