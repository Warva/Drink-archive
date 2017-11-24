import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HaeDrinkki extends HttpServlet { 

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
 	}

		String haku1 = "select dnimi, dtunnus from Drinkki where valmistus=? "+
		"and ehdotus = 1 order by dnimi";
		String haku2 = "select dnimi, dtunnus from Drinkki where latunnus=? "+
		"and ehdotus = 1 order by dnimi";
		String haku3 = "select dnimi, dtunnus from Drinkki where ehdotus = 1 order by dnimi";		
		String haku4 = "select Drinkki.dtunnus, Drinkki.dnimi, Kooste.dtunnus, Kooste.ainesosa from Kooste, Drinkki "+
		"where Drinkki.ehdotus = 1 and Kooste.dtunnus = Drinkki.dtunnus order by Kooste.ainesosa";		

       String valmistus = req.getParameter("valmistus");
       String sana = req.getParameter("sana");
       int latunnus = Integer.parseInt(req.getParameter("latunnus"));
       
       PreparedStatement stm = null;
       ResultSet rs = null; 
       Statement pstm= null;


	out.println("<div class=\"main\"><h1>Hakutulokset</h1>");
	
	try {
	
	 if (valmistus.equals("valitse") != true) {
		stm = con.prepareStatement(haku1);
		stm.setString(1,valmistus);
		rs = stm.executeQuery();
		while(rs.next()) { 
      out.println("<a class=\"keskitetty\" href=\"Juoma?dtunnus=" +rs.getInt("dtunnus")+ "\">" +rs.getString("dnimi")+"</a><br />");
      }
     }
	
	 else if (latunnus != 0) {
		stm = con.prepareStatement(haku2);
		stm.setInt(1,latunnus);
		rs = stm.executeQuery();
		while(rs.next()) { 
      out.println("<a class=\"keskitetty\" href=\"Juoma?dtunnus=" +rs.getInt("dtunnus")+ "\">" +rs.getString("dnimi")+"</a><br />");
      }
     }

	 else if (sana.equals("") != true) {
		pstm = con.createStatement();		
		rs = pstm.executeQuery(haku3);
				
		while(rs.next()) { 
		
		String dnimi = rs.getString("dnimi");
		
		if (sana.equalsIgnoreCase(dnimi)){
      out.println("<a class=\"keskitetty\" href=\"Juoma?dtunnus=" +rs.getInt("dtunnus")+ "\">" +dnimi+"</a><br />");
      }
      }

		pstm = con.createStatement();		
		rs = pstm.executeQuery(haku4);
		
		while(rs.next()) {
		 
		String ainesosa = rs.getString("ainesosa");
		
		if (sana.equalsIgnoreCase(ainesosa)){
      out.println("<a class=\"keskitetty\" href=\"Juoma?dtunnus=" +rs.getInt("dtunnus")+ "\">" +rs.getString("dnimi")+"</a><br />");
      }
      }
     
     }else {out.println("<p class=\"keskitetty\">Hakukriteereit&auml; ei ollut.</p>");}
     
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
	out.println("</div></body></html>");

}
}
