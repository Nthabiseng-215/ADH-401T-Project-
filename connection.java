package tweet;
import java.sql.*;

import com.mysql.jdbc.Statement;

public class connection {
	
	public Connection connect() throws SQLException, ClassNotFoundException
	{
		Connection conn = null;
		
		Class.forName("com.jdbc.mysql.Driver");
		
        conn = DriverManager.getConnection("jdbc:mysql://localhost/ADH401TDS","",""); 
		
		return conn;
	}

}
