package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by timur on 12.05.2014.
 */
@Controller
@RequestMapping("/User/buy")
public class BuyAction {
    @RequestMapping(method = RequestMethod.POST)
    public String buy(HttpSession session,@RequestParam(value = "id") int id){
        ArrayList<Item> items= (ArrayList<Item>) session.getAttribute("byuitems");
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        items.add(itemDao.getItem(id));
        session.setAttribute("byuitems",items);
        return "/data";
    }
}
