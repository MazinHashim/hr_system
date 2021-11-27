package application.controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Managements {
	private int mgnId;
	private String mngName;
	public static ObservableList<Managements> manageList = FXCollections.observableArrayList();
	private ObservableList<Jops> manageJopList;
	
	public Managements(int mgnId, String mngName) {
		this.mgnId = mgnId;
		this.mngName = mngName;
		addUniqueManagement(manageList, this);
		manageJopList = FXCollections.observableArrayList();
	}
	
	public ObservableList<Jops> getManageJopList() {
		return manageJopList;
	}

	public void addManageJop(Jops manageJop) {
		addUniqueMngJop(manageJopList, manageJop);
	}
	
	private void addUniqueMngJop(ObservableList<Jops> list,Jops item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getJopId() == list.get(i).getJopId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "الوظيفة موجود مسبقاً في هذه الإدارة");//.showWarning();
		else
			list.add(item);
	}
	private void addUniqueManagement(ObservableList<Managements> list,Managements item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getMgnId() == list.get(i).getMgnId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "الإدارة  موجود مسبقاً");//.showWarning();
		else
			list.add(item);
	}

	public int getMgnId() {
		return mgnId;
	}
	public void setMgnId(int mgnId) {
		this.mgnId = mgnId;
	}
	public String getMngName() {
		return mngName;
	}
	public void setMngName(String mngName) {
		this.mngName = mngName;
	}

	@Override
	public String toString() {
		return getMngName();
	}

	public static Managements getManagement(int id) {
		for (Managements management : manageList) {
			if(management.getMgnId() == id)
				return management;
		}
		return null;
	}
	@Override
	public int hashCode() {
		
		return manageList.indexOf(this);
	}
}
