package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.app.configs.DataSource;
import com.app.models.User;

public class UserDao {

	Connection connection = null; // ket noi toi database
	PreparedStatement statement = null; // thuc thi cau lenh SQL
	ResultSet result = null; // ket qua cau lenh SQL

	public List<User> listUser() {
		try {

			String query = "SELECT * FROM user ORDER BY createdAt DESC LIMIT 10";

			connection = new DataSource().getConnection();
			
			statement = connection.prepareStatement(query);
			
			result = statement.executeQuery();

			List<User> users = new ArrayList<User>();
			while (result.next()) {
				User user = new User();
				user.setId(result.getString("id"));
				user.setName(result.getString("name"));
				user.setAddress(result.getString("address"));
				user.setEmail(result.getString("email"));
				users.add(user);
			}
			result.close();
			statement.close();
			connection.close();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public User detail(String id) {
		User user = null;
		try {
			String query = "SELECT * FROM user WHERE user.id = ?";
			connection = new DataSource().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, id);

			result = statement.executeQuery();
			while (result.next()) {
				user = new User();
				user.setId(result.getString("id"));
				user.setName(result.getString("name"));
				user.setAddress(result.getString("address"));
				user.setEmail(result.getString("email"));
			}
			result.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean insertUser(User user) {
		try {
			Calendar now = Calendar.getInstance();
			String query = "INSERT INTO user (id, name, address, email, createdAt) " + "VALUES (?, ?, ?, ?, ?)";
			connection = new DataSource().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, user.getId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getEmail());
			statement.setDate(5, new java.sql.Date(now.getTimeInMillis()));

			boolean insertResult = statement.executeUpdate() > 0;
			statement.close();
			connection.close();

			return insertResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(User user) {
		try {
			Calendar now = Calendar.getInstance();
			String query = "UPDATE user SET name = ?, email = ?, address = ?, updatedAt = ? WHERE id = ?";
			connection = new DataSource().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getAddress());
			statement.setDate(4, new java.sql.Date(now.getTimeInMillis()));
			statement.setString(5, user.getId());
			boolean updateResult = statement.executeUpdate() > 0;
			statement.close();
			connection.close();

			return updateResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(String id) {
		try {
			String query = "DELETE FROM user WHERE id = ? ";
			connection = new DataSource().getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, id);

			boolean deletedResult = statement.executeUpdate() > 0;
			statement.close();
			connection.close();

			return deletedResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
