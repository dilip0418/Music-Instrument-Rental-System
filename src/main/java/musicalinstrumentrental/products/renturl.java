package musicalinstrumentrental.products;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicalinstrumentrental.ConnectDB;

@WebServlet("/rent")
public class renturl extends HttpServlet {
	private final static String InsertQuery = "insert into rented(product_id,user_id,quantity,return_date)values(?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		// set content type
		res.setContentType("text/html");
		// link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h1 class=\"text-danger\">MUSICAL INSTRUMENTS RENTAL SYSTEM</h1></marquee>");
		pw.println("<h2 class='text-primary' style='margin-left:650px;margin-top:30px;'> RENTING  THE PRODUCTS  </h2>");
		// get the values
		int id = Integer.parseInt(req.getParameter("id"));
		String product_name = req.getParameter("product_name");
		String product_price =req.getParameter("product_price");
		int  product_stock =Integer.parseInt(req.getParameter("product_stock") );
		String user_id = req.getParameter("user_id");
		int  quantity_rented =Integer.parseInt(req.getParameter("quantity_rented") );
		int days = Integer.parseInt(req.getParameter("days"));
		Date date=new Date();
		long time=date.getTime();
		Timestamp ts=new Timestamp(time);
		Calendar cal=Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.DAY_OF_MONTH,days);
		ts=new Timestamp(cal.getTime().getTime());
		
		
//		String return_date = "select current_timestamp+interval " + days + " day as ans";
		
	

		// load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// generate the connection
		try {
			
			if(product_stock != 0 && quantity_rented <= product_stock )
			{
			Connection conn = ConnectDB.connectDB();
			PreparedStatement ps = conn.prepareStatement(InsertQuery);
//			Statement stmt = conn.createStatement();
//	
//			ResultSet rs = stmt.executeQuery(return_date);
//			rs.next();
			ps.setInt(1, id);
			ps.setString(2, user_id);
			ps.setInt(3, quantity_rented);
			
			ps.setTimestamp(4,ts);
			
			
			// execute the query
			int count = ps.executeUpdate();
			if (count == 1) {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCT RENTED SUCCESSFULLY </h2>");
			} else {
				pw.println("<h2 class='bg-danger text-light text-center'> PRODUCT NOT RENTED SUCCESSFULLY </h2>");
			}
			}
			else {
				pw.println("<h2 class='bg-danger text-light text-center'>  THE REQUERED PRODUCT STOCK IS NOT AVALIBALE </h2>");
				
			}
			pw.println("<div class='card' style='margin:auto;width:300px;margin-top:50px'>");
			
		} catch (SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='newproduct.html'><button class='btn btn-success btn-lg'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='showproduct'><button class='btn btn-success btn-lg'>Show User</button></a>");
		pw.println("</div>");
		// close the stram
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
