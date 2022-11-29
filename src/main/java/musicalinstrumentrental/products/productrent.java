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

@WebServlet("/renturlp")
public class productrent extends HttpServlet {
	private final static String query = "select product_name,price,stock from products where product_id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html");
		
		//get the id
		//get the values
		int id = Integer.parseInt(req.getParameter("id"));
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;margin-top:30px;'> RENTING THE PRODUCTS </h2>");
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//generate the connection
		try {
			Connection conn = ConnectDB.connectDB();
				PreparedStatement ps = conn.prepareStatement(query);
			//set value 
			ps.setInt(1, id);
			//resultSet
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<body style='background-color:white; text-color:white;'>");
			pw.println("<div style='margin:auto;width:500px;margin-top:50px;'>");
			pw.println("<form action='rent?id="+id+"' method='post'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='product_name' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Price</td>");
			pw.println("<td><input type='text' name='product_price' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");

			pw.println("<td>Stock</td>");
			pw.println("<td><input type='text' name='product_stock' value='"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<td>ID</td>");
			pw.println("<td><input type='text' name='user_id' value=''></td>");
			pw.println("</tr>");
			
			pw.println("<td>Quantity</td>");
			pw.println("<td><input type='number' step='1' min='1' name='quantity_rented' value=''></td>");
			pw.println("</tr>");
			
			pw.println("<td>Days</td>");
			pw.println("<td><input type='number' step='1' min='1' name='days' value=''></td>");
			pw.println("</tr>");
			
			
			pw.println("<tr>");
			pw.println("<tr>");
			pw.println("<td><button type='submit' class='btn btn-success btn-lg'> RENT </button></td>");
			pw.println("<td><button type='reset' class='btn btn-danger btn-lg'> CANCEL </button></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='newproduct.html'><button style='margin-left:200px;' class='btn btn-primary btn-lg'> BACK </button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}