package org.javalab.inetmagaz.webspring;

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
@RequestMapping("/User/delitem")
public class FromCorsinAction {
    @RequestMapping(method = RequestMethod.POST)
    public String delitem(HttpSession session,@RequestParam(value = "id") int id){
        ArrayList<Item> items= (ArrayList<Item>) session.getAttribute("byuitems");
        for (int i=0;i<items.size();i++){
            Item item=items.get(i);
            if(item.getId()==id){items.remove(i);break;}
        }
        return("User/corsina.jsp");

    }
}
