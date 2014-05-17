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
@RequestMapping("/Admin/delete")
public class DeleteAction {
    @RequestMapping(method = RequestMethod.POST)
    public String delete(Model m, @RequestParam(value = "id") Integer id){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        if(id!=null){
        if(itemDao.getItem(id)!=null){
            itemDao.removeItem(id);}}

        return "/data";
    }
}
