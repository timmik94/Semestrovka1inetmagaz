package org.javalab.inetmagaz.factory;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.dao.UserDao;
import org.javalab.inetmagaz.dao.ZakazDao;

/**
 * Created by timur on 11.04.2014.
 */
public abstract class AbstractDaoFactory {
    public abstract ItemDao getItemDao();
    public abstract UserDao getUserDao();
    public abstract ZakazDao getZakazDao();
}
