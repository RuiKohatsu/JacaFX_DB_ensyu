package com.example.javafxdb;


import com.example.javafxdb.service.CompanyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<Company> companiesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        companyTableView.setTableMenuButtonVisible(false);
        companiesList = companyService.tableView();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

        companyTableView.setItems(companiesList);
    }




}


