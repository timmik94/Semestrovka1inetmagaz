package org.apache.jsp.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import org.javalab.inetmagaz.model.Item;

public final class Redactor_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title></title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");


    HttpSession sesion=request.getSession();
    ArrayList<Item> items= (ArrayList<Item>) request.getAttribute("items");

    for (int i=0;i<items.size();i++){

        Item item=items.get(i);

        
      out.write("<p>");
      out.print(item.toString());
      out.write("</p>");


    }

      out.write("\r\n");
      out.write("<h2>Add new Item </h2>\r\n");
      out.write("<form method=\"post\" action=\"/Admin/add\" >\r\n");
      out.write("   <p>name: <input name=\"name\" size=\"30\" type=\"text\" VALUE=\" \"></p>\r\n");
      out.write("   <p>coast: <input name=\"coast\" size=\"5\" type=\"number\"></p>\r\n");
      out.write("    <p>about: <input name=\"description\" type=\"text\" value=\" \"></p>\r\n");
      out.write("    <button type=\"submit\">add new item</button>\r\n");
      out.write("</form>\r\n");
      out.write("<h2>Redact existing item</h2>\r\n");
      out.write("<form method=\"post\" action=\"/Admin/redact\">\r\n");
      out.write("  <p>redact item id:  <input name=\"id\" type=\"number\"></p>\r\n");
      out.write("    <p> new name: <input name=\"name\" type=\"text\"></p>\r\n");
      out.write("   <p>new coast: <input name=\"coast\" type=\"number\"></p>\r\n");
      out.write("   <p>new describtion: <input name=\"text\" type=\"text\"></p>\r\n");
      out.write("    <button type=\"submit\">redact</button>\r\n");
      out.write("</form>\r\n");
      out.write("<h2>Delete item</h2>\r\n");
      out.write("<form method=\"post\" action=\"/Admin/delete\">\r\n");
      out.write("  <p>del item id:  <input type=\"number\" name=\"id\"/></p>\r\n");
      out.write("    <button type=\"submit\">delete</button>\r\n");
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
