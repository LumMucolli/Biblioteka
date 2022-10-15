package com.probitacademy.biblioteka.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.probitacademy.biblioteka.domain.Author;
public class AuthorDao implements Dao<Author> {
@Override
public Author get(long id) throws Exception {
Author author = null;
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "SELECT * FROM authors WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setLong(1, id);
ResultSet rs = stmt.executeQuery();
if (rs.next()) {
author = createAuthor(rs);
}
}
return author;
}
@Override
public List<Author> getAll() throws Exception {
ArrayList<Author> list = null;
try (Connection conn = ConnectionFactory.getConnection()) {
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM authors");
ResultSet rs = stmt.executeQuery();
list = new ArrayList<>();
while (rs.next()) {
Author author = createAuthor(rs);
list.add(author);
}
}
return list;
}
@Override
public int save(Author author) throws Exception {
if (author.getId() > 0) {
return update(author);
} else {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "INSERT INTO authors (name, lastname, country) VALUES (?, ?, ?)";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, author.getName());
stmt.setString(2, author.getLastName());
stmt.setString(3, author.getCountry());
return stmt.executeUpdate();
}
}
}
@Override
public int update(Author author) throws Exception {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "UPDATE authors SET name = ? , lastname = ? WHERE id=?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, author.getName());
stmt.setString(2, author.getLastName());
stmt.setLong(3, author.getId());
return stmt.executeUpdate();
}
}
@Override
public int delete(Author author) throws Exception {
try (Connection conn = ConnectionFactory.getConnection()) {
String sql = "DELETE FROM authors WHERE id=?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setLong(1, author.getId());
return stmt.executeUpdate();
}
}
private Author createAuthor(ResultSet rs) throws SQLException {
Author author = new Author();
author.setId(rs.getInt("id"));
author.setName(rs.getString("name"));
author.setLastName(rs.getString("lastname"));
author.setCountry(rs.getString("country"));
return author;
}
}
