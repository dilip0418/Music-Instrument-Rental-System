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

@WebServlet("/showdata")
public class userlist extends HttpServlet {
	private final static String query = "select user_id,name,ph_no,gender,city,status from users";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;'> LIST OF USERS </h2>");
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
			pw.println("<tr >");
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Phone Number</th>");
			pw.println("<th>Gender</th>");

			pw.println("<th>City</th>");
			pw.println("<th>Status</th>");

			pw.println("<th>Edit</th>");
			pw.println("<th>Delete</th>");
			pw.println("</tr>");
			while (rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" + rs.getInt(1) + "</td>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				pw.println("<td>" + rs.getString(4) + "</td>");
				pw.println("<td>" + rs.getString(5) + "</td>");
				pw.println("<td>" + rs.getString(6) + "</td>");

				pw.println("<td><a href='editurl?id=" + rs.getInt(1) + "'>EDIT</a></td>");
				pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>DELETE</a></td>");
				
				pw.println("</tr>");
			}
			pw.println("<a href='home.html'><button class=\"btn btn-primary btn-lg\" style='margin-bottom:20px;margin-left:400px;'>Home</button></a>");
			
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
