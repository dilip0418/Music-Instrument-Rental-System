package musicalinstrumentrental.user;

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

@WebServlet("/edit")
public class editurl extends HttpServlet {
	private final static String query = "update users set name=?,ph_no=?,gender=?,city=?,status=? where user_id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;margin-top:30px;'> EDITING USERS DATA </h2>");
		// get the values
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String ph_no = req.getParameter("ph_no");
		String gender = req.getParameter("gender");
		String city = req.getParameter("city");
		String status=req.getParameter("status");

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
			ps.setString(1, name);
			ps.setString(2, ph_no);
			ps.setString(3, gender);
			ps.setString(4, city);
			ps.setString(5, status);

			ps.setInt(6, id);
			// execute the query
			int count = ps.executeUpdate();
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:50px'>");
			if (count == 1) {
				pw.println("<h2 class='bg-danger text-light text-center'> USER DATA EDITIED SUCCESSFULLY </h2>");
			} else {
				pw.println("<h2 class='bg-danger text-light text-center'> USER DATA NOT EDITED SUCCESSFULLY </h2>");
			}
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button style='margin-left:97px' class='btn btn-success btn-lg'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showdata'><button style='margin-left:81px' class='btn btn-success btn-lg'>Show User</button></a>");
		pw.println("</div>");
		// close the stram
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
