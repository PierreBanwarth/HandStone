
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnexion {

	private Connection connexion;
	private String DBUser;
	private String DBPassword;
	private String DBUrl;
	
	public DBConnexion(String user, String password, String url) {
		this.DBUser = user;
		this.DBPassword = password;
		this.DBUrl = url;
		
		this.connexion = null;
	}
	
	public boolean connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
			connexion = DriverManager.getConnection( DBUrl, DBUser, DBPassword );
			return true;
		} catch (Exception e) {
			System.out.println("An Error occured while connecting to the Data Base.");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modify(String query) {
		if (connexion != null) {
			try {
				Statement statement = connexion.createStatement();
				int statut = statement.executeUpdate(query);
				return true;
			} catch (SQLException e) {
				System.out.println("An Error occured while attempting to modify the Data Base.");
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Data Base not connected");
			return false;
		}
	}

	public ResultSet read(String query) {
		if (connexion != null) {
			try {
				Statement statement = connexion.createStatement();
				ResultSet result = statement.executeQuery(query);
				return result;
			} catch (SQLException e) {
				System.out.println("An Error occured while attempting to modify the Data Base.");
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("Data Base not connected");
			return null;
		}
	}
	
	public boolean isConnected() {
		
		return (connexion != null);
	}
	

}
