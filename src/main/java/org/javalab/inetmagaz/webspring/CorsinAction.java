package org.javalab.inetmagaz.webspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by timur on 11.05.2014.
 */
@Controller
@RequestMapping("/corsina")
public class CorsinAction {
    @RequestMapping(method = RequestMethod.GET)
    public String doget()
    {
       return "User/corsina.jsp";
    }
}
