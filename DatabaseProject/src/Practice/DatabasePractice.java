package Practice;

//import java.sql.Connection; // to use java sql or ms access database
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.*; // allows to use all sql functions
import java.util.*;

public class DatabasePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			// to load the driver
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("problem in loading the driver");
		}
		
		// this is to establish the connection to the database
		try {
			Scanner sc = new Scanner(System.in);
			String dbName = "Employee.accdb";
			String dbURL = "jdbc:ucanaccess://" + dbName;
			conn = DriverManager.getConnection(dbURL);
			String n = "John";
			double sa = 156000;
			stat = conn.createStatement();
			String query = "INSERT INTO EMP (Ename,Salary)" + "values ('"+n+"', "+sa+")";
			stat.executeUpdate(query);
			
			//updates salary in the specific name provided
			query = "UPDATE EMP SET Salary = 120000 " + "where EName = 'ABC'";
			stat.executeUpdate(query);
			
			query = "DELETE FROM EMP where EName = 'Cyrus'";
			stat.executeUpdate(query);
					
			rs = stat.executeQuery("SELECT * FROM Emp");
			int id;
			String name;
			double sal;
			while(rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				sal = rs.getDouble(3);
				System.out.println("ID " + id + " Name " + name + " Salary " + sal);
			}
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		// this is to close connection
		finally {
			try {
				if (conn != null) {
					rs.close();
					stat.close();
					conn.close();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}

}
