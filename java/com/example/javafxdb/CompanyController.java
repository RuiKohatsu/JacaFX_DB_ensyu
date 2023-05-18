package com.example.javafxdb;


import com.example.javafxdb.service.CompanyService;
import com.example.javafxdb.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyController {
    @FXML
    private TextField addCompany;
    @FXML
    private TextField editCompany;
    @FXML
    private TableColumn<Company,Integer> idColumn;
    @FXML
    private TableColumn<Company,String> companyColumn;
    @FXML
    private TableView<Company> companyTableView;
    public CompanyService companyService = new CompanyService();

    public UserService userService = new UserService();
//    public UserController userController = new UserController();

    ObservableList<Company> companiesList = FXCollections.observableArrayList();



    private int id;

    @FXML
    public void initialize() {
        companyTableView.setTableMenuButtonVisible(false);
        companiesList = companyService.tableView();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        companyTableView.setItems(companiesList);

    }

    public void insertCompany() {
            var infoCompany = addCompany.getText();
            companiesList = companyService.insertCompany(infoCompany);
            companyTableView.setItems(companiesList);
//            userService.join();



    }

    public void selectCompanyTable(){
        int index = companyTableView.getSelectionModel().getSelectedIndex();
        // 行のデータを取得
        Object clickedRowData = companyTableView.getSelectionModel().getSelectedItem();
        // 列のデータを取得
        var specificIdData = ((Company) clickedRowData).getId();
        var specificCompanyData = ((Company) clickedRowData).getName();
        this.id = specificIdData;

        editCompany.setText(specificCompanyData);
    }

    public void deleteInfo(){
        companiesList = companyService.deleteCompany(id);
        companyTableView.setItems(companiesList);
//        userService.join();

    }

    public void updateInfo(){
        var infoCompany = editCompany.getText();
        companiesList = companyService.updateCompany(this.id, infoCompany);
        companyTableView.setItems(companiesList);
//        userService.join();

//        var a = userController.user2List = userService.join();
//        System.out.println(a);
//
//        userController.aa(a);

//        userController.myTableView.setItems(userController.user2List);
    }

}


