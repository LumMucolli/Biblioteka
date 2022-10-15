package com.probitacademy.biblioteka.dao;
import java.sql.*;
import java.util.*;
import com.probitacademy.biblioteka.domain.User;
public class UserDao implements Dao<User> {
@Override
public User get(long id) throws Exception {
User user = null;
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setLong(1, id);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
user = createUser(rs);
}
}
return user;
}
@Override
public List<User> getAll() throws Exception {
ArrayList<User> list = null;
try (Connection conn = ConnectionFactory.getConnection()) {
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
ResultSet rs = stmt.executeQuery();
list = new ArrayList<>();
while (rs.next()) {
User user = createUser(rs);
list.add(user);
}
}
return list;
}
private User createUser(ResultSet rs) throws SQLException {
User user = new User();
user.setId(rs.getInt("id"));
user.setUsername(rs.getString("username"));
user.setPassword(rs.getString("password"));
user.setLastLogin(rs.getTimestamp("last_login"));
return user;
}
@Override
public int save(User user) throws Exception {
if (user.getId() > 0) {
return update(user);
} else {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, user.getUsername());
stmt.setString(2, user.getPassword());
return stmt.executeUpdate();
}
}
}
@Override
public int update(User user) throws Exception {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "UPDATE users SET username = ? , password = ? WHERE id=?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, user.getUsername());
stmt.setString(2, user.getPassword());
stmt.setLong(3, user.getId());
return stmt.executeUpdate();
}
}
@Override
public int delete(User user) throws Exception {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "DELETE FROM users WHERE id=?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setLong(1, user.getId());
return stmt.executeUpdate();
}
}
public User login(String username, String password) throws Exception {
User user = null;
try (Connection conn = ConnectionFactory.getConnection()) {
String query = "SELECT * FROM users WHERE username=? AND password=?";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, username);
pstmt.setString(2, password);
ResultSet rs = pstmt.executeQuery();
if (rs.next()) {
user = createUser(rs);
}
}
return user;
}
}