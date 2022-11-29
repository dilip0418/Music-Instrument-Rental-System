package musicalinstrumentrental.user;
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

@WebServlet("/editurl")
public class editinguser extends HttpServlet {
	private final static String query = "select name,ph_no,gender,city,status from users where user_id=?";
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
		pw.println("<h2 class='text-primary' style='margin-left:650px;margin-top:30px;'> EDITING USERS DATA </h2>");
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
			pw.println("<form action='edit?id="+id+"' method='post'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Phone Number</td>");
			pw.println("<td><input type='text' name='ph_no' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");

			pw.println("<td>Gender</td>");
			pw.println("<td><input type='text' name='gender' value='"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<td>City</td>");
			pw.println("<td><input type='text' name='city' value='"+rs.getString(4)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<td>Status</td>");
			pw.println("<td><input type='text' name='status' value='"+rs.getString(5)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<tr>");
			pw.println("<td><button type='submit' class='btn btn-success btn-lg'>EDIT</button></td>");
			pw.println("<td><button type='reset' class='btn btn-danger btn-lg'>CANCEL</button></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button style='margin-left:200px;' class='btn btn-primary btn-lg'> BACK </button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}