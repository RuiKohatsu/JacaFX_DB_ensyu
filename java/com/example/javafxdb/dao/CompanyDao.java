package com.example.javafxdb.dao;

import com.example.javafxdb.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao {

    private Connection connection;

    public CompanyDao(Connection connection) {
        this.connection = connection;
    }

    public ObservableList<Company> tableView() throws SQLException {
        final var SQL = "SELECT * FROM companies ORDER BY id";
        ObservableList<Company> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();


        while (rs.next()) {
            var company = new Company(rs.getInt("id"), rs.getString("name"));
            list.add(company);
//            System.out.print(rs.getInt("name"));
        }
        return list;
    }

    public ObservableList<Company> insertCompany(String infoCompany) throws SQLException {
        final var SQL = "INSERT INTO companies(name) VALUES('"+ infoCompany +"')";
        ObservableList<Company> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        stmt.executeUpdate();

        final var SQL1 = "SELECT * FROM companies ORDER BY id";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        ResultSet rs = stmt1.executeQuery();

        while (rs.next()) {
            var company = new Company(rs.getInt("id"), rs.getString("name"));
            list.add(company);

        }
        return list;
    }

    public ObservableList<Company> deleteCompany(int id) throws SQLException {
        final var SQL = "DELETE FROM companies WHERE id = '"+ id +"'";
        ObservableList<Company> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        stmt.executeUpdate();

        final var SQL1 = "SELECT * FROM companies ORDER BY id";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        ResultSet rs = stmt1.executeQuery();

        while (rs.next()) {
            var company = new Company(rs.getInt("id"), rs.getString("name"));
            list.add(company);
        }
        return list;
    }

    public ObservableList<Company> updateCompany(int id, String infoCompany) throws SQLException {
        final var SQL = "UPDATE companies " +
                "SET name = '"+ infoCompany +"'" +
                "WHERE id = '"+ id +"'";
        ObservableList<Company> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        stmt.executeUpdate();

        final var SQL1 = "SELECT * FROM companies ORDER BY id";
        PreparedStatement stmt1 = this.connection.prepareStatement(SQL1);
        ResultSet rs = stmt1.executeQuery();

        while (rs.next()) {
            var company = new Company(rs.getInt("id"), rs.getString("name"));
            list.add(company);
        }
        return list;
    }

}