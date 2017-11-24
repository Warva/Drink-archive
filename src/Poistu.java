import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Poistu extends HttpServlet { 

public void service(HttpServletRequest req, HttpServletResponse res) 
       throws ServletException, IOException {

	HttpSession session = req.getSession(false);
	if (session != null){
	session.invalidate();
	}
	
       ServletOutputStream out;  
       res.setContentType("text/html");
       out= res.getOutputStream();  
      
       out.println("<?xml version=\"1.0\" encoding=\"iso-8859-15\"?>");
       out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
       out.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");

       out.println("<html lang=\"fi\" xml:lang=\"fi\" xmlns=\"http://www.w3.org/1999/xhtml\">");
       out.println("<head><title>Drinkkiarkisto</title>");
       out.println("<link href=\"../tyyli.css\" type=\"text/css\" rel=\"stylesheet\" /></head>");
       out.println("<body>");
       out.println("<div class=\"valikko\">");
       out.println("<p><a href=\"../index.html\">Etusivu</a><br />");
       out.println("<a href=\"Haku\">Haku</a><br />");
       out.println("<a href=\"DrinkkiAlku\">Ehdota omaa drinkki&auml;</a><br />");
       out.println("<a href=\"../rekis.html\">Rekister&ouml;idy</a></p>");
       out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
       out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
     	 out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
		 out.println("<form class=\"vali\" method=\"post\" action=\"Tunnistus\">");
       out.println("<p>Tunnus:<br /><input type=\"text\" name=\"kayttaja\" size=\"10\" maxlength=\"20\" /></p>");
       out.println("<p>Salasana:<br /><input type=\"password\" name=\"salasana\" size=\"10\" maxlength=\"10\" /></p>");
       out.println("<p><input type=\"submit\" value=\"Sis&auml;&auml;n\" /></p></form></div>");	
	 	 out.println("</div><div class=\"main\"><h1>Tervetuloa drinkkiarkistoon</h1>");
		 out.println("<p class=\"keskitetty\">T&auml;m&auml; on harkkaty&ouml;n&auml; toteutettu drinkkiarkisto.</p>");
	  	 
	  	 out.println("<p class=\"keskitetty2\">Olet kirjautunut ulos.</p></div></body></html>");
}
}

