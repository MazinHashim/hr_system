package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddEmpController implements Initializable{
    @FXML
    private TextField numEmpText,phoneText,nameEmpText,numUpgText,salaryText,degreeText;
    @FXML
    private JFXButton editButton;
    @FXML
    private ComboBox<Jops> jopBox;
    @FXML
    private ComboBox<Managements> manageBox;
    @FXML
    private DatePicker upgDate,dateOB;
    
    private ObservableList<Jops> jp;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main.justANumberVaild(numEmpText);
		Main.justANumberVaild(numUpgText);
		Main.justANumberVaild(salaryText);
		
    	degreeText.setText(Upgrade.upgList.get(8).getDegree());
       	salaryText.setText(Double.toString(Upgrade.upgList.get(8).getSalary()));
		manageBox.setItems(Managements.manageList);
		try {
			int index  = EmpMenuController.getInstance().getEmpTable().getSelectionModel().getSelectedIndex();
			Employees emp = EmpMenuController.getInstance().getEmpTable().getItems().get(index);
			fillRecords(emp);			
		} catch (ArrayIndexOutOfBoundsException e) {
			Main.dialogInfo("تنبيه", "إختار موظف واحد من الجدول");
		}
		degreeText.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				if(!newValue) {
					for (int i=0; i<9; i++) {
						if(Upgrade.upgList.get(i).getDegree().equals(degreeText.getText()))
							salaryText.setText(Double.toString(Upgrade.upgList.get(i).getSalary()));
					}
					
				}
			}
		});
	}
	
    @FXML
    private void AddEmpEvent(ActionEvent event) {
    	if(!isClearAll()) {
    		addNewEmployee();
    		EmpMenuController.getInstance().getEmpTable().refresh();
    		clearFields();
    		
    	}else {
    		Main.dialogInfo("خطأ", "تطلب إدخال كل البيانات");//.showError();
    	}
    }

	@FXML
    private void editEmpEvent(ActionEvent event) {
		Employees index = Employees.getEmployee(Integer.parseInt(numEmpText.getText())); 
    	if(!isClearAll()) {
    		if(Employees.empList.remove(index)) {
        		addNewEmployee();
        		EmpMenuController.getInstance().getEmpTable().refresh();
        		clearFields();
        		Main.dialogInfo("معلومة", "تم تعديل البيانات وإضافته في نهاية الجدول");//.showInformation();    			
    		}
    		else	
    			Main.dialogInfo("خطأ", "الموظف غير موجود");//.showError();
    	}else {
			Main.dialogInfo("خطأ", "يتتطلب إدخال كل البيانات");//.showError();
		}

    }

    @FXML
    private void searchEvent(ActionEvent event) {
    	Employees emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    	if(emp != null) 
    		fillRecords(emp);
    	else
    		Main.dialogInfo("خطأ", "الموظف غير موجود");//.showWarning();
    }

    private Employees addNewEmployee() {
    	Managements mang = manageBox.getValue();
    	Jops jp = mang.getManageJopList().get(jopBox.getSelectionModel().getSelectedIndex());
    	Upgrade upg = Upgrade.upgList.get(8);
    	double sal = upg.getSalary();
		return new Employees(Integer.parseInt(numEmpText.getText()),
									nameEmpText.getText(),sal, upg, mang, jp,phoneText.getText(),dateOB.getValue());
	}
	@FXML
    private void mainSelectioAction(ActionEvent event) {
    	try {
        	jp = Managements.manageList.get(manageBox.getValue().hashCode()).getManageJopList();
       		jopBox.setItems(jp);
		} catch (NullPointerException e) {}
    }
    
    private void fillRecords(Employees emp) {
    	try {
        	jp = Managements.manageList.get(manageBox.getValue().hashCode()).getManageJopList();
       		jopBox.setItems(jp);
		} catch (NullPointerException e) {}
    	
    	numEmpText.setText(Integer.toString(emp.getEmpId()));
    	nameEmpText.setText(emp.getEmpName());
		numUpgText.setText(Integer.toString(emp.getEmpUpgrade().getUpgId()));
		upgDate.setValue(emp.getEmpUpgrade().getUpgDate());
		salaryText.setText(Double.toString(emp.getEmpSalary()));
		manageBox.setValue(emp.getEmpManage());
		jopBox.setValue(emp.getEmpJop());
		degreeText.setText(emp.getEmpUpgrade().getDegree());
		phoneText.setText(emp.getPhone());
		dateOB.setValue(emp.getDateOB());
		editButton.setDisable(false);
		
	}
    
    private void clearFields() {
    	numEmpText.clear();
		numUpgText.clear();
		nameEmpText.clear();
		salaryText.clear();
		upgDate.getEditor().clear();
		degreeText.clear();
		manageBox.getSelectionModel().select(-1);
		jopBox.getSelectionModel().select(-1);
		
	}
    private boolean isClearAll() {
		if(numUpgText.getText().isEmpty()||numEmpText.getText().isEmpty()||nameEmpText.getText().isEmpty()
				||salaryText.getText().isEmpty()||upgDate.getEditor().getText().isEmpty()
				||manageBox.getSelectionModel().isEmpty()||degreeText.getText().isEmpty()||jopBox.getSelectionModel().isEmpty()){
			return true;
		}
		return false;
	}
}
