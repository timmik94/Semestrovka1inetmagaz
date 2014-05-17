package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.dao.UserDao;
import org.javalab.inetmagaz.factory.AbstractDaoFactory;
import org.javalab.inetmagaz.factory.DaoFactoryBuilder;
import org.javalab.inetmagaz.model.Item;
import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Usertypes;
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
@RequestMapping("/")
public class LoginAction {
    @RequestMapping(method = RequestMethod.GET)
    public  String doGet()
    {return "index.jsp";}

   @RequestMapping(method = RequestMethod.POST)
   public String login(@RequestParam(value = "login")String login,@RequestParam(value = "password")String password,HttpSession session,Model m){
        AbstractDaoFactory daoFactory= DaoFactoryBuilder.getFactory();
        UserDao userDao=daoFactory.getUserDao();
       if(login.equals("")){return "index.jsp";}
       if(password.equals("")){return "index.jsp";}
        User user=userDao.getUser(login,password);
        if(user==null){
            if (!userDao.havelogin(login)){return "index.jsp";}
            m.addAttribute("message","");
            return "registry.jsp";}
        else{
            session.setAttribute("User",user);
            if(user.getType()== Usertypes.ADMIN){return("data");}
           if(user.getType()==Usertypes.USER){
                session.setAttribute("byuitems",new ArrayList<Item>());
                return ("data");}
            return "index.jsp";
        }

    }
}
