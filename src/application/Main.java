package application;

import org.controlsfx.control.Notifications;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/fxml/LoginPage.fxml"));
			Visible(primaryStage, root);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void justANumberVaild(TextField text) {
    	text.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					if(!isNumber(text)) {
						text.clear();
						dialogInfo("Error", "Just A Number").showError();
					}
				}
			}

			private boolean isNumber(TextField text) {
				try {
					if (text.getText().equals(""))
						return true;	
					else {
						Integer.parseInt(text.getText());
						return true;
					}
				} catch (NumberFormatException e) {
					return false;
				}
			}
		});
	}
	
	public static <T> void getCellSearch(TableView<T> table,ObservableList<T> list,TextField search) {
    	search.textProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(search.textProperty().get().isEmpty()) {
					table.setItems(list);
					return;
				}
				
				ObservableList<T> tableItems = FXCollections.observableArrayList();
				ObservableList<TableColumn<T, ?>> cols = table.getColumns();
				
				for(int i=0; i<list.size(); i++) {
					for(int j=0; j<cols.size(); j++) {
						String cellValue = cols.get(j).getCellData(list.get(i)).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(search.getText().toLowerCase()) && 
								cellValue.startsWith(search.getText().toLowerCase())) {
							tableItems.add(list.get(i));
							break;
						}
					}
				}
				table.setItems(tableItems);
			}
		});
}
	
	public static void Visible(Stage stage,Parent root) {
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
	}
	public static Notifications dialogInfo(String header,String message) {
		Notifications notific = Notifications.create();
		notific.title(header);
		notific.text(message);
		notific.hideAfter(Duration.seconds(5));
		notific.position(Pos.BOTTOM_RIGHT);
		notific.darkStyle();
		return notific;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
