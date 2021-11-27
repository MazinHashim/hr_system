package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainPageController implements Initializable{
    @FXML
    private JFXButton menuEmpButton,holiButton,endButton,upgradeButton,reportButton,emploButton,manageButton;
	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Upgrade frist = new Upgrade(10, LocalDate.of(2011, 9, 21), "الاولى",10000);
		Upgrade second = new Upgrade(20, LocalDate.of(2018, 10, 25), "التانية",9000);
		Upgrade third = new Upgrade(30, LocalDate.of(2015, 10, 11), "الثالثة",8000);
		Upgrade fourth = new Upgrade(40, LocalDate.of(2012, 8, 05), "الرابعة",7000);
		Upgrade fiveth = new Upgrade(50, LocalDate.of(2011, 6, 23), "الخامسة",6000);
		Upgrade sixth = new Upgrade(60, LocalDate.of(2011, 11, 18), "السادسة",5000);
		Upgrade seventh = new Upgrade(70, LocalDate.of(2010, 10, 10), "السابعة",4000);
		Upgrade eight = new Upgrade(80, LocalDate.of(2009, 11, 07), "الثامنة",3000);
		Upgrade nineth = new Upgrade(90, LocalDate.of(2017, 6, 17), "التاسعة",2000);
		Jops manager = new Jops(1, "مدير");
		Jops secManager = new Jops(2,"نائب مدير");
		Jops searcher = new Jops(3,"مفتش حاسوب");
		Jops comHelper = new Jops(4,"مساعد حاسوب");
		Jops superviser = new Jops(5,"مشرف");
		Jops superHelper = new Jops(6,"مساعد مشرف");
		Jops compTechno = new Jops(7,"تقني حاسوب");
		Jops counter = new Jops(8,"محاسب");
		Jops moraje3 =  new Jops(9,"مراجع");
		Jops motahsel = new Jops(10,"متحصل");
		Jops inner9 = new Jops(11,"مراجع داخلي");
		Jops general9 = new Jops(12,"مراجع عام");
		Jops general3 = new Jops(13,"مفتش عام");
		
		Managements infoCenter = new Managements(100, "مركز المعلومات");
		infoCenter.addManageJop(manager);
		infoCenter.addManageJop(secManager);
		infoCenter.addManageJop(searcher);
		infoCenter.addManageJop(comHelper);
		infoCenter.addManageJop(superviser);
		infoCenter.addManageJop(superHelper);
		
		Managements eradat = new Managements(110, "الإيرادات");
		eradat.addManageJop(manager);
		eradat.addManageJop(secManager);
		eradat.addManageJop(moraje3);
		eradat.addManageJop(searcher);
		
		Managements mahaseel = new Managements(120, "المحاصيل");
		mahaseel.addManageJop(manager);
		mahaseel.addManageJop(secManager);
		mahaseel.addManageJop(compTechno);
		mahaseel.addManageJop(counter);
		mahaseel.addManageJop(moraje3);
		mahaseel.addManageJop(motahsel);
		
		Managements hasibat = new Managements(130, "الحاسبات");
		hasibat.addManageJop(manager);
		hasibat.addManageJop(secManager);
		hasibat.addManageJop(inner9);
		hasibat.addManageJop(general9);
		hasibat.addManageJop(general3);
		hasibat.addManageJop(counter);

		
		new Employees(10, "Elaf", eight.getSalary(),eight, eradat,
						eradat.getManageJopList().get(1),"0123456789",LocalDate.of(2015, 05, 21)).setEmpHoliday(new Holiday(50, "Travel", 10,10));
		new Employees(11, "Mayada", second.getSalary(),second, hasibat,
						hasibat.getManageJopList().get(1),"0967045843",LocalDate.of(2015, 06, 12));
		new Employees(12, "Manal", frist.getSalary(),frist, hasibat,
						hasibat.getManageJopList().get(0),"0118285431",LocalDate.of(2015, 12, 15));
		new Employees(13, "Ibrahim",fiveth.getSalary(),fiveth, infoCenter,
						infoCenter.getManageJopList().get(2),"0965173770",LocalDate.of(2011, 05, 10));
		new Employees(14, "Musa", fourth.getSalary(),fourth, infoCenter,
						infoCenter.getManageJopList().get(3),"0118254421",LocalDate.of(2009, 04, 22));
		new Employees(15, "Mohammed", sixth.getSalary(),sixth, eradat,
						eradat.getManageJopList().get(1),"0955546254",LocalDate.of(2005, 11, 02));
		new Employees(16, "Malak", fourth.getSalary(),fourth, mahaseel,
						mahaseel.getManageJopList().get(3),"0987654321",LocalDate.of(2011, 02, 25));
		new Employees(17, "Ahmed", nineth.getSalary(),nineth, infoCenter,
						infoCenter.getManageJopList().get(0),"09365214789",LocalDate.of(2017, 12, 11));
		new Employees(18, "Ali", third.getSalary(),third, mahaseel,
						mahaseel.getManageJopList().get(2),"0125436987",LocalDate.of(2012, 01, 23));
		new Employees(19, "Maab", seventh.getSalary(),seventh, mahaseel,
						mahaseel.getManageJopList().get(3),"0922445588",LocalDate.of(2018, 04, 25));
	}
    @FXML
    private void logoutAction(ActionEvent event) {
    	
    	Parent show;
		try {
			Stage stage = new Stage();
			show = FXMLLoader.load(getClass().getResource("/application/fxml/loginPage.fxml"));
			menuEmpButton.getScene().getWindow().hide();
			Main.Visible(stage, show);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void mainMenuActions(ActionEvent event) {
    	if(event.getSource() == menuEmpButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/EmpMenu.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	if(event.getSource() == holiButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/Holidays.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	if(event.getSource() == upgradeButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/Upgrading.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	if(event.getSource() == emploButton) {
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
    	if(event.getSource() == reportButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/Reports.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	if(event.getSource() == manageButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/AddManagement.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	if(event.getSource() == endButton) {
        	Parent show;
    		try {
    			Stage stage = new Stage();
    			stage.initModality(Modality.APPLICATION_MODAL);
    			show = FXMLLoader.load(getClass().getResource("/application/fxml/endServ.fxml"));
    			Main.Visible(stage, show);
    			stage.showAndWait();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    }
}
