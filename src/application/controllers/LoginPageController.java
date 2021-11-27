package application.controllers;

import java.io.IOException;

import org.controlsfx.control.Notifications;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passwordText;
    

    @FXML
    private void closeAction(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }

    @FXML
    private void loginActionEvent(ActionEvent event) {
    	String us = userText.getText();
    	String pass = passwordText.getText();
    	if(us.matches("[Hh][Rr]")&&pass.matches("1234")) {
    		Parent show;
			try {
				Stage stage = new Stage();
				show = FXMLLoader.load(getClass().getResource("/application/fxml/MainPage.fxml"));
				userText.getScene().getWindow().hide();
				Main.Visible(stage, show);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			userText.clear();
			passwordText.clear();
    	}else {
    		Notifications notific = Main.dialogInfo("Error Message", "User name or Password is wrong");
    		notific.showError();
    	}
    }
}
