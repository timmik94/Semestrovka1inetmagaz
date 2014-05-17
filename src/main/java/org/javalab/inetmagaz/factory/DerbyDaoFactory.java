package org.javalab.inetmagaz.factory;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.dao.UserDao;
import org.javalab.inetmagaz.dao.ZakazDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by timur on 11.04.2014.
 */
public class DerbyDaoFactory extends AbstractDaoFactory {
    @Override
    public ItemDao getItemDao() {
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ItemDao itemDao= (ItemDao) beanFactory.getBean("ItemDao");
        return itemDao;
    }

    @Override
    public UserDao getUserDao() {
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao= (UserDao) beanFactory.getBean("UserDao");
        return userDao;
    }

    @Override
    public ZakazDao getZakazDao() {
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ZakazDao zakazDao=(ZakazDao)beanFactory.getBean("ZakazDao");
        return zakazDao;
    }
}
