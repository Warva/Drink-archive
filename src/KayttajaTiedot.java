import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class KayttajaTiedot extends HttpServlet { 

public void service(HttpServletRequest req, HttpServletResponse res) 
       throws ServletException, IOException {

	int ktunnus = 0;
	int taso = 0;

	HttpSession session = req.getSession(false);
	if (session != null) {
	ktunnus = (Integer) session.getAttribute("ktunnus");
	taso = (Integer) session.getAttribute("taso");
	}
	
       ServletOutputStream out;  
       res.setContentType("text/html");
       out= res.getOutputStream();  
      
       Connection con=null;
       con= Yhteys.createDbConnection();
       if (con==null) {
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
      out.println("<div class=\"main\"><h1>Tervetuloa drinkkiarkistoon</h1>");
		out.println("<p class=\"keskitetty\">T&auml;m&auml; on harkkaty&ouml;n&auml; toteutettu drinkkiarkisto.</p>");
		out.println("<p class=\"keskitetty2\">Ei tietokantayhteytt&auml;.</p>");
      out.println("</div></body></html>");
        return;
       }

       // connection established
      
       out.println("<?xml version=\"1.0\" encoding=\"iso-8859-15\"?>");
       out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
       out.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");

       out.println("<html lang=\"fi\" xml:lang=\"fi\" xmlns=\"http://www.w3.org/1999/xhtml\">");
       out.println("<head><title>Drinkkiarkisto</title>");
       out.println("<link href=\"../tyyli.css\" type=\"text/css\" rel=\"stylesheet\" /></head>");
       out.println("<body>");
       out.println("<div class=\"valikko\">");

	if (session == null || taso == 3) {
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

		out.println("<div class=\"main\"><h1>Tervetuloa drinkkiarkistoon</h1>");
		out.println("<p class=\"keskitetty\">T&auml;m&auml; on harkkaty&ouml;n&auml; toteutettu drinkkiarkisto.</p>");
		out.println("<p class=\"keskitetty2\">Et ole kirjautunut sis&auml;&auml;n.</p></div></body></html>");
	}

	if (ktunnus != 0 && taso == 2){
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><br />");
		out.println("<a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
		
		out.println("<div class=\"main\"><h1>Tervetuloa drinkkiarkistoon</h1>");
		out.println("<p class=\"keskitetty\">T&auml;m&auml; on harkkaty&ouml;n&auml; toteutettu drinkkiarkisto.</p>");
		out.println("<p class=\"keskitetty2\">Sinulla ei ole tarvittavia valtuuksia.</p></div></body></html>");
	}

	if (ktunnus != 0 && taso == 1) {
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a></p>");
		out.println("</p><a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a><br />");
		out.println("<a href=\"Kayttajat?tunnus=2\">Rekister&ouml;ityneet</a></p>");
		out.println("<p><a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
   
	out.println("<div class=\"main\">");
	
	String kayttaja = req.getParameter("kayttaja");
	
	String kayttajatiedot = "select ktunnus, kayttaja, taso, knimi, maili from Kayttaja where kayttaja = ?";
	
    PreparedStatement stm = null;
    ResultSet rs = null; 
 
     try { 
 
         stm = con.prepareStatement(kayttajatiedot);  
         stm.setString(1,kayttaja);
			rs = stm.executeQuery();   	 
   	
   	 while(rs.next()) { 
  		
  		out.println("<h1>K&auml;ytt&auml;j&auml; " +rs.getString("kayttaja")+ "</h1>");	
		out.println("<table class=\"keskitetty\">");	
		out.println("<tr><td class=\"paksu\">Nimi:</td><td>" +rs.getString("knimi")+ "</td></tr>");
		out.println("<tr><td class=\"paksu\">Tunnus:</td><td>" +rs.getString("kayttaja")+ 
		"</td></tr>");
		out.println("<tr><td class=\"paksu\">Email:</td><td>" +rs.getString("maili")+ 
		"</td></tr>");
		out.println("<tr><td class=\"paksu\">Taso:</td><td>" +rs.getInt("taso")+"</td></tr>");
		
		out.println("<tr><td><form method=\"post\" action=\"Muokkaus?tunnus=2&ktunnusapu=" +rs.getInt("ktunnus")+ "\">");
		out.println("<p><span class=\"paksu\">Taso</span><br /><select name=\"tasou\">");
		out.println("<option value=\"0\">--valitse--</option>");
		out.println("<option value=\"1\">1. kaikki oikeudet</option>");
		out.println("<option value=\"2\">2. vain drinkkien lis&auml;ys ja muokkaus</option>");
		out.println("</select></p></td></tr>");
		
		out.println("<tr><td><input type=\"submit\" value=\"Tallenna uusi taso\" /></form></td>");
		out.println("<td><form method=\"post\" action=\"Poista?tunnus=3&ktunnusapu=" +rs.getInt("ktunnus")+ "\" class=\"sisennys\"><input type=\"submit\" value=\"Poista tunnus\" /></form></td></tr>");
		out.println("</table></div></body></html>");
		}
       } catch (SQLException ee) {
             out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
              }       
       finally {
             try {
                if (rs!=null) rs.close(); 
                if (stm!=null) stm.close(); 
                Yhteys.closeConnection(con);
             } catch(SQLException e) { 
                out.println("An SQL Exception was thrown."); 
             }
       }   out.println("</div></body></html>");
      }
}
}

