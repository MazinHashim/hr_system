package application.controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Jops {
	private int jopId;
	private String jopName;
	public static ObservableList<Jops> jopList = FXCollections.observableArrayList();
	private ObservableList<Employees> jopEmpList;
	
	public Jops(int jopId, String jopName) {
		
		this.jopId = jopId;
		this.jopName = jopName;
		addUniqueJop(jopList, this);
		jopEmpList = FXCollections.observableArrayList();
	}
	private void addUniqueJop(ObservableList<Jops> list,Jops item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getJopId() == list.get(i).getJopId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "الوظيفة  موجود مسبقاً");//.showWarning();
		else
			list.add(item);
	}
	private void addUniqueJopEmp(ObservableList<Employees> list,Employees item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getEmpId() == list.get(i).getEmpId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "الموظف  يمتلك هذه الوظيفة  مسبقاً");//.showWarning();
		else
			list.add(item);
	}
	
	
	public ObservableList<Employees> getJopEmpList() {
		return jopEmpList;
	}
	public void addJopEmp(Employees jopEmp) {
		addUniqueJopEmp(jopEmpList, jopEmp);
	}
	public int getJopId() {
		return jopId;
	}
	public void setJopId(int jopId) {
		this.jopId = jopId;
	}
	public String getJopName() {
		return jopName;
	}
	public void setJopName(String jopName) {
		this.jopName = jopName;
	}
	@Override
	public String toString() {
		return getJopName();
	}
	public static Jops getJop(int id) {
		for (Jops jop : jopList) {
			if(jop.getJopId() == id)
				return jop;
		}
		return null;
	}
	@Override
	public int hashCode() {
		
		return jopList.indexOf(this);
	}
	
	
}
