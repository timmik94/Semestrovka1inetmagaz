package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.UserDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.javalab.inetmagaz.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by timur on 11.05.2014.
 */
@Controller
@RequestMapping("/registry")
public class RegistrationAction {
    @RequestMapping(method = RequestMethod.POST)
    public String registrate(HttpSession session,@RequestParam(value = "login")String login,@RequestParam(value = "password")String password,Model m){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        UserDao userDao=daoFactory.getUserDao();
        if(userDao.havelogin(login)&&password.length()>6){
            userDao.insertUser(login,password,false);
            User user= userDao.getUser(login, password);
            session.setAttribute("User",user);
            session.setAttribute("byuitems",new ArrayList<Item>());
            return "data";
        }else{
            if(password.length()>6){
            m.addAttribute("message","login don't exsist");}
            else { m.addAttribute("message","short password");}
            return  "registry.jsp";
        }

    }
}
