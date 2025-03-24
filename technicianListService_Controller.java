package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class technicianListService_Controller{
	
	public String username;
	
	
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	private ComboBox<String> nameComboBox;
	
	@FXML
	private TextField priceField;
	
	@FXML
	private TextField descriptionfield;
	@FXML
	private Label errorMessage;
	
	String name1;
	int Price1;
	String description1;
	public void initialize() {
		nameComboBox.getItems().addAll("Smartphone Repair", "Laptop Repair" , "Tablet Repair" , "Oil Change" , "Brake Repair");        
    }
	
	@FXML
	public void handlelistserviceButtonclick() {
		listservice();
	}
	
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("technicianMain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Technician-BuyTopia");
        stage.show();
	}
	
	public void listservice() {
		 errorMessage.setVisible(false);

		    if (nameComboBox.getValue() == null || nameComboBox.getValue().isEmpty()) {
		        errorMessage.setText("Service name must be selected.");
		        errorMessage.setVisible(true);
		        return;
		    }

		    String priceText = priceField.getText();
		    if (priceText.isEmpty()) {
		        errorMessage.setText("Price must not be empty.");
		        errorMessage.setVisible(true);
		        return;
		    }
		    try {
		        Integer.parseInt(priceText); 
		    } catch (NumberFormatException e) {
		        errorMessage.setText("Price must be a valid integer.");
		        errorMessage.setVisible(true);
		        return;
		    }
		    if (descriptionfield.getText().isEmpty()) {
		        errorMessage.setText("Description must not be empty.");
		        errorMessage.setVisible(true);
		        return;
		    }
		    Main.System.addService(nameComboBox.getValue(),Integer.parseInt(priceText), descriptionfield.getText(), username);
		    errorMessage.setText("Service listed successfully!");
		    errorMessage.setVisible(true);
	}
	public void enterdetails(String name , int Price , String description) {
		this.name1 = name;
		this.Price1 = Price;
		this.description1 = description;
		Main.System.validate(name, name, description);
	}

}