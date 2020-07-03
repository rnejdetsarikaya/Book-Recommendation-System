package DaoImpl;
import java.sql.*;

public class mysql {

	public Connection getConnection() throws Exception{
		  try{
		   String driver = "com.mysql.jdbc.Driver";
		   String url = "jdbc:mysql://localhost/test1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		   String username = "root";
		   String password = "asdlis123";
		   Class.forName(driver);
		   
		   Connection conn = DriverManager.getConnection(url,username,password);
		   System.out.println("Connect Success");
		   return conn;
		  } catch(Exception e){
			  System.out.println("Connecct Fail");
		  }
		  
		  
		  return null;
		 }
	
	

}
