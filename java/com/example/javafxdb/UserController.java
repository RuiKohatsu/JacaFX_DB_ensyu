package com.example.javafxdb;

import com.example.javafxdb.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import javafx.stage.Stage;


import java.io.IOException;


public class UserController {

    @FXML
    private TextField addName;

    @FXML
    private TextField addScore;

    @FXML
    private ComboBox<String> addCompany;

    @FXML
    private TableColumn<User,Integer> iColumn;
    @FXML
    private TableColumn<User,String> cColumn;
    @FXML
    private TableColumn<User,String> nColumn;
    @FXML
    private TableColumn<User,Integer> sColumn;

    @FXML
    private ComboBox<String> editCompany;
    @FXML
    private TextField editName;
    @FXML
    private TextField editScore;
    @FXML
    private Label error;
    @FXML
    private TableView<User> myTableView;
    @FXML
    private TextField searchName;
    private int id;
    public UserService userService = new UserService();
    ObservableList<User> user2List = FXCollections.observableArrayList();
    ObservableList<User> searchList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        myTableView.setTableMenuButtonVisible(false);

        userService.infoInit();
        user2List = userService.join();

        iColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        nColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        myTableView.setItems(user2List);

//        ObservableList<String> items = FXCollections.observableArrayList("株式会社A", "株式会社B", "株式会社C");
        ObservableList<String> items = userService.itemSelect();

        addCompany.setItems(items);
        editCompany.setItems(items);
    }

    public void addInfo(ActionEvent actionEvent) {
        if (addCompany.getValue() == null || addName.getText().isEmpty() || addScore.getText().isEmpty() ||
                !"0123456789".contains(addScore.getText()) || Integer.parseInt(addScore.getText()) < 0 || Integer.parseInt(addScore.getText()) > 100) {
            error.setText("エラー：TextFieldへの空白　or 不正な値の設定 or 企業の未選択");
        }else{
            var infoCompany = addCompany.getValue();
            var infoName = addName.getText();
            var infoScore = Integer.parseInt(addScore.getText());
//            var idSize = user2List.size() + 1;
            error.setText("");

            userService.userInsert(infoCompany, infoName, infoScore);
            user2List = userService.join();
            myTableView.setItems(user2List);
        }
    }

    public void selectTable(){
        int index = myTableView.getSelectionModel().getSelectedIndex();
        // 行のデータを取得
        Object clickedRowData = myTableView.getSelectionModel().getSelectedItem();
        // 列のデータを取得
        var specificIdData = ((User) clickedRowData).getId();
        var specificCompanyData = ((User) clickedRowData).getCompany();
        var specificNameData = ((User) clickedRowData).getName();
        var specificScoreData = ((User) clickedRowData).getScore();

        this.id = specificIdData;
        editCompany.setValue(specificCompanyData);
        editName.setText(specificNameData);
        editScore.setText( String.valueOf(specificScoreData));
    }

    public void deleteInfo(ActionEvent actionEvent){
        userService.userDelete(id);
        user2List = userService.join();
        myTableView.setItems(user2List);

    }

    public void updateInfo(){
        try {
            var infoCompany = editCompany.getValue();
            var infoName = editName.getText();
            var infoScore = Integer.parseInt(editScore.getText());
            if (editCompany.getValue() == null || editName.getText().isEmpty() || editScore.getText().isEmpty() ||
                    Integer.parseInt(editScore.getText()) < 0 || Integer.parseInt(editScore.getText()) > 100) {
                error.setText("エラー：TextFieldへの空白　or 不正な値の設定 or 企業の未選択");
            }else {
                userService.userUpdate(this.id, infoCompany, infoName, infoScore);
                user2List = userService.join();
                myTableView.setItems(user2List);
                error.setText("");
            }

        }catch(NumberFormatException e){
                error.setText("エラー：TextFieldへの空白　or 不正な値の設定 or 企業の未選択");
        }

    }

    public void search(){
        var keyword = searchName.getText();
        searchList = userService.userSearch(keyword);
        myTableView.setItems(searchList);
    }

    public void back(){
        searchList.clear();
        myTableView.setItems(user2List);
    }

    public void manageCompany()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserApplication.class.getResource("Company-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 400);

        Stage newStage = new Stage();
        newStage.setTitle("企業管理");
        newStage.setScene(scene);

        // ウィンドウのコンテンツを設定
        // ...

        newStage.show();
    }
}


