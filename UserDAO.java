package vnua.fita.model;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vnua.fita.bean.User;
import java.sql.Statement;

public class UserDAO {
	public static boolean updateUser(User user) throws SQLException {
		String sql = "UPDATE tbluser SET fullname = ?";
		sql += "WHERE email = ?";
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String dbUser = "";
		String dbPass = "";
		
		Connection jdbcConnection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getFullname());
        statement.setString(2, user.getEmail());
       
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        jdbcConnection.close();
        return rowUpdated;
	}
	
	public static List<User> listAllUsers() throws SQLException {
		List<User> listUser = new ArrayList<>();
	
		String sql = "SELECT * FROM tbluser";
		
		String jdbcURL = "jdbc:ucanaccess://lib/QLNS.accdb";
		String dbUser = "";
		String dbPass = "";
		
		Connection jdbcConnection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String email = resultSet.getString("email");
			String fullname = resultSet.getString("fullname");
			
			User user = new User(email, fullname);
			listUser.add(user);
		}
		
		resultSet.close();
		statement.close();
		
		jdbcConnection.close();
		
		return listUser;
	}
	
	public static void main(String[] args) throws SQLException {
		
		//Tạo đối tượng User
		User updateUser = new User("nvt@gmail.com", "Nguyễn Văn Thắng");
		boolean updateResult = UserDAO.updateUser(updateUser);
		System.out.println(updateResult);
		
		List<User> listUser = UserDAO.listAllUsers();
		for(User user: listUser) {
			System.out.println(user.getEmail() + "-" + user.getFullname());
		}
	}
}
