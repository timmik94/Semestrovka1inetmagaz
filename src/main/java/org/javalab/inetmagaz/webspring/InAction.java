package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ItemDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Usertypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 20.04.2014.
 */
@Controller
@RequestMapping("/data")
public class InAction {
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public String showall(Model m,HttpSession session){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ItemDao itemDao=daoFactory.getItemDao();
        List<Item> items=itemDao.getall();
        ArrayList<Item> items1=(ArrayList<Item>)items;
        m.addAttribute("items",items1);
        System.out.print("started getting");
        User user= (User) session.getAttribute("User");
        if(user.getType()== Usertypes.ADMIN){
        return "Admin/Redactor.jsp";}else {return "User/home.jsp";}
    }


}
