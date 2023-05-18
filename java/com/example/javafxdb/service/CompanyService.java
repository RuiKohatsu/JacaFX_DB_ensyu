package com.example.javafxdb.service;

import com.example.javafxdb.Company;
import com.example.javafxdb.util.DbUtil;
import com.example.javafxdb.dao.CompanyDao;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CompanyService {
    CompanyDao companyDao;

    public CompanyService(){
        var connection = DbUtil.getConnection();
        this.companyDao = new CompanyDao(connection);
    }

    public ObservableList<Company> tableView(){
        try{
            return companyDao.tableView();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Company> insertCompany(String infoCompany){
        try{
            return companyDao.insertCompany(infoCompany);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Company> deleteCompany(int id){
        try{
            return companyDao.deleteCompany(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Company> updateCompany(int id, String infoCompany){
        try{
            return companyDao.updateCompany(id, infoCompany);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}