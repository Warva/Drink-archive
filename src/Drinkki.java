import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Drinkki extends HttpServlet { 

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
     
	if (ktunnus != 0 && taso == 1) {
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><br />");
		out.println("<a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a></p>");
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
		
		String lasi = "select lnimi, ltunnus from Lasi order by lnimi";
		String laji = "select lanimi, latunnus from Laji order by lanimi";

		int ainelm = Integer.parseInt(req.getParameter("ainelm"));
				
		Statement stm= null;
      ResultSet rs = null;        
      
		try { 
		
		out.println("<p class=\"keskitetty2\">T&auml;hdell&auml; merkityt kent&auml;t ovat pakollisia.</p>");
		out.println("<form class=\"keskitetty\" method=\"post\" action=\"Lisays?tunnus=1&ainelm=" +ainelm+ "\">");
 		out.println("<p><span class=\"paksu\">Drinkin nimi *</span><br />");
		out.println("<input type=\"text\" size=\"15\" maxlength=\"99\" name=\"dnimi\" /></p>");
		out.println("<p><span class=\"paksu\">Ajankohta</span><br /><select name=\"ajankohta\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Aperitiivi\">Aperitiivi (ennen ruokaa)</option>");
		out.println("<option value=\"All Day\">All Day</option>");
		out.println("<option value=\"Digestiivi\">Digestiivi (ruuan j&auml;lkeen)</option>");
 		out.println("<option value=\"Fancy\">Fancy</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Laji *</span><br /><select name=\"latunnus\">");
		out.println("<option value=\"0\">--valitse--</option>");
		
		stm= con.createStatement();
		rs = stm.executeQuery(laji);
	
           while(rs.next()) { 
		out.println("<option value=\"" +rs.getInt("latunnus")+ "\">" +rs.getString("lanimi")+ "</option>");
           }
           
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Lasi *</span><br /><select name=\"ltunnus\">");
		out.println("<option value=\"0\">--valitse--</option>");
		   
		stm= con.createStatement();
		rs = stm.executeQuery(lasi);
			
           while(rs.next()) { 
		out.println("<option value=\"" +rs.getInt("ltunnus")+ "\">" +rs.getString("lnimi")+ "</option>");
           }
           
      out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Valmistustapa</span><br /><select name=\"valmistus\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Sekoitetaan\">Sekoitetaan</option>");
		out.println("<option value=\"Ravistetaan\">Ravistetaan</option>");
		out.println("<option value=\"Rakennetaan\">Rakennetaan</option>");
		out.println("<option value=\"Hämmennetään\">H&auml;mmennet&auml;&auml;n</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Makeus</span><br /><select name=\"makeus\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Kuivahko\">Kuivahko</option>");
		out.println("<option value=\"Kuiva\">Kuiva</option>");
		out.println("<option value=\"Makeahko\">Makeahko</option>");
		out.println("<option value=\"Makea\">Makea</option>");
		out.println("<option value=\"Hapanimelä\">Hapanimel&auml;</option>");
		out.println("<option value=\"Hapan\">Hapan</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">L&auml;mp&ouml;tila</span><br /><select name=\"lampotila\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Kuuma\">Kuuma</option>");
		out.println("<option value=\"Viileä\">Viile&auml;</option>");
		out.println("<option value=\"Jäinen\">J&auml;inen</option>");
		out.println("</select></p>");
		out.println("<table>");
		out.println("<tr><td><span class=\"paksu\">M&auml;&auml;r&auml;</span></td>");
		out.println("<td><span class=\"paksu\">Tyyppi</span></td>");
		out.println("<td><span class=\"paksu\">Ainesosa</span></td></tr>");

		while (ainelm > 0){
		out.println("<tr><td><input type=\"text\" size=\"3\" maxlength=\"5\" name=\"maara" +ainelm+ "\" /></td>");
		out.println("<td><select name=\"tyyppi" +ainelm+ "\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"tippa\">tippa</option>");
		out.println("<option value=\"tl\">tl</option>");
		out.println("<option value=\"rkl\">rkl</option>");
		out.println("<option value=\"kpl\">kpl</option>");
		out.println("<option value=\"ml\">ml</option>");
		out.println("<option value=\"cl\">cl</option>");
		out.println("<option value=\"dl\">dl</option>");
		out.println("<option value=\"l\">l</option>");
		out.println("</select></td>");
		out.println("<td><input type=\"text\" size=\"15\" maxlength=\"99\" name=\"ainesosa" +ainelm+ "\" /></td></tr>");
		--ainelm;
		}		
		out.println("</table><table>");
		out.println("<tr><td><span class=\"paksu\">Ohje</span><br /><textarea cols=\"35\" rows=\"10\" name=\"ohje\"></textarea></td></tr>");
		out.println("<tr><td><span class=\"paksu\">Kuvaus</span><br /><textarea cols=\"35\" rows=\"10\" name=\"kuvaus\"></textarea></td></tr>");	
		out.println("</table><table>");
		out.println("<tr><td><input type=\"submit\" value=\"L&auml;het&auml;\" /></td><td><input type=\"reset\" value=\"Tyhjenn&auml;\" /></td></tr>");
		out.println("</table></form>");
  
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

