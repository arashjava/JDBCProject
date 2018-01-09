package DBConnect;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection
{
	private static java.sql.Connection conn= null;
	
	public static Connection getConnection(){
		try{
			String url="jdbc.oracle:thin:@localhost:1521:xe";
			String username ="test";
			String password = "";
			
			conn =DriverManager.getConnection(url, username, password);

		}
		catch(SQLException e){
			System.out.println("Error! " + e);
		}
		return (Connection) conn;
	}
	
	public static void closeConnection()
	{
		try{
			  if (conn != null) conn.close();
		}
		catch(SQLException e){
			System.out.println("Error in closing : "+ e);
		}
	}

}
