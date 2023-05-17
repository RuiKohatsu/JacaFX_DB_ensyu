package com.example.javafxdb.dao;

import com.example.javafxdb.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void infoInit() throws SQLException{
        final var SQL = "INSERT INTO companies VALUES (1, '株式会社A')" +
                ",(2, '株式会社B')" +
                ",(3, '株式会社C')";
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        stmt.executeUpdate();

        final var SQL1 = "INSERT INTO users VALUES (1, '山田太郎', 3, 85)" +
                ",(2, '山田次郎', 2, 76)" +
                ",(3, '山田三郎', 1, 92)";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        stmt1.executeUpdate();
    }

    public ObservableList<User> join() throws SQLException{
        final var SQL = "SELECT u.id AS id, c.name AS company_name, u.name AS user_name, score " +
                "FROM companies c " +
                "JOIN users u " +
                "ON c.id = u.company_id " +
                "ORDER BY id ASC";
        ObservableList<User> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();


        while (rs.next()) {
            var user = new User(rs.getInt("id"), rs.getString("company_name")
                    ,  rs.getString("user_name"),rs.getInt("score"));
            list.add(user);
        }
       return list;
    }

    public ObservableList<String> itemSelect() throws SQLException{
        final var SQL = "SELECT name FROM companies";
        ObservableList<String> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String user = rs.getString("name");
            list.add(user);
        }
        return list;
    }

    public void userInsert(String company, String name, int score)throws  SQLException{
        int company_id = 0;
        final var SQL = "SELECT id FROM companies WHERE name = '"+ company +"'";
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            company_id = rs.getInt("id");
        }

        final var SQL1 = "INSERT INTO users(name, company_id, score) VALUES('"+ name +"', '"+ company_id +"', '"+ score +"')";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        stmt1.executeUpdate();
    }

    public void userDelete(int id)throws SQLException{
        final var SQL = "DELETE FROM users WHERE id = '"+ id +"'";
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        stmt.executeUpdate();
    }

    public void userUpdate(int id, String company, String name, int score)throws SQLException{
        int company_id = 0;
        final var SQL = "SELECT id FROM companies WHERE name = '"+ company +"'";
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            company_id = rs.getInt("id");
        }

        final var SQL1 = "UPDATE users " +
                "SET name = '"+ name +"', company_id = '"+ company_id +"', score = '"+ score +"'" +
                "WHERE id = '"+ id +"'";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        stmt1.executeUpdate();
    }

    public ObservableList<User> userSearch(String keyword)throws SQLException{
        final var SQL = "SELECT u.id AS id, c.name AS company_name, u.name AS user_name, score " +
                "FROM users u " +
                "JOIN companies c " +
                "ON u.company_id = c.id " +
                "WHERE u.id IN (SELECT id FROM users WHERE name LIKE '%"+ keyword +"%')";

        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();
        ObservableList<User> list = FXCollections.observableArrayList();

        while (rs.next()) {
            var searchUser = new User(rs.getInt("id"), rs.getString("company_name")
                    ,  rs.getString("user_name"),rs.getInt("score"));
            list.add(searchUser);
        }
        return list;

    }






}