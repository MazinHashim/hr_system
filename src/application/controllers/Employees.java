package application.controllers;

import java.time.LocalDate;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employees {
	private int empId;
	private String empName;
	private double empSalary;
	private Upgrade empUpgrade;
	private Holiday empHoliday;
	private String phone;
	private LocalDate dateOB;
	private Managements empManage;
	private LocalDate endOSer;
	private Jops empJop;
	public static ObservableList<Employees> empList = FXCollections.observableArrayList();
	public static ObservableList<Employees> empEndList = FXCollections.observableArrayList();
	
	public Employees(int empId, String empName, double empSalary, Upgrade empUpgrade, Managements empManage,
			Jops empJop,String phone,LocalDate dateOB) {
		
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empUpgrade = empUpgrade;
		this.empManage = empManage;
		this.empJop = empJop;
		this.dateOB = dateOB;
		this.phone = phone;
		addUniqueEmployee(empList, this);
	}
	public void decreaseSalary(double amount) {
		setEmpSalary(getEmpSalary() - amount);
	}
	public void increaseSalary(double amount) {
		setEmpSalary(getEmpSalary() + amount);
	}
	private void addUniqueEmployee(ObservableList<Employees> list,Employees item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getEmpId() == list.get(i).getEmpId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("خطأ", "الموظف  موجود مسبقاً");//.showWarning();
		else
			list.add(item);
	}
	
	public LocalDate getEndOSer() {
		return endOSer;
	}
	public void setEndOSer(LocalDate endOSer) {
		this.endOSer = endOSer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getDateOB() {
		return dateOB;
	}
	public void setDateOB(LocalDate dateOB) {
		this.dateOB = dateOB;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	public Upgrade getEmpUpgrade() {
		return empUpgrade;
	}
	public void setEmpUpgrade(Upgrade empUpgrade) {
		this.empUpgrade = empUpgrade;
	}
	public Holiday getEmpHoliday() {
		return empHoliday;
	}
	public void setEmpHoliday(Holiday empHoliday) {
		double amountInDay = empHoliday.getEmployee().getEmpSalary() / 30;
		empHoliday.getEmployee().decreaseSalary(Math.round(empHoliday.getHolDay() * amountInDay));
		this.empHoliday = empHoliday;
	}
	public Managements getEmpManage() {
		return empManage;
	}
	public void setEmpManage(Managements empManage) {
		this.empManage = empManage;
	}
	public Jops getEmpJop() {
		return empJop;
	}
	public void setEmpJop(Jops empJop) {
		this.empJop = empJop;
	}
	public static Employees getEmployee(int id) {
		for (Employees employee : empList) {
			if(employee.getEmpId() == id)
				return employee;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getEmpName()+" , "+getEmpId();
	}
	@Override
	public int hashCode() {
		
		return empList.indexOf(this);
	}
}
