package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagementController implements Initializable{

    @FXML
    private TextField searchMngText;
    @FXML
    private TableView<Managements> ManageTable;
    @FXML
    private TableColumn<Managements, ObservableList<Jops>> mJopsCol;
    @FXML
    private TableColumn<Managements, String> mNameCol;
    @FXML
    private TableColumn<Managements, Integer> mNumCol;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	initailManageColumns();
    	Main.getCellSearch(ManageTable, Managements.manageList, searchMngText);
    	ManageTable.setItems(Managements.manageList);
		
	}
    private void initailManageColumns() {
    	mNumCol.setCellValueFactory(new PropertyValueFactory<>("mgnId"));
    	mNameCol.setCellValueFactory(new PropertyValueFactory<>("mngName"));
    	mJopsCol.setCellValueFactory(new PropertyValueFactory<>("manageJopList"));
    	
    }
}
