import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DrinkkiAlku extends HttpServlet { 

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

       out.println("<?xml version=\"1.0\" encoding=\"iso-8859-15\"?>");
       out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"");
       out.println("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");

       out.println("<html lang=\"fi\" xml:lang=\"fi\" xmlns=\"http://www.w3.org/1999/xhtml\">");
       out.println("<head><title>Drinkkiarkisto</title>");
       out.println("<link href=\"../tyyli.css\" type=\"text/css\" rel=\"stylesheet\" /></head>");
       out.println("<body>");
       out.println("<div class=\"valikko\">");

	if (ktunnus != 0 && taso == 1) {
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><p/>");
		out.println("<p><a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a><br />");
		out.println("<a href=\"Kayttajat?tunnus=2\">Rekister&ouml;ityneet</a></p>");
		out.println("<p><a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
		
		out.println("<div class=\"main\"><h1>Lis&auml;&auml; drinkki</h1>");
	}
	else if (ktunnus != 0 && taso == 2){
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
		out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><br />");
		out.println("<a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
		
		out.println("<div class=\"main\"><h1>Lis&auml;&auml; drinkki</h1>");
	}

	else {
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

		out.println("<div class=\"main\"><h1>Ehdota omaa drinkki&auml;</h1>");
	}

		out.println("<p class=\"sisennys\">Kerro aineosien lukum&auml;&auml;r&auml;.<br />");	
		out.println("<form class=\"sisennys2\" method=\"post\" action=\"Drinkki\">");
		out.println("<select name=\"ainelm\">");
		out.println("<option value=\"1\">1</option>");
		out.println("<option value=\"2\">2</option>");
		out.println("<option value=\"3\">3</option>");
		out.println("<option value=\"4\">4</option>");
		out.println("<option value=\"5\">5</option>");
		out.println("<option value=\"6\">6</option>");
		out.println("<option value=\"7\">7</option>");
		out.println("<option value=\"8\">8</option>");
		out.println("<option value=\"9\">9</option>");
		out.println("<option value=\"10\">10</option>");
		out.println("</select>");
		out.println("<input type=\"submit\" value=\"Anna lukum&auml;&auml;r&auml;\" />");
		out.println("</form></p>");

		out.println("<p style=\"margin-left: 100px; margin-top: 10px;\">Alla olevassa listassa on kaikki lasivaihtoehdot. Mik&auml;li drinkin tarvitsemaa lasia ei ole listattuna niin voit lis&auml;t&auml; sen listan alla olevalla lomakkeella.</p>");
		
		out.println("<table class=\"keskitetty\">");
		
		String lasit = "select ltunnus, lnimi from Lasi order by lnimi";
		
		Statement stm= null;
      ResultSet rs = null;        
      
      try { 
      
      // Listataan tietokannassa olevat lasivaihtoehdot
      stm = con.createStatement();
		rs = stm.executeQuery(lasit);		
		
      while(rs.next()) { 
		out.println("<tr><td>" +rs.getString("lnimi")+ "</span></td>");
		if (ktunnus != 0 && taso == 1) {
		out.println("<td><form method=\"post\" action=\"Poista?tunnus=2&ltunnus=" +rs.getInt("ltunnus")+ "\" class=\"sisennys2\"><input type=\"submit\" value=\"Poista\" /></form></td>");
		}
		out.println("</tr>");
		}
		out.println("</table>");
      out.println("<form class=\"keskitetty\" method=\"post\" action=\"Lisays?tunnus=2\">");
		out.println("<span class=\"paksu\">Lasi</span><br /><input type=\"text\" name=\"lnimi\" size=10 maxlength=\"99\"/>");
		out.println("<p><input type=\"submit\" value=\"Lis&auml;&auml; lasi\" /><input type=\"reset\" value=\"Tyhjenn&auml;\" /></p>");
		out.println("</form>");
      } catch (SQLException ee) {
              out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
       }	finally {
             try {
                if (rs!=null) rs.close(); 
                if (stm!=null) stm.close(); 
                Yhteys.closeConnection(con);
             } catch(SQLException e) { 
                out.println("An SQL Exception was thrown."); 
             }
            }
      		out.println("</div></body></html>");
	}
}

