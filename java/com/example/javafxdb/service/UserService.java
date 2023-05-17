package com.example.javafxdb.service;

import com.example.javafxdb.util.DbUtil;
import com.example.javafxdb.User;
import com.example.javafxdb.dao.UserDao;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserService {
    UserDao userDao;

    public UserService(){
        var connection = DbUtil.getConnection();
        this.userDao = new UserDao(connection);
    }

    public void infoInit(){
        try{
           userDao.infoInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> join(){
        try{
            return userDao.join();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<String> itemSelect(){
        try {
            return userDao.itemSelect();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void userInsert(String company, String name, int score){
        try {
            userDao.userInsert(company, name, score);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void userDelete(int id){
        try {
            userDao.userDelete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void userUpdate(int id, String company, String name, int score){
        try {
            userDao.userUpdate(id, company, name, score);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ObservableList<User> userSearch(String keyword){
        try {
            return userDao.userSearch(keyword);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }



}