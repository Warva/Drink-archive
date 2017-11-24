import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Yhteys extends HttpServlet { 

// to use Oracle 9 in bodbacka:
   final static String dbDriver="oracle.jdbc.OracleDriver";
   final static String dbServer= 
"jdbc:oracle:thin:@bodbacka.cs.helsinki.fi:1521:test";
   // nothing to replace
   // classpath must contain /opt/jdbc/oracle/classes12.zip

   final static String dbUser= "warva";        // replace with your db user account
   final static String dbPassword ="hdkr6a"; // replace with your password 
   
public static Connection createDbConnection() throws IOException {

 // establish a database connection
    try{ 
        Class.forName(dbDriver);               // load driver
    } catch (ClassNotFoundException e) { 
          System.out.println("<p class=\"sisennys\">Couldn't find driver "+dbDriver+ "</p>");
          return null; 
    }
    Connection con=null;
    try {
       con = DriverManager.getConnection(dbServer,dbUser,dbPassword); 
    } catch (SQLException se) {
          System.out.println("<p class=\"sisennys\">Couldn\'t get connection to "+dbServer+ " for "+ 
dbUser+"<br>");
          System.out.println(se.getMessage()+ "</p>");          
    }
    return con;
}

public static void closeConnection(Connection con) {
   // close database connection
   try {
       con.close();
   }catch (SQLException e) {}
}

}

