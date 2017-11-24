import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Poista extends HttpServlet { 

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
      out.println("<a href=\"DrinkkiAlku\">Lis&auml;&auml; drinkki</a><p/>");
		out.println("<p><a href=\"Kayttajat?tunnus=1\">K&auml;ytt&auml;j&auml;t</a><br />");
		out.println("<a href=\"Kayttajat?tunnus=2\">Rekister&ouml;ityneet</a></p>");
		out.println("<p><a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
      	
      out.println("<div class=\"main\">");
		}
		
		String varatutlasit = "select ltunnus from Drinkki where ltunnus=?";
		String lasinpoisto = "delete from Lasi where ltunnus=?";
		String drinkinpoisto1 = "delete from Kooste where dtunnus=?";
		String drinkinpoisto2 = "delete from Drinkki where dtunnus=?";
		String kayttajanpoisto = "delete from Kayttaja where ktunnus=?";
		String varmistus = "select kayttaja, salasana from Kayttaja where ktunnus=?";
		
		int tunnus = Integer.parseInt(req.getParameter("tunnus"));

 	   PreparedStatement stm = null;
   	ResultSet rs = null; 
   	PreparedStatement pstm = null;
   	
		try { 
		
		// Poista drinkki
		if (tunnus == 1){

			int dtunnus = Integer.parseInt(req.getParameter("dtunnus"));

      	pstm = con.prepareStatement(drinkinpoisto1);  
      	pstm.setInt(1,dtunnus);
      	pstm.executeUpdate();

			con.commit();      	
      	
      	pstm = con.prepareStatement(drinkinpoisto2);  
      	pstm.setInt(1,dtunnus);
      	pstm.executeUpdate();

			con.commit();      	

			out.println("<p class=\"keskitetty\">Drinkki poistettiin.</p>"); 	      	
		}	
			
		// Poista lasi
		if (tunnus == 2){
		
			int ltunnus = Integer.parseInt(req.getParameter("ltunnus"));
			
			stm = con.prepareStatement(varatutlasit);  
      	stm.setInt(1,ltunnus);
      	rs = stm.executeQuery();
      
      	while(rs.next()) {
      	
      	int ltunnusapu = rs.getInt("ltunnus");

			if (ltunnusapu == ltunnus){
			
			out.println("<p class=\"keskitetty\">Lasia ei voi poistaa.</p>");
			out.println("</div></body></html>");
			return; 			
			} 
			}
			
      	pstm = con.prepareStatement(lasinpoisto);  
      	pstm.setInt(1,ltunnus);
			pstm.executeUpdate();   	 
			
			con.commit();
			
			out.println("<p class=\"keskitetty\">Lasin poistaminen onnistui.</p>");		
		}

		// Poista kayttaja
		if (tunnus == 3) {
      	
      	int ktunnusapu = Integer.parseInt(req.getParameter("ktunnusapu"));
      	
      	pstm = con.prepareStatement(kayttajanpoisto);  
      	pstm.setInt(1,ktunnusapu);
			pstm.executeUpdate();   	 
			
			con.commit();
			
			out.println("<p class=\"keskitetty\">K&auml;ytt&auml;j&auml; poistettiin.</p>");
		}     
		}  catch (SQLException ee) {
              out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
      }finally {
             try {
                if (rs!=null) rs.close(); 
                if (stm!=null) stm.close();
                if (pstm!=null) pstm.close(); 
                Yhteys.closeConnection(con);
             } catch(SQLException e) { 
                out.println("An SQL Exception was thrown."); 
             }
       } out.println("</div></body></html>");

   } 

}

