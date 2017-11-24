import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Juoma extends HttpServlet { 

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
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><p/>");
		out.println("<p><a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a><br /");
		out.println("<a href=\"Kayttajat?tunnus=2\">Rekister&ouml;ityneet</a></p>");
		out.println("<p><a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
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
  	}
	else  {
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
	}

       out.println("<div class=\"main\">");
       
	int dtunnus = Integer.parseInt(req.getParameter("dtunnus"));
	
	String juomatiedot = "select Drinkki.dnimi, Drinkki.ajankohta, Drinkki.makeus, Drinkki.lampotila, Drinkki.valmistus , Laji.lanimi, Lasi.lnimi " +
            "from Drinkki, Lasi, Laji where Drinkki.ltunnus=Lasi.ltunnus and Drinkki.latunnus=Laji.latunnus and Drinkki.dtunnus=?";	
	String ainesosat = "select Kooste.maara, Kooste.tyyppi, Kooste.ainesosa from Drinkki, Kooste where Kooste.dtunnus=Drinkki.dtunnus and Drinkki.dtunnus=?";
	String ohjeet = "select ohje, kuvaus from Drinkki where dtunnus=?";
	String ehdotukset = "select ehdotus from Drinkki where dtunnus=?";
	
       PreparedStatement stm = null;
       ResultSet rs = null; 

       try { 
         stm = con.prepareStatement(juomatiedot);  
         stm.setInt(1,dtunnus);
			rs = stm.executeQuery(); 
			  	 
   	 while(rs.next()) { 
		out.println("<h2>" +rs.getString("dnimi")+ "</h2>");
		out.println("<table class=\"keskitetty\">");		
		
		String laji = rs.getString("lanimi");
		String ajankohta = rs.getString("ajankohta");
		String makeus = rs.getString("makeus");
		String lampotila = rs.getString("lampotila");
		String valmistus = rs.getString("valmistus");
		
		out.println("<tr><td class=\"paksu\">Laji:</td><td>" +laji+ "</td></tr>");

		if (ajankohta.equals("valitse") != true) {
		out.println("<tr><td class=\"paksu\">Ajankohta:</td><td>" +ajankohta+ "</td></tr>");
		}
		if (makeus.equals("valitse") != true) {
		out.println("<tr><td class=\"paksu\">Makeus:</td><td>" +makeus+ "</td></tr>");
		}
		if (lampotila.equals("valitse") != true) {		
		out.println("<tr><td class=\"paksu\">L&auml;mp&ouml;tila:</td><td>" +lampotila+ "</td></tr>");
		}
		if (valmistus.equals("valitse") != true) {
		out.println("<tr><td class=\"paksu\">Valmistustapa:</td><td>" +valmistus+ 
		"</td></tr>");
		}
		
		out.println("<tr><td class=\"paksu\">Lasi:</td><td>" +rs.getString("lnimi")+"</td></tr>");
		out.println("<tr><td class=\"paksu\">Ainekset:</td><td>");
		}

      stm = con.prepareStatement(ainesosat);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
	 while(rs.next()) { 
		out.println(rs.getInt("maara")+ "&nbsp;" +rs.getString("tyyppi")+ "&nbsp;" +rs.getString("ainesosa")+ "<br />");
	          }
		out.println("</td></tr>");
		
      stm = con.prepareStatement(ohjeet);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
	 while(rs.next()) { 
	 
	String ohje = rs.getString("ohje");
	String kuvaus = rs.getString("kuvaus");
	
	if (ohje != null) {
	out.println("<tr><td class=\"paksu\">Ohje:</td>");
	out.println("<td>" +ohje+ "</td></tr>");
	}
		
	if (kuvaus != null) {
	out.println("<tr><td class=\"paksu\">Kuvaus:</td>");
	out.println("<td>" +kuvaus+ "</td></tr>");
	}
	}
   out.println("</table>");
	
	 stm = con.prepareStatement(ehdotukset);  
    stm.setInt(1,dtunnus);
	 rs = stm.executeQuery(); 
	 while(rs.next()) { 

	int ehdotus = rs.getInt("ehdotus");

	if (ehdotus == 2 && ktunnus != 0 && taso == 1) {
		out.println("<div class=\"drinkki\">");
		out.println("<table><tr>");
		out.println("<td><form method=\"post\" action=\"MuokkausDrinkki?dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Muokkaa\" /></form></td>");
      out.println("<td><form method=\"post\" action=\"Muokkaus?tunnus=3&dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Tallenna\" /></form></td>");		
      out.println("<td><form method=\"post\" action=\"Poista?tunnus=1&dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Poista\" /></form></td>");
		out.println("</tr></table>");
		out.println("</div>");
	}

	if (ehdotus == 2 && ktunnus != 0 && taso == 2) {
		out.println("<div class=\"drinkki\">");
		out.println("<table><tr>");
		out.println("<td><form method=\"post\" action=\"MuokkausDrinkki?dtunnus=" +dtunnus+ "\" class=\"sisennys\"><input type=\"submit\" value=\"Muokkaa\" /></form></td>");
      out.println("<td><form method=\"post\" action=\"Muokkaus?tunnus=3&dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Tallenna\" /></form></td>");		
		out.println("</tr></table>");
		out.println("</div>");
	}

	if (ehdotus == 1 && ktunnus != 0 && taso == 1) {
		out.println("<div class=\"drinkki\">");
		out.println("<table><tr>");
		out.println("<td><form method=\"post\" action=\"MuokkausDrinkki?dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Muokkaa\" /></form></td>");
      out.println("<td><form method=\"post\" action=\"Poista?tunnus=1&dtunnus=" +dtunnus+ "\"><input type=\"submit\" value=\"Poista\" /></form></td>");
		out.println("</tr></table>");
		out.println("</div>");
	}

	if (ehdotus == 1 && ktunnus != 0 && taso == 2) {
		out.println("<div class=\"drinkki\">");
		out.println("<table><tr>");
		out.println("<td><form method=\"post\" action=\"MuokkausDrinkki?dtunnus=" +dtunnus+ "\" class=\"sisennys\"><input type=\"submit\" value=\"Muokkaa\" /></form></td>");
		out.println("</tr></table>");
		out.println("</div>");
	}
	}
       } catch (SQLException ee) {
             out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
       } finally {
             try {
                if (rs!=null) rs.close(); 
                if (stm!=null) stm.close(); 
                Yhteys.closeConnection(con);
             } catch(SQLException e) { 
                out.println("An SQL Exception was thrown."); 
             }
       }  
       out.println("");
       out.println("</div></body></html>");
   } 
}

