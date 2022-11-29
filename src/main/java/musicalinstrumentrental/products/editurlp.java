package musicalinstrumentrental.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicalinstrumentrental.ConnectDB;

@WebServlet("/editp")
public class editurlp extends HttpServlet {
	private final static String query = "update products set product_name=?,price=?,stock=? where product_id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;margin-top:30px;'> EDITING PRODUCTS DATA </h2>");
		// get the values
		int id = Integer.parseInt(req.getParameter("id"));
		String product_name = req.getParameter("product_name");
		String product_price = req.getParameter("product_price");
		String product_stock = req.getParameter("product_stock");
	

		// load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// generate the connection
		try {
			Connection conn = ConnectDB.connectDB();
			PreparedStatement ps = conn.prepareStatement(query);
		
			// set the values
			ps.setString(1, product_name);
			ps.setString(2, product_price);
			ps.setString(3, product_stock);
			ps.setInt(4, id);
			// execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:50px'>");
			if (count == 1) {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCTS DATA EDITIED SUCCESSFULLY </h2>");
			} else {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCTS DATA NOT EDITED SUCCESSFULLY </h2>");
			}
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='newproduct.html'><button style='margin-left:97px' class='btn btn-success btn-lg'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showproduct'><button style='margin-left:81px' class='btn btn-success btn-lg'>Show User</button></a>");
		pw.println("</div>");
		// close the stram
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
