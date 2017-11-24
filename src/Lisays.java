import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lisays extends HttpServlet { 

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
	
	else if (ktunnus != 0 && taso == 1) {
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
		
		out.println("<div class=\"main\">");
		
		String uusirekis = "insert into Kayttaja (ktunnus, taso, kayttaja, salasana, knimi, maili) values "+
		"((select max(ktunnus) from Kayttaja) + 1, 3, ?, ?, ?, ?)";
		String uusilasi = "insert into Lasi (ltunnus, lnimi) values ((select max(ltunnus) from Lasi) + 1, ?)";
   	String uusidrinkki = "insert into Drinkki (dtunnus, dnimi, ajankohta, ltunnus, makeus, lampotila, valmistus, latunnus, ohje, kuvaus, ehdotus, ainelm) "+
   	"values ((select max(dtunnus) from Drinkki) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String uusidrinkki2 = "insert into Kooste (dtunnus, maara, tyyppi, ainesosa) values ((select max(dtunnus) from Drinkki), ?, ?, ?)";
		String nimivertailu = "select kayttaja from Kayttaja where kayttaja=?";

		int tunnus = Integer.parseInt(req.getParameter("tunnus"));
 	   PreparedStatement stm = null;
   	ResultSet rs = null; 
   	PreparedStatement pstm = null;
   	
	try { 
	
		// Rekisteroidyn lisays
		if (tunnus == 5){
		
			String kayttajau = req.getParameter("kayttajau");
			String salasanau = req.getParameter("salasanau");
			String knimiu = req.getParameter("knimiu");
			String mailiu = req.getParameter("mailiu");
			
			stm = con.prepareStatement(nimivertailu);  
      	stm.setString(1,kayttajau);
			rs = stm.executeQuery();
			
			while(rs.next()) {
			
			String kayttaja = rs.getString("kayttaja");			
			
			if (kayttajau.equals(kayttaja)) {
			out.println("<p class=\"keskitetty\">Tunnus on jo k&auml;yt&ouml;ss&auml;.</p></div></body></html>");
			return; 
			}						
			}
			
			if (kayttajau.equals("") != true && salasanau.equals("") != true && knimiu.equals("") != true && mailiu.equals("") != true && salasanau.length() >= 5) {
			
			pstm = con.prepareStatement(uusirekis);  
      	pstm.setString(1,kayttajau);
      	pstm.setString(2,salasanau);
      	pstm.setString(3,knimiu);
      	pstm.setString(4,mailiu);
			pstm.executeUpdate();
			
			con.commit();
			
			out.println("<p class=\"keskitetty\">Rekister&ouml;ityminen onnistui. Saat s&auml;hk&ouml;postia, kun tunnuksesi on hyv&auml;ksytty.</p>");
			
			}else {out.println("<p class=\"keskitetty\">T&auml;yt&auml; jokainen kohta.</p>");}
		}

	
   	// Lasin lisays
		if (tunnus == 2){
			String lnimi = req.getParameter("lnimi");

		if (lnimi.equals("") != true){
      	pstm = con.prepareStatement(uusilasi);  
      	pstm.setString(1,lnimi);
			pstm.executeUpdate();  
			 	 
			con.commit();
		
		out.println("<p class=\"keskitetty\">Lasin lis&auml;&auml;minen onnistui.</p>");
		
		}
		else {out.println("<p class=\"keskitetty\">Anna lasia kuvaava nimi.</p>");}
	}

      // Drinkin lisays ja ehdottaminen
		if (tunnus == 1){
		
		String dnimi = req.getParameter("dnimi");
		String ajankohta = req.getParameter("ajankohta");
		String makeus = req.getParameter("makeus");
		String lampotila = req.getParameter("lampotila");
		String valmistus = req.getParameter("valmistus");
		String ohje = req.getParameter("ohje");
		String kuvaus = req.getParameter("kuvaus");
		int ltunnus = Integer.parseInt(req.getParameter("ltunnus"));
		int latunnus = Integer.parseInt(req.getParameter("latunnus"));
		int ainelm = Integer.parseInt(req.getParameter("ainelm"));
				
		if (ktunnus != 0) {
		
 		if (dnimi.equals("") != true && latunnus != 0 && ltunnus != 0) {
	
		pstm = con.prepareStatement(uusidrinkki);  
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
		pstm.setInt(11,ainelm);
		pstm.executeUpdate();
		
		con.commit();
		
		while (ainelm > 0){
		int maara = Integer.parseInt(req.getParameter("maara"+ainelm));
		String tyyppi = req.getParameter("tyyppi"+ainelm);
		String ainesosa = req.getParameter("ainesosa"+ainelm);

		pstm = con.prepareStatement(uusidrinkki2);  
      pstm.setInt(1,maara);
     	pstm.setString(2,tyyppi);
      pstm.setString(3,ainesosa);
		pstm.executeUpdate();
		
		con.commit();
	
		--ainelm;		
	
	} out.println("<p class=\"keskitetty\">Drinkki on tallennettu tietokantaan.</p>");
	// Drinkki lisättiin
	} else {out.println("<p class=\"keskitetty\">T&auml;hdell&auml; merkityt kent&auml;t ovat pakollisia.</p>");}

		}else {
		
 		if (dnimi.equals("") != true && latunnus != 0 && ltunnus != 0) {
	
		pstm = con.prepareStatement(uusidrinkki);  
      pstm.setString(1,dnimi);
      pstm.setString(2,ajankohta);
      pstm.setInt(3,ltunnus);
      pstm.setString(4,makeus);
      pstm.setString(5,lampotila);
		pstm.setString(6,valmistus);
		pstm.setInt(7,latunnus);
		pstm.setString(8,ohje);
		pstm.setString(9,kuvaus);
		pstm.setInt(10,2);
		pstm.executeUpdate();
		
		con.commit();
		
		while (ainelm > 0){
		int maara = Integer.parseInt(req.getParameter("maara"+ainelm));
		String tyyppi = req.getParameter("tyyppi"+ainelm);
		String ainesosa = req.getParameter("ainesosa"+ainelm);

		pstm = con.prepareStatement(uusidrinkki2);  
      pstm.setInt(1,maara);
     	pstm.setString(2,tyyppi);
      pstm.setString(3,ainesosa);
		pstm.executeUpdate();
		
		con.commit();
	
	--ainelm;
	// Drinkin ehdotus lisättiin 
	} out.println("<p class=\"keskitetty\">Drinkki on tallennettu tietokantaan.</p>");
	} else {out.println("<p class=\"keskitetty\">T&auml;hdell&auml; merkityt kent&auml;t ovat pakollisia.</p>");}
  }
  }
 
 }catch (SQLException ee) {
              out.println("<p class=\"sisennys\">Tietokantavirhe "+ee.getMessage()+"</p>");
       }  finally {
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

