package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmpMenuController implements Initializable{
	
    @FXML
    private TableView<Employees> EmpTable;
    @FXML
    private TextField searchEmpText;
    
    private TableColumn<Employees, String> nameCol,upgradeCol,jopCol,manageCol;
    private TableColumn<Employees, Double> salaryCol;
    private static EmpMenuController instance;
    public EmpMenuController() {
		instance = this;
	}
	
    public static EmpMenuController getInstance() {
		return instance;
	}
    public TableView<Employees> getEmpTable(){
    	return EmpTable;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnInitialization();
		Main.getCellSearch(EmpTable, Employees.empList, searchEmpText);
	}
	
    @FXML
    private void selectEmpByMouse(MouseEvent event) {
    	if(event.getClickCount() == 2) {
    		Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/AddEmp.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    @FXML
    private void deleteEmpAction(ActionEvent event) {
    	int index = EmpTable.getSelectionModel().getSelectedIndex();
    	if(index <= -1) {
    		return;
    	}
    	EmpTable.getItems().remove(index);
    }

    @FXML
    private void editEmpAction(ActionEvent event) {
    	EmpTable.getSelectionModel().clearSelection();
    	Parent show;
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			show = FXMLLoader.load(getClass().getResource("/application/fxml/AddEmp.fxml"));
			Main.Visible(stage, show);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	@SuppressWarnings("unchecked")
	private void columnInitialization() {
		nameCol = new TableColumn<>("أسماء الموظفين");
		nameCol.setPrefWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
		upgradeCol = new TableColumn<>("الدرجة الوظيفية");
		upgradeCol.setPrefWidth(200);
		upgradeCol.setCellValueFactory(new PropertyValueFactory<>("empUpgrade"));
		jopCol = new TableColumn<>("الوظيفة");
		jopCol.setPrefWidth(200);
		jopCol.setCellValueFactory(new PropertyValueFactory<>("empJop"));
		manageCol = new TableColumn<>("الإدارة");
		manageCol.setPrefWidth(200);
		manageCol.setCellValueFactory(new PropertyValueFactory<>("empManage"));
		salaryCol = new TableColumn<>("الراتب");
		salaryCol.setPrefWidth(200);
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
		
		EmpTable.getColumns().addAll(upgradeCol,salaryCol,jopCol,manageCol,nameCol);
		EmpTable.setItems(Employees.empList);
		
	}
}
