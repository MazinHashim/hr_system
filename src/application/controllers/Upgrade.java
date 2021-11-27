package application.controllers;

import java.time.LocalDate;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Upgrade {
	private int upgId;
	private LocalDate upgDate;
	private String degree;
	private double salary;
	public static ObservableList<Upgrade> upgList = FXCollections.observableArrayList();
	
	public Upgrade(int upgId, LocalDate upgDate, String degree, double salary) {
		
		this.upgId = upgId;
		this.upgDate = upgDate;
		this.degree = degree;
		this.salary = salary;
		addUniqueUpgrade(upgList, this);
	}
	private void addUniqueUpgrade(ObservableList<Upgrade> list,Upgrade item) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if(item.getUpgId() == list.get(i).getUpgId())
				flag = true;
		}
		if(flag) 
			Main.dialogInfo("تنبيه", "الترقية  موجود مسبقاً").showWarning();
		else
			list.add(item);
	}
	public int getUpgId() {
		return upgId;
	}

	public void setUpgId(int upgId) {
		this.upgId = upgId;
	}

	public LocalDate getUpgDate() {
		return upgDate;
	}

	public void setUpgDate(LocalDate upgDate) {
		this.upgDate = upgDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return getDegree();
	}
	public static Upgrade getUpgrade(int id) {
		for (Upgrade upgrade : upgList) {
			if(upgrade.getUpgId() == id)
				return upgrade;
		}
		return null;
	}
	@Override
	public int hashCode() {
		
		return upgList.indexOf(this);
	}
	
	
}
