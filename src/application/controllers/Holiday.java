package application.controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Holiday {
	private int holId;
	private String holtype;
	private int holDay;
	private Employees employee;
	
	public static ObservableList<Holiday> holList = FXCollections.observableArrayList();
	
	public Holiday(int holId, String holtype, int holDay,int id) {
		this.holId = holId;
		this.holtype = holtype;
		this.holDay = holDay;
		addUniqueHoliday(holList, this);
		for (Employees emp : Employees.empList) {
			if(emp.getEmpId() == id)
				this.employee = emp;
		}
		
	}
	private void addUniqueHoliday(ObservableList<Holiday> list,Holiday item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getHolId() == list.get(i).getHolId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "رقم الإجازة موجود  مسبقاً");//.showWarning();
		else
			list.add(item);
	}
	
	public Employees getEmployee() {
		
		return employee;
	}
	public void setEmployee(int id) {
		for (Employees emp : Employees.empList) {
			if(emp.getEmpId() == id)
				this.employee = emp;
		}
	}
	public int getHolId() {
		return holId;
	}
	public void setHolId(int holId) {
		this.holId = holId;
	}
	public String getHoltype() {
		return holtype;
	}
	public void setHoltype(String holtype) {
		this.holtype = holtype;
	}
	public int getHolDay() {
		return holDay;
	}
	public void setHolDay(int holDay) {
		this.holDay = holDay;
	}
	
	@Override
	public String toString() {
		return getHoltype();
	}

	public static Holiday getHoliday(int id) {
		for (Holiday holiday : holList) {
			if(holiday.getHolId() == id)
				return holiday;
		}
		return null;
	}
	@Override
	public int hashCode() {
		
		return holList.indexOf(this);
	}
}
