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

public class customerHireTechnician_Controller{
	
	@FXML
	private ListView<String> list; 
	
	@FXML
	private Button hireButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorMessage;
	
	public String username;
	
	@FXML
    public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerMain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Customer-BuyTopia");
        stage.show();
	}
	
	@FXML
	public void initialize() {
	    list.getItems().clear();
	    if (Main.System.services.isEmpty()) {
	        list.getItems().add("No services available.");
	    } else {
	        for (Service service : Main.System.services) {
	            String serviceDetails = "Service ID: " + service.serviceId 
	                    + ", Name: " + service.getName() 
	                    + ", Technician: " + service.technicianName 
	                    + ", Price: $" + service.getPrice() 
	                    + ", Description: " + service.getDescription();
	            list.getItems().add(serviceDetails);
	        }
	    }
	}

	
	@FXML
	public void handlehireButtonClick() {
			selectTechnician(username);
	}
	
	public void selectTechnician(String username1) {
		String selectedService = list.getSelectionModel().getSelectedItem();
	    if (selectedService == null || selectedService.equals("No services available.")) {
	        errorMessage.setText("Please select a valid service to hire.");
	        return;
	    }

	    String[] serviceDetails = selectedService.split(", ");
	    int serviceId = Integer.parseInt(serviceDetails[0].split(": ")[1]);
	    String technicianName = serviceDetails[2].split(": ")[1];
	    //Main.System.addServiceTransaction(technicianName, username, serviceId);
	    ServiceTransaction transaction = new ServiceTransaction(technicianName, username, serviceId);
	    Main.System.serviceTransactions.add(transaction);

	    errorMessage.setText("Technician successfully hired!");
	    list.getItems().remove(selectedService); // Remove the service from the list
	
	}

}