package Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.DatabaseConnection;
import Model.User;

public class UserService {

	private DatabaseConnection dbCon = new DatabaseConnection();
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public UserService() {
		try {
			dbCon.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() throws SQLException {
		List<User> list = new ArrayList<User>();
		statement = dbCon.getCon().createStatement();
		resultSet = statement.executeQuery("select * from user");
		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String phone = resultSet.getString("phone");
			String address = resultSet.getString("address");
			list.add(new User(username, name, email, Long.parseLong(phone), address));
		}

		return list;
	}

	public User getUserByUsername(String username) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement("select * from user where username=?");
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		User user = null;
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String phone = resultSet.getString("phone");
			String address = resultSet.getString("address");
			user = new User(username, name, email, Long.parseLong(phone), address);
		}
		return user;

	}

	public User createUser(User user) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement("insert into  user values ( ?, ?, ?, ? , ?)");
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setLong(4, user.getPhone());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.executeUpdate();
		return user;
	}

	public User updateUser(String username, User user) throws SQLException {
		preparedStatement = dbCon.getCon()
				.prepareStatement("update user set username=?, name=?, email=?, phone=?, address=?  where username=?;");
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setLong(4, user.getPhone());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.setString(6, username);
		preparedStatement.executeUpdate();
		return user;
	}

	public String deleteUser(String username) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement("delete from user where username=?");
		preparedStatement.setString(1, username);
		preparedStatement.executeUpdate();
		return username;
	}

	public DatabaseConnection getDbCon() {
		return dbCon;
	}

	public void setDbCon(DatabaseConnection dbCon) {
		this.dbCon = dbCon;
	}

}
