package org.javalab.inetmagaz.org.javalab.inetmagaz.filter;

import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Usertypes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by timur on 27.03.2014.
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest  servletRequest , ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest &&
                servletResponse instanceof HttpServletResponse) {
            HttpSession session=((HttpServletRequest) servletRequest).getSession();
            User user = (User) session.getAttribute("User");
            if(user==null){((HttpServletResponse) servletResponse).sendRedirect("../index.jsp");}
            if (user.getType() != Usertypes.ADMIN) {
               ((HttpServletResponse) servletResponse).sendRedirect("../index.jsp");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
