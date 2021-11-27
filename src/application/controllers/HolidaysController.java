package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class HolidaysController implements Initializable{

    @FXML
    private TextField numHolText,nameEmpText,dayHolText,typeHolText,numEmpText,searchHoliText;
    @FXML
    private TableView<Holiday> HoliTable;
    @FXML
    private TableColumn<Employees, Employees> hEmpCol;
    @FXML
    private TableColumn<Holiday, Integer> hDayCol,hNumCol;
    @FXML
    private TableColumn<Holiday, String> holTypeCol;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	Main.justANumberVaild(dayHolText);
    	Main.justANumberVaild(numEmpText);
    	Main.justANumberVaild(numHolText);
    	
    	hDayCol.setCellValueFactory(new PropertyValueFactory<>("holDay"));
    	hEmpCol.setCellValueFactory(new PropertyValueFactory<>("employee"));
    	holTypeCol.setCellValueFactory(new PropertyValueFactory<>("holtype"));
    	hNumCol.setCellValueFactory(new PropertyValueFactory<>("holId"));
    	Main.getCellSearch(HoliTable, Holiday.holList, searchHoliText);
    	
    	HoliTable.setItems(Holiday.holList);	
	}
    
    @FXML
    private void addHolidayForEmp(ActionEvent event) {
    	if(!isClearAll()) {
    		Employees emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    		if(emp != null) {
    			emp.setEmpHoliday(new Holiday(Integer.parseInt(numHolText.getText()), typeHolText.getText(),
    						Integer.parseInt(dayHolText.getText()), emp.getEmpId()));
    		}else
    			Main.dialogInfo("خطأ", "الموظف غير موجود");//.showInformation();
    	}else {
    		Main.dialogInfo("معلومة", "يتتطلب إدخال كل البيانات");//.showInformation();
    	}
    	
    	
    }
    @FXML
    private void searchEvent(ActionEvent event) {
    	Employees emp = Employees.getEmployee(Integer.parseInt(numEmpText.getText()));
    	numEmpText.clear();
    	nameEmpText.clear();
    	typeHolText.clear();
    	numHolText.clear();
    	dayHolText.clear();
    	if(emp != null)
    		fillRecords(emp);
    	else
    		Main.dialogInfo("خطأ", "الموظف غير موجود");//.showWarning();
    }
    @FXML
    private void selectHoliByMouse(MouseEvent event) {
    	if(event.getClickCount() == 2) {
    		int index = HoliTable.getSelectionModel().getSelectedIndex();
    		Holiday hol = Holiday.holList.get(index);
    		numEmpText.setText(Integer.toString(hol.getEmployee().getEmpId()));
        	nameEmpText.setText(hol.getEmployee().getEmpName());
        	typeHolText.setText(hol.getHoltype());
        	numHolText.setText(Integer.toString(hol.getHolId()));
       		dayHolText.setText(Integer.toString(hol.getHolDay()));	
    	}
    	
    }
    
    private void fillRecords(Employees emp) {
    	
    	numEmpText.setText(Integer.toString(emp.getEmpId()));
    	nameEmpText.setText(emp.getEmpName());
    	try {
    		typeHolText.setText(emp.getEmpHoliday().getHoltype());
    		numHolText.setText(Integer.toString(emp.getEmpHoliday().getHolId()));
    		dayHolText.setText(Integer.toString(emp.getEmpHoliday().getHolDay()));
		} catch (NullPointerException e) {
			Main.dialogInfo("معلومة", "هذا الموظف ليس له إجازة قم بإضافة إجازة له");//.showInformation();
		}
	}
    private boolean isClearAll() {
		if(numHolText.getText().isEmpty()||numEmpText.getText().isEmpty()||nameEmpText.getText().isEmpty()
				||typeHolText.getText().isEmpty()||dayHolText.getText().isEmpty()){
			return true;
		}
		return false;
	}
}
