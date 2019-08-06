package Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CreditCard;
import Model.DatabaseConnection;

public class CreditCardService {

	private DatabaseConnection dbCon = new DatabaseConnection();
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public CreditCardService() {
		try {
			dbCon.connectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<CreditCard> getUserCreditCards(String username) throws SQLException {
		List<CreditCard> list = new ArrayList<>();
		preparedStatement = dbCon.getCon().prepareStatement("select * from creditcard where User_username=?");
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String number = resultSet.getString("number");
			String name = resultSet.getString("name");
			String expirationDate = resultSet.getString("expirationDate");
			int securityCode = resultSet.getInt("securityCode");
			list.add(new CreditCard(id, name, number, expirationDate, securityCode, username));
		}
		return list;
	}

	public CreditCard getCreditCardById(int id) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement("select * from creditcard where id=?");
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		CreditCard creditCard = null;
		while (resultSet.next()) {
			String number = resultSet.getString("number");
			String name = resultSet.getString("name");
			String expirationDate = resultSet.getString("expirationDate");
			int securityCode = resultSet.getInt("securityCode");
			String username = resultSet.getString("User_username");
			creditCard = new CreditCard(id, name, number, expirationDate, securityCode, username);
		}

		return creditCard;

	}

	public CreditCard createCreditCard(String username, CreditCard creditCard) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement(
				"insert into creditcard (number,name,expirationDate,securityCode,User_username) values (?,?,?,?,?)");
		preparedStatement.setString(1, creditCard.getNumber());
		preparedStatement.setString(2, creditCard.getName());
		preparedStatement.setString(3, creditCard.getExpirationDate());
		preparedStatement.setLong(4, creditCard.getSecurityCode());
		preparedStatement.setString(5, username);
		preparedStatement.executeUpdate();
		// getid
		preparedStatement = dbCon.getCon()
				.prepareStatement("select * from creditcard where User_username=? and number=?");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, creditCard.getNumber());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String number = resultSet.getString("number");
			String name = resultSet.getString("name");
			String expirationDate = resultSet.getString("expirationDate");
			int securityCode = resultSet.getInt("securityCode");
			creditCard = new CreditCard(id, name, number, expirationDate, securityCode, username);
		}
		return creditCard;
	}

	public CreditCard updateCreditCard(int id, CreditCard creditCard) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement(
				"update creditcard set number=?, name=?, expirationDate=?, securityCode=? where id=?;");
		preparedStatement.setString(1, creditCard.getNumber());
		preparedStatement.setString(2, creditCard.getName());
		preparedStatement.setString(3, creditCard.getExpirationDate());
		preparedStatement.setLong(4, creditCard.getSecurityCode());
		preparedStatement.setInt(5, id);
		preparedStatement.executeUpdate();
		// getid
		preparedStatement = dbCon.getCon()
				.prepareStatement("select * from creditcard where User_username=? and number=?");
		preparedStatement.setString(1, creditCard.getUsername());
		preparedStatement.setString(2, creditCard.getNumber());
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String number = resultSet.getString("number");
			String name = resultSet.getString("name");
			String expirationDate = resultSet.getString("expirationDate");
			int securityCode = resultSet.getInt("securityCode");
			String username = resultSet.getString("User_username");
			creditCard = new CreditCard(id, name, number, expirationDate, securityCode, username);
		}
		return creditCard;
	}

	public int deleteCreditCard(int id) throws SQLException {
		preparedStatement = dbCon.getCon().prepareStatement("delete from creditcard where id=?");
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		return id;
	}

	public DatabaseConnection getDbCon() {
		return dbCon;
	}

	public void setDbCon(DatabaseConnection dbCon) {
		this.dbCon = dbCon;
	}

}
