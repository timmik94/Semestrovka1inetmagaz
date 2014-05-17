package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ZakazDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.javalab.inetmagaz.model.Status;
import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Zakaz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by timur on 11.05.2014.
 */
@Controller
@RequestMapping("/User/zakaz")
public class ZakazAction {
    @RequestMapping (method = RequestMethod.GET)
    public String setzakaz(HttpSession session){
        ArrayList<Item> items= (ArrayList<Item>) session.getAttribute("byuitems");
        User user= (User) session.getAttribute("User");
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ZakazDao zakazDao=daoFactory.getZakazDao();
        if(items!=null){
        for (int i=0;i<items.size();i++){
        zakazDao.setzakaz(new Zakaz(user.getLogin(),1, Status.Accepted,items.get(i).getId()));}}
        items=new ArrayList<Item>();
        session.setAttribute("byuitems",items);
        return "User/getzakaz";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String cancel(@RequestParam(value = "id") int id){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ZakazDao zakazDao=daoFactory.getZakazDao();
        zakazDao.updatestatus(id,Status.canceld);
        return "User/getzakaz";
    }
}
