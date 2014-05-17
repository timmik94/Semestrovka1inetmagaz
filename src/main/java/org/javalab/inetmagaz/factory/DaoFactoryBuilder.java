package org.javalab.inetmagaz.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by timur on 11.04.2014.
 */
public class DaoFactoryBuilder {
    public static AbstractDaoFactory getFactory(){
        BeanFactory beanFactory=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        AbstractDaoFactory daoFactory= (AbstractDaoFactory) beanFactory.getBean("DaoFactory");
        return daoFactory;
    }
}
