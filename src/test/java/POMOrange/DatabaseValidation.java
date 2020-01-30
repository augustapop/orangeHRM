package POMOrange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import orangeHRM.orangeHRM.ReadConfigOrange;

public class DatabaseValidation {

    private String dbUrl = "jdbc:sqlserver://conshealth02.database.windows.net:1433;DatabaseName=conshealth02";
    private String dbUsername = "conshealth02@conshealth02.database.windows.net";
    private String dbPassword = "Grants\\Sql";
    static ReadConfigOrange props;

    public Connection createConnection() throws Exception {
	Connection con = null;
	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	    if (con == null)
		System.out.print("connection is null");
	} catch (SQLException e) {
	    e.printStackTrace();
	    System.out.print("exception:  " + e);
	}
	return con;
    }

    public int selectUserIdByUserName(String username) throws Exception {
	ResultSet rs = null;
	String statement = "select UserName, UserId from [dbo].[User] where UserName = '" + username + "'";
	String userNameValue = null;
	int userIdValue = 0;

	try (Connection con = createConnection()) {
	    System.out.println("database execute statement: " + statement);
	    try (Statement stmt = con.createStatement()) {
		rs = stmt.executeQuery(statement);
		while (rs.next()) {
		    userNameValue = rs.getString("UserName");
		    userIdValue = rs.getInt("UserId");
		    System.out.println("UserName is: " + userNameValue + "; UserId is: " + userIdValue);
		}
	    }
	    return userIdValue;
	}
    }

    public void deleteUserByUserName(String username) throws Exception {
	int userId = selectUserIdByUserName(username);
	try (Connection con = createConnection()) {
	    try {
		con.setAutoCommit(false); //commit transaction manually at the end
		
		PreparedStatement deleteStm1 = con.prepareStatement("delete from UserRoleRel where userId = ?");
		deleteStm1.setInt(1, userId);
		deleteStm1.executeUpdate();

		PreparedStatement deleteStm2 = con.prepareStatement("delete From AppointmentItem where AppointmentId in (select AppointmentId From Appointment where PatientId in (select PatientId from patient where userId = ? ))");
		deleteStm2.setInt(1, userId);
		deleteStm2.executeUpdate();
		
		PreparedStatement deleteStm3 = con.prepareStatement("delete From Appointment where PatientId in (select PatientId from patient where userId = ?)");
		deleteStm3.setInt(1, userId);
		deleteStm3.executeUpdate();

		PreparedStatement deleteStm4 = con.prepareStatement("delete From DoctorPatientRel where patientid in (select PatientId from patient where userId = ?)");
		deleteStm4.setInt(1, userId);
		deleteStm4.executeUpdate();
		
		PreparedStatement deleteStm5 = con.prepareStatement("delete from patient where userId = ?");
		deleteStm5.setInt(1, userId);
		deleteStm5.executeUpdate();

		PreparedStatement deleteStm6 = con.prepareStatement("delete from Doctor where userId = ?");
		deleteStm6.setInt(1, userId);
		deleteStm6.executeUpdate();

		PreparedStatement deleteStm7 = con.prepareStatement("delete From Notification where userId = ?");
		deleteStm7.setInt(1, userId);
		deleteStm7.executeUpdate();

		PreparedStatement deleteStm8 = con.prepareStatement("delete from [user] where userId = ?");
		deleteStm8.setInt(1, userId);
		deleteStm8.executeUpdate();
		
		con.commit();
	    } catch (SQLException e) {
		e.printStackTrace();
		System.out.print("exception:  " + e);
	    }
	    
	}
    }

}
