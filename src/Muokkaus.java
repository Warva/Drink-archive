import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Muokkaus extends HttpServlet { 

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
 	}
	if (ktunnus != 0 && taso == 2){
		out.println("<p><a href=\"Etu\">Etusivu</a><br />");
		out.println("<a href=\"Haku\">Haku</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=3\">Drinkit</a><br />");
      out.println("<a href=\"Juomat?tunnus=2\">Boolit</a><br />");
      out.println("<a href=\"Juomat?tunnus=1\">Alkoholittomat</a></p>");
      out.println("<p><a href=\"Juomat?tunnus=4\">Ehdotetut drinkit</a><br />");
		out.println("<a href=\"LisaysDrinkkiAlku\">Lis&auml;&auml; drinkki</a><br />");
		out.println("<a href=\"Kayttaja\">K&auml;ytt&auml;j&auml;tiedot</a><br />");
		out.println("<a href=\"Poistu\">Kirjaudu ulos</a></p></div>");
	}

	out.println("<div class=\"main\">");

	String uusisalasana = "update Kayttaja set salasana=? where ktunnus=?";
	String uusitaso = "update Kayttaja set taso=? where ktunnus=?";
	String ehdotus = "update Drinkki set ehdotus=? where dtunnus=?";
	String aines = "select ainelm from Drinkki where dtunnus=?";
	String drinkkimuutos1 = "update Drinkki set dnimi=?, ajankohta=?, ltunnus=?, makeus=?, lampotila=?, valmistus=?, latunnus=?, "+
	"ohje=?, kuvaus=?, ehdotus=? where dtunnus=?";
	String drinkkimuutos2 = "update Kooste set maara=?, tyyppi=?, ainesosa=? where dtunnus=?";

	int tunnus = Integer.parseInt(req.getParameter("tunnus"));

 	PreparedStatement stm = null;
  	ResultSet rs = null; 
  	PreparedStatement pstm = null;

	try { 
	
	// salasanan vaihto
	if (tunnus == 1){
		String usalasana = req.getParameter("usalasana");
		String uusalasana = req.getParameter("uusalasana");

		if (usalasana.equals(uusalasana) && usalasana.length() >= 5 && uusalasana.length() >= 5){
      
      pstm = con.prepareStatement(uusisalasana); 
      pstm.setString(1,usalasana);
      pstm.setInt(2,ktunnus);
		pstm.executeUpdate(); 
		  
		con.commit();
	
		out.println("<p class=\"keskitetty\">Salasanan vaihto onnistui.</p>");
		}else {out.println("<p class=\"keskitetty\">Salasanan vaihtaminen ep&auml;onnistui.</p>");}
	}
	
	// Kayttajan ja rekisteroityneen tason vaihtaminen
	if (tunnus == 2) {
	
		int tasou = Integer.parseInt(req.getParameter("tasou"));
		int ktunnusapu = Integer.parseInt(req.getParameter("ktunnusapu"));
		
      pstm = con.prepareStatement(uusitaso); 
      pstm.setInt(1,tasou);
   	pstm.setInt(2,ktunnusapu);
		pstm.executeUpdate();   	 

		con.commit();
		
		out.println("<p class=\"keskitetty\">Tason vaihto onnistui.</p>");
	}
	
	// Ehdotuksen hyvaksynta
	if (tunnus == 3) {
	
		int dtunnus = Integer.parseInt(req.getParameter("dtunnus"));
		
      pstm = con.prepareStatement(ehdotus); 
      pstm.setInt(1,1);
      pstm.setInt(2,dtunnus);
		pstm.executeUpdate(); 
		
		con.commit();
		
		out.println("<p class=\"keskitetty\">Ehdotus hyv&auml;ksyttiin.</p>");
	 
	}

	// Muuta drinkkiä
	if (tunnus == 4) {
	
		int dtunnus = Integer.parseInt(req.getParameter("dtunnus"));
		String dnimi = req.getParameter("dnimi");
		String ajankohta = req.getParameter("ajankohta");
		String makeus = req.getParameter("makeus");
		String lampotila = req.getParameter("lampotila");
		String valmistus = req.getParameter("valmistus");
		String ohje = req.getParameter("ohje");
		String kuvaus = req.getParameter("kuvaus");
		int ltunnus = Integer.parseInt(req.getParameter("ltunnus"));
		int latunnus = Integer.parseInt(req.getParameter("latunnus"));
		
		if (dnimi.equals("") != true && latunnus != 0 && ltunnus != 0) {
		
		pstm = con.prepareStatement(drinkkimuutos1); 
      pstm.setString(1,dnimi);
      pstm.setString(2,ajankohta);
      pstm.setInt(3,ltunnus);
      pstm.setString(4,makeus);
      pstm.setString(5,lampotila);
      pstm.setString(6,valmistus);
      pstm.setInt(7,latunnus);
      pstm.setString(8,ohje);
      pstm.setString(9,kuvaus);
      pstm.setInt(10,1);
      pstm.setInt(11,dtunnus);
		pstm.executeUpdate(); 
		
		con.commit();

		stm = con.prepareStatement(aines);  
      stm.setInt(1,dtunnus);
		rs = stm.executeQuery(); 
		
      while(rs.next()) { 
		
		int ainelm = rs.getInt("ainelm");

		while (ainelm > 0){
		int maara = Integer.parseInt(req.getParameter("maara"+ainelm));
		String tyyppi = req.getParameter("tyyppi"+ainelm);
		String ainesosa = req.getParameter("ainesosa"+ainelm);
			
		--ainelm;
		
		}
		
		out.println("<p class=\"keskitetty\">Muutos onnistui.</p>"); 
		// Drinkki lisättiin
		}
		} else {out.println("<p class=\"keskitetty\">Muutos ep&auml;onnistui.</p>");}
	 
	}

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

