package org.apache.jsp.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.javalab.inetmagaz.model.Item;
import java.util.ArrayList;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title></title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h1>userpage</h1>\r\n");


    HttpSession sesion=request.getSession();
    ArrayList<Item> items= (ArrayList<Item>) request.getAttribute("items");

    for (int i=0;i<items.size();i++){

        Item item=items.get(i);


      out.write("<form action=\"/User/buy\" method=\"post\">");
      out.print(item.toString());
      out.write(" <input type=\"hidden\" value=\"");
      out.print(item.getId());
      out.write("\" name=\"id\"/>  <button type=\"submit\">buy</button></form>");


    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<form action=\"/User/corsina.jsp\" method=\"get\">\r\n");
      out.write("    <button type=\"submit\">to my corsin</button>\r\n");
      out.write("</form>\r\n");
      out.write("<form action=\"/User/getzakaz\" method=\"get\">\r\n");
      out.write("    <button type=\"submit\">my zakazes</button>\r\n");
      out.write("</form>\r\n");
      out.write("<form action=\"/exit\" method=\"get\">\r\n");
      out.write("<button type=\"submit\">exit</button>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
