package musicalinstrumentrental;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	static Connection connection = null;
	public static Connection connectDB() {

		try {
			DBSetup.createDB();
			
			String url = "jdbc:mysql://127.0.0.1:3306/musical_instrumental_db";
			String user = "root";
			String password = "#3BR19cs044";
			
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to DB succsessfully");
			DBSetup.createTables(connection);
			System.out.println("Created tables succsessfully");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println("We successfully connected and disconnected the DB");
		return connection;
	}
	public void connectionClose(Connection conn) throws SQLException {
		conn.close();
	}
}