package musicalinstrumentrental.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicalinstrumentrental.ConnectDB;

@WebServlet("/showproduct")
public class productlist extends HttpServlet {
	private final static String query = "select product_id,product_name,price,stock,rented from products";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;'> LIST OF PRODUCTS </h2>");
		
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
		
			// resultSet
			ResultSet rs = ps.executeQuery();
			pw.println("<body style='background-color:white; text-color:white;'>");
			pw.println("<div style='margin:auto;width:900px;margin-top:10px;;'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Price</th>");
			pw.println("<th>Stock</th>");
			pw.println("<th>Rented</th>");

			

			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while (rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getString(4) + "</td>");
				pw.println("<td>" + rs.getInt(5) + "</td>");
				

				pw.println("<td><a href='editurlp?id=" + rs.getInt(1) + "'>Edit</a></td>");
				pw.println("<td><a href='renturlp?id=" + rs.getInt(1) + "'>Rent</a></td>");
				
				pw.println("</tr>");
			}
			pw.println("<a href='newproduct.html'><button class=\"btn btn-primary btn-lg\" style='margin-bottom:20px;margin-left:400px;'>Home</button></a>");
			
			pw.println("</table>");
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//pw.println("<button class='btn btn-outline-success d-block'><a href='home.html'>Home</a></button>");
		pw.println("</div>");
		// close the stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
