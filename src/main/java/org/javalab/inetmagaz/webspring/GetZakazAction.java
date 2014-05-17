package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.ZakazDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Zakaz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by timur on 12.05.2014.
 */
@Controller
@RequestMapping("/User/getzakaz")
public class GetZakazAction {
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public String getzakaz(HttpSession session){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        ZakazDao zakazDao=daoFactory.getZakazDao();
        User user= (User) session.getAttribute("User");
        List<Zakaz> zakazs=zakazDao.getzakaz(user.getLogin());
        session.setAttribute("zakaz", zakazs);
        return "User/Zakazi.jsp";
    }

}
