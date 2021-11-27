package application.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class endServController implements Initializable{
    @FXML
    private TableView<Employees> EmpEndTable;
    @FXML
    private TableColumn<Employees, LocalDate> endSerCol;
    @FXML
    private TableColumn<Employees, String> phoneCol;
    @FXML
    private TableColumn<Employees, String> degreeCol;
    @FXML
    private TableColumn<Employees, Double> salCol;
    @FXML
    private TableColumn<Employees, Jops> jopCol;
    @FXML
    private TableColumn<Employees, Managements> manageCol;
    @FXML
    private TableColumn<Employees, String> nameCol;
    @FXML
    private TableColumn<Employees, Integer> numCol;
    @FXML
    private TextField searchEmpText,numEmpText,nameEmpText,degreeText,salaryText,mangeText,jopText,phoneText;
    @FXML
    private DatePicker upgDate;
    @FXML
    private DatePicker DateOB;
    @FXML
    private DatePicker DateEnd;
    Employees emp = null;
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	Main.justANumberVaild(numEmpText);
    	Main.justANumberVaild(salaryText);
    	Main.justANumberVaild(searchEmpText);
    	numCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
    	jopCol.setCellValueFactory(new PropertyValueFactory<>("empJop"));
    	salCol.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
    	degreeCol.setCellValueFactory(new PropertyValueFactory<>("empUpgrade"));
    	phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
    	manageCol.setCellValueFactory(new PropertyValueFactory<>("empManage"));
    	endSerCol.setCellValueFactory(new PropertyValueFactory<>("endOSer"));
		EmpEndTable.setItems(Employees.empEndList);
		
	}
    @FXML
    private void searchEvent(ActionEvent event) {
    	emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    	if(emp != null){
    		numEmpText.setText(Integer.toString(emp.getEmpId()));
        	nameEmpText.setText(emp.getEmpName());
        	degreeText.setText(emp.getEmpUpgrade().getDegree());
        	salaryText.setText(Double.toString(emp.getEmpSalary()));
        	mangeText.setText(emp.getEmpManage().getMngName());
        	jopText.setText(emp.getEmpJop().getJopName());
        	phoneText.setText(emp.getPhone());
        	upgDate.setValue(emp.getEmpUpgrade().getUpgDate());
        	DateOB.setValue(emp.getDateOB());
    	}else {
    		Main.dialogInfo("معلومة", "الوظف غير موجود");//.showInformation();
    	}
    }

    @FXML
    private void serviceEndAction(ActionEvent event) {
    	if(!isClearAll()) {
    		emp.setEndOSer(DateEnd.getValue());
    		Employees.empList.remove(emp);
    		Employees.empEndList.add(emp);
    	}else
    		Main.dialogInfo("خطأ", "يتتطلب إدخال كل البيانات");//.showError();
    }
    private boolean isClearAll() {
		if(numEmpText.getText().isEmpty()||mangeText.getText().isEmpty()||nameEmpText.getText().isEmpty()
				||salaryText.getText().isEmpty()||upgDate.getEditor().getText().isEmpty()||DateEnd.getEditor().getText().isEmpty()
				||DateOB.getEditor().getText().isEmpty()
				||jopText.getText().isEmpty()||degreeText.getText().isEmpty()||phoneText.getText().isEmpty()){
			return true;
		}
		return false;
	}
}
