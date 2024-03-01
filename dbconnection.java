import java.sql.DriverManager;
import java.sql.*;

public class dbconnection {
	private static final String url="jdbc:mysql://localhost:3306/supermarket";
	private static final String username="root";
	private static final String password="Girim2611devi$";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
}
