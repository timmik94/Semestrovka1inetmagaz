package org.javalab.inetmagaz.webspring;

import org.javalab.inetmagaz.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by timur on 12.05.2014.
 */
@Controller
@RequestMapping("/exit")
public class ExitAction {
    @RequestMapping(method = RequestMethod.GET)
    public String exit(HttpSession session){
        User user= (User) session.getAttribute("User");
        user=null;
        return "index.jsp";

    }
}
