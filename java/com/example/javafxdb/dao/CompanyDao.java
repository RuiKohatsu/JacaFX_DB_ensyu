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
        final var SQL = "SELECT * FROM companies";
        ObservableList<Company> list = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement(SQL);
        ResultSet rs = stmt.executeQuery();


        while (rs.next()) {
            var company = new Company(rs.getInt("id"), rs.getString("name"));
            list.add(company);
        }
        return list;
    }

}