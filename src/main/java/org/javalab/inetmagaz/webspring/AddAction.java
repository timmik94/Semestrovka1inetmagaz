package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by timur on 21.04.2014.
 */
@Configuration
@RequestMapping("/Admin/add")
public class AddAction {

    @RequestMapping(method = RequestMethod.POST)
    public String add(Model m,@RequestParam(value = "name") String name,
                      @RequestParam(value = "coast")Integer coast,
                      @RequestParam(value = "description")String about){
        if(name.equals(" ")||coast==null){return "Admin/data";}else{
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        itemDao.insertitem(name,coast,about);
        return "/data";}
    }
}
