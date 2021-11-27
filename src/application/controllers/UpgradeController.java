package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpgradeController implements Initializable{
	
	
    @FXML
    private TableView<Upgrade> upgTable;
    @FXML
    private TableColumn<Upgrade, String> uDegreeCol;
    @FXML
    private TableColumn<Upgrade, LocalDate> uDateCol;
    @FXML
    private TableColumn<Upgrade, Integer> uNumCol;
    @FXML
    private DatePicker upgDate;
    @FXML
    private TextField numEmpText,searchUpgText,nameEmpText,numUpgText,salText,degreeText;
    @FXML
    private JFXButton decButton,addButton;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	Main.justANumberVaild(numEmpText);
    	Main.justANumberVaild(numUpgText);
    	Main.justANumberVaild(salText);
		
		uDateCol.setCellValueFactory(new PropertyValueFactory<>("upgDate"));
		uDegreeCol.setCellValueFactory(new PropertyValueFactory<>("degree"));
		uNumCol.setCellValueFactory(new PropertyValueFactory<>("upgId"));
		upgTable.setItems(Upgrade.upgList);
		Main.getCellSearch(upgTable, Upgrade.upgList, searchUpgText);
		
	}
    
    @FXML
    private void addUgradeForEmp(ActionEvent event) {
    	if(!isClearAll()) {
    		Employees emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    		if(emp != null) {
    			Upgrade up = new Upgrade(Integer.parseInt(numUpgText.getText()), upgDate.getValue(),
						degreeText.getText(),Double.parseDouble(salText.getText()));
    			emp.setEmpUpgrade(up);
    			emp.setEmpSalary(up.getSalary());
    			clearField();
    		}else
    			Main.dialogInfo("خطأ", "الموظف غير موجود").showInformation();
    	}else {
    		Main.dialogInfo("معلومة", "يتتطلب إدخال كل البيانات").showInformation();
    	}
    }
    @FXML
    private void increaseUpgrade(ActionEvent event) {
    	try {
    		for (int i=0; i<9; i++) {
    			if(Upgrade.upgList.get(i).getDegree().equals(degreeText.getText())) {
    				degreeText.setText(Upgrade.upgList.get(i-1).getDegree());
    				salText.setText(Double.toString(Upgrade.upgList.get(i-1).getSalary()));
    				break;
    			}
    		}
    		decButton.setDisable(true);
		} catch (ArrayIndexOutOfBoundsException e) {
			Main.dialogInfo("معلومة", "الدرجة الوظيفية الأولى هي أعلى درجة").showInformation();
			addButton.setDisable(true);
		}
    	
    }
    @FXML
    private void searchEvent(ActionEvent event) {
    	addButton.setDisable(false);
    	decButton.setDisable(false);
    	Employees emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    	clearField();
    	if(emp != null)
    		fillRecords(emp);
    	else
    		Main.dialogInfo("خطأ", "الموظف غير موجود").showWarning();
    }

	private void fillRecords(Employees emp) {
		numEmpText.setText(Integer.toString(emp.getEmpId()));
    	nameEmpText.setText(emp.getEmpName());
   		numUpgText.setText(Integer.toString(emp.getEmpUpgrade().getUpgId()));
   		salText.setText(Double.toString(emp.getEmpSalary()));
    	upgDate.setValue(emp.getEmpUpgrade().getUpgDate());
    	degreeText.setText(emp.getEmpUpgrade().getDegree());
		
	}
	private void clearField() {
		numEmpText.clear();
    	nameEmpText.clear();
    	numUpgText.clear();
    	salText.clear();
    	upgDate.getEditor().clear();
    	degreeText.clear();
	}
	private boolean isClearAll() {
		if(numUpgText.getText().isEmpty()||numEmpText.getText().isEmpty()||nameEmpText.getText().isEmpty()
				||salText.getText().isEmpty()||upgDate.getEditor().getText().isEmpty()||degreeText.getText().isEmpty()){
			return true;
		}
		return false;
	}
}
