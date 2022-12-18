package musicalinstrumentrental;
import java.sql.*;


public class DBSetup {
	
	final static String user = " create table if not exists users ( user_id int primary key auto_increment, name varchar(50), ph_no varchar(50), gender varchar(6), city varchar(50), status varchar(10) default 'active' )auto_increment = 101";
	final static String products = "create table if not exists products(product_id int primary key auto_increment,"
			+ "product_name varchar(50) not null,"
			+ "price float default 0,"
			+ "stock int default 0,"
			+ "rented int default 0)";
	final static String Rented = "CREATE TABLE IF NOT EXISTS rented(RENT_ID INT PRIMARY KEY AUTO_INCREMENT,PRODuct_ID INT,USER_ID INT, AMOUNT FLOAT default 0, QUANTITY INT, RENTED_DATE TIMESTAMP default(current_timestamp), RETURN_DATE TIMESTAMP, STATUS VARCHAR(10) default 'delivered', FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(PRODUCT_ID), FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID))AUTO_INCREMENT = 1001";
	final static String Club_collection = "create table if not exists club_collection(collection_date date primary key default (current_date), amount float DEFAULT 0, products_rented int DEFAULT 0)";

		public static void createDB() {
			try {
				String url = "jdbc:mysql://127.0.0.1:3306/";
				String user = "user_name";
				String password = "password";
				Connection con = DriverManager.getConnection(url, user, password);
				//Creating the Statement
				Statement stmt = con.createStatement();
				//Query to create a database
				String query = "CREATE database if not exists musical_instrumental_db";
				//Executing the query
				stmt.execute(query);
				System.out.println("Database created");
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	
	public static void createTables(Connection conn) {
		try {
			Statement st = conn.createStatement();
			st.execute(user);
			st.execute(products);
			st.execute(Rented);
			st.execute(Club_collection);
			System.out.println("Succesfully Crated all Tables Required");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
