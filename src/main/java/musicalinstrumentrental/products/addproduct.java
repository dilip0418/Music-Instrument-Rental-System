package musicalinstrumentrental.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicalinstrumentrental.ConnectDB;

@WebServlet("/newproduct")
public class addproduct extends HttpServlet {

	private final static String InsertQuery = "insert into products(product_name,price,stock)values(?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get print writer
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// get the values
		

		String product_name = req.getParameter("product_name");
		String product_price = req.getParameter("product_price");
		String product_stock = req.getParameter("product_stock");
		

		// load the jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Connection conn = ConnectDB.connectDB();
			PreparedStatement ps = conn.prepareStatement(InsertQuery);
		

			ps.setString(1, product_name);
			ps.setString(2, product_price);
			ps.setString(3, product_stock);
			
			boolean count = ps.execute();
			if (count = true) {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCT ADDED SUCCESSFULLY </h2>");
			} else {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCT NOT ADD  SUCCESSFULLY </h2>");

			}
		
		} catch (SQLException e) {
			pw.println("<h2>" + e.getMessage() + "</h2");

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		pw.println("<a href='home.html'><button>Home</button></a>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}
}
