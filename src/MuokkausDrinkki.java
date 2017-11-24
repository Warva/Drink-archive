import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MuokkausDrinkki extends HttpServlet { 

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
	
	else {

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
	}

	if (ktunnus != 0 && taso == 1) {
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a></p>");
		out.println("<p><a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a><br />");
		out.println("<a href=\"Kayttajat?tunnus=2\">Rekister&ouml;ityneet</a></p>");
		out.println("<p><a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
	}

       out.println("<div class=\"main\"><h1>Muokkaa drinkki&auml;</h1>");

	int dtunnus = Integer.parseInt(req.getParameter("dtunnus"));
	
	String juomatiedot = "select Drinkki.dnimi, Drinkki.ajankohta, Drinkki.makeus, Drinkki.lampotila, Drinkki.valmistus , Laji.lanimi, Lasi.lnimi " +
            "from Drinkki, Lasi, Laji where Drinkki.ltunnus=Lasi.ltunnus and Drinkki.latunnus=Laji.latunnus and Drinkki.dtunnus=?";	
	String ainesosat = "select maara, tyyppi, ainesosa from Kooste where dtunnus=?";
	String aines = "select ainelm from Drinkki where dtunnus=?";
	String ohjeet = "select ohje, kuvaus from Drinkki where dtunnus=?";
	String lasilaji = "select latunnus, ltunnus from Drinkki where dtunnus=?";	
	String lasi = "select lnimi, ltunnus from Lasi order by lnimi";
	String laji = "select lanimi, latunnus from Laji order by lanimi";

       PreparedStatement stm = null;
       ResultSet rs = null; 
       Statement pstm= null;

       try { 
         stm = con.prepareStatement(juomatiedot);  
         stm.setInt(1,dtunnus);
			rs = stm.executeQuery(); 
			  	 
   	 while(rs.next()) { 
   	
		String ajankohta = rs.getString("ajankohta");
		String dnimi = rs.getString("dnimi");
		
		out.println("<h2>" +dnimi+ "</h2>");
		out.println("<form class=\"keskitetty\" method=\"post\" action=\"Muokkaus?tunnus=4&dtunnus=" +dtunnus+ "\">");		
		out.println("<p class=\"keskitetty2\">T&auml;hdell&auml; merkityt kent&auml;t ovat pakollisia.</p>");
		out.println("<form class=\"keskitetty\" method=\"post\" action=\"Lisays?tunnus=1\">");
 		out.println("<p><span class=\"paksu\">Drinkin nimi *</span><br />");
		out.println("<input type=\"text\" size=\"15\" maxlength=\"99\" name=\"dnimi\" value =" +dnimi+ " /></p>");
		out.println("<p><span class=\"paksu\">Ajankohta</span><br /><select name=\"ajankohta\">");	
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Aperitiivi\" ");
		if (ajankohta.equals("Aperitiivi")) {out.print("selected=selected");}
		out.print(">Aperitiivi (ennen ruokaa)</option>");
		out.println("<option value=\"All Day\" ");
		if (ajankohta.equals("All Day")) {out.print("selected=selected");}
		out.print(">All Day</option>");
		out.println("<option value=\"Digestiivi\" ");
 		if (ajankohta.equals("Digestiivi")) {out.print("selected=selected");}
 		out.print(">Digestiivi (ruuan j&auml;lkeen)</option>");
 		out.println("<option value=\"Fancy\" ");
 		if (ajankohta.equals("Fancy")) {out.print("selected=selected");}
 		out.print(">Fancy</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Laji *</span><br /><select name=\"latunnus\">");
		out.println("<option value=\"0\">--valitse--</option>");
		}
		
      stm = con.prepareStatement(lasilaji);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
	
      while(rs.next()) { 
  
      int latunnusapu = rs.getInt("latunnus");

		pstm= con.createStatement();
		rs = pstm.executeQuery(laji);
 
		while(rs.next()) { 
      
      int latunnus = rs.getInt("latunnus");
		String lanimi = rs.getString("lanimi");
    
		out.println("<option value=\"" +latunnus+ "\" ");
		if (latunnus == latunnusapu) {out.println("selected=selected");}
		out.println(">" +lanimi+ "</option>");
       }
      }
           
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Lasi *</span><br /><select name=\"ltunnus\">");
		out.println("<option value=\"0\">--valitse--</option>");
		   
      stm = con.prepareStatement(lasilaji);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
	
      while(rs.next()) { 
  
      int ltunnusapu = rs.getInt("ltunnus");

		pstm= con.createStatement();
		rs = pstm.executeQuery(lasi);
 
		while(rs.next()) { 
      
      int ltunnus = rs.getInt("ltunnus");
		String lnimi = rs.getString("lnimi");
    
		out.println("<option value=\"" +ltunnus+ "\" ");
		if (ltunnus == ltunnusapu) {out.println("selected=selected");}
		out.println(">" +lnimi+ "</option>");
       }
      }
      
    	stm = con.prepareStatement(juomatiedot);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
			  	 
   	while(rs.next()) { 
   	
		String ajankohta = rs.getString("ajankohta");
		String makeus = rs.getString("makeus");
		String lampotila = rs.getString("lampotila");
		String valmistus = rs.getString("valmistus");

      out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Valmistustapa</span><br /><select name=\"valmistus\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Sekoitetaan\" ");
		if (valmistus.equals("Sekoitetaan")) {out.print("selected=selected");}
		out.print(">Sekoitetaan</option>");
		out.println("<option value=\"Ravistetaan\" ");
		if (valmistus.equals("Ravistetaan")) {out.print("selected=selected");}
		out.print(">Ravistetaan</option>");
		out.println("<option value=\"Rakennetaan\" ");
		if (valmistus.equals("Rakennetaan")) {out.print("selected=selected");}
		out.print(">Rakennetaan</option>");
		out.println("<option value=\"Hämmennetään\" ");
		if (valmistus.equals("Hämmennetään")) {out.print("selected=selected");}
		out.print(">H&auml;mmennet&auml;&auml;n</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">Makeus</span><br /><select name=\"makeus\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Kuivahko\" ");
		if (makeus.equals("Kuivahko")) {out.print("selected=selected");}
		out.print(">Kuivahko</option>");
		out.println("<option value=\"Kuiva\" ");
		if (makeus.equals("Kuiva")) {out.print("selected=selected");}
		out.print(">Kuiva</option>");
		out.println("<option value=\"Makeahko\" ");
		if (makeus.equals("Makeahko")) {out.print("selected=selected");}
		out.print(">Makeahko</option>");
		out.println("<option value=\"Makea\" ");
		if (makeus.equals("Makea")) {out.print("selected=selected");}
		out.print(">Makea</option>");
		out.println("<option value=\"Hapanimelä\" ");
		if (makeus.equals("Hapanimelä")) {out.print("selected=selected");}
		out.print(">Hapanimel&auml;</option>");
		out.println("<option value=\"Hapan\" ");
		if (makeus.equals("Hapan")) {out.print("selected=selected");}
		out.print(">Hapan</option>");
		out.println("</select></p>");
		out.println("<p><span class=\"paksu\">L&auml;mp&ouml;tila</span><br /><select name=\"lampotila\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"Kuuma\" ");
		if (lampotila.equals("Kuuma")) {out.print("selected=selected");}
		out.print(">Kuuma</option>");
		out.println("<option value=\"Viileä\" ");
		if (lampotila.equals("Viileä")) {out.print("selected=selected");}
		out.print(">Viile&auml;</option>");
		out.println("<option value=\"Jäinen\" ");
		if (lampotila.equals("Jäinen")) {out.print("selected=selected");}
		out.print(">J&auml;inen</option>");
		out.println("</select></p>");
		out.println("<table>");
		out.println("<tr><td><span class=\"paksu\">M&auml;&auml;r&auml;</span></td>");
		out.println("<td><span class=\"paksu\">Tyyppi</span></td>");
		out.println("<td><span class=\"paksu\">Ainesosa</span></td></tr>");
		}
		out.println("<p style=\"font-weight: bold\">Ohjeen ainesten muuttaminen ei toimi.</p>");

		stm = con.prepareStatement(aines);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
		
      while(rs.next()) { 
      
      int ainelm = rs.getInt("ainelm");
	 	
		stm = con.prepareStatement(ainesosat);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
		
	 	while(rs.next()) { 
	 	
	 	String tyyppi = rs.getString("tyyppi");	
	 	
	 	if (ainelm > 0){
		out.println("<tr><td><input type=\"text\" size=\"3\" maxlength=\"5\" name=\"maara" +ainelm+ "\" value =\"" +rs.getInt("maara")+ "\" /></td>");
		out.println("<td><select name=\"tyyppi" +ainelm+ "\">");
		out.println("<option value=\"valitse\">--valitse--</option>");
		out.println("<option value=\"tippa\" ");
		if (tyyppi.equals("tippa")) {out.print("selected=selected");}
		out.print(">tippa</option>");
		out.println("<option value=\"tl\" ");
		if (tyyppi.equals("tl")) {out.print("selected=selected");}
		out.print(">tl</option>");
		out.println("<option value=\"kpl\" ");
		if (tyyppi.equals("kpl")) {out.print("selected=selected");}
		out.print(">kpl</option>");
		out.println("<option value=\"ml\" ");
		if (tyyppi.equals("ml")) {out.print("selected=selected");}
		out.print(">ml</option>");
		out.println("<option value=\"cl\" ");
		if (tyyppi.equals("cl")) {out.print("selected=selected");}
		out.print(">cl</option>");
		out.println("<option value=\"dl\" ");
		if (tyyppi.equals("dl")) {out.print("selected=selected");}
		out.print(">dl</option>");
		out.println("<option value=\"l\" ");
		if (tyyppi.equals("l")) {out.print("selected=selected");}
		out.print(">l</option>");
		out.println("</select></td>");
		out.println("<td><input type=\"text\" size=\"15\" maxlength=\"99\" value =\"" +rs.getString("ainesosa")+ "\" name=\"ainesosa" +ainelm+ "\" /></td></tr>");	 
		--ainelm;
		}
		}
		}		
		out.println("</table><table>");
		
		stm = con.prepareStatement(ohjeet);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
		
		while(rs.next()) {
	 
		String ohje = rs.getString("ohje");
		String kuvaus = rs.getString("kuvaus");

		out.println("<tr><td><span class=\"paksu\">Ohje</span><br /><textarea cols=\"35\" rows=\"10\" name=\"ohje\">");
		if (ohje != null) {out.print(ohje);}
		out.println("</textarea></td></tr>");
		out.println("<tr><td><span class=\"paksu\">Kuvaus</span><br /><textarea cols=\"35\" rows=\"10\" name=\"kuvaus\">");
		if (kuvaus != null) {out.print(kuvaus);}
		out.println("</textarea></td></tr>");	
		}
		
		out.println("<tr><td><input type=\"submit\" value=\"Tallenna\" /></form>");
		out.println("<form class=\"keskitetty\" method=\"post\" action=\"Etu\"><input type=\"submit\" value=\"Peruuta\" /></form></td></tr></table>");
		
       } catch (SQLException ee) {
             out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
       } finally {
             try {
                if (rs!=null) rs.close(); 
                if (stm!=null) stm.close(); 
                if (pstm!=null) pstm.close();
                Yhteys.closeConnection(con);
             } catch(SQLException e) { 
                out.println("An SQL Exception was thrown."); 
             }
       }  
       out.println("");
       out.println("</div></body></html>");
	}
   } 
}

