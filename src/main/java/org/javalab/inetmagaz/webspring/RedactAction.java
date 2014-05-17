package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by timur on 21.04.2014.
 */
@Configuration
@RequestMapping("/Admin/redact")
public class RedactAction {
    @RequestMapping(method = RequestMethod.POST)
    public String redact(Model m,
                         @RequestParam(value = "id")Integer id,
                         @RequestParam(value = "name") String name,
                         @RequestParam(value = "coast")Integer coast,
                         @RequestParam(value = "text")String text){
        if(id==null){ return "Admin/data";}
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        Item item= itemDao.getItem(id);
        if(item!=null){
        if (name.equals("")){name=item.getName();}
        if(coast==null){
        coast=item.getCoast();}
        if(text.equals("")){text=item.getText();}
        itemDao.updateItem(id,coast,name,text);}
        return "/data";
    }
}
