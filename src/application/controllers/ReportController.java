package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class ReportController implements Initializable{
    
    @FXML
    private ComboBox<Managements> manBox;
    @FXML
    private JFXRadioButton partCheck,allCheck;
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	manBox.setItems(Managements.manageList);
		
	}
	@FXML
    private void allManageAction(ActionEvent event) {
		manBox.getSelectionModel().select(-1);
		manBox.setDisable(true);
    }

    @FXML
    private void partManageAction(ActionEvent event) {
    	manBox.setDisable(false);
    }

}
