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

public class technicianApproveServiceRequest_Controller{
	@FXML
	private Button acceptButton;
	
	@FXML
	private ListView<String> list; 
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorMessage;
	
	public String username;
	
	@FXML
    public void setUsername(String username) {
        this.username = username;
        initializeList();
    }
	
	@FXML
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("technicianMain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Customer-BuyTopia");
        stage.show();
	}
	
	@FXML
	public void initializeList() {
	    list.getItems().clear();
	    boolean found = false;
	    for (ServiceTransaction transaction : Main.System.serviceTransactions) {
	        if (transaction.technicianName.equals(username) && !transaction.approved) {
	            String displayText = "Service ID: " + transaction.serviceId
	                    + ", Customer: " + transaction.customerName
	                    + ", Approved: " + transaction.approved
	                    + ", Rating: " + transaction.rating;
	            list.getItems().add(displayText);
	            found = true;
	        }
	    }
	    if (!found) {
	        list.getItems().add("No service requests available.");
	    }
	}

	
	@FXML
	public void handleacceptButtonclick() {
		approveRequest(0);
	}
	
	public void approveRequest(int requestId1) {
		String selectedRequest = list.getSelectionModel().getSelectedItem();
	    if (selectedRequest == null || selectedRequest.equals("No service requests available.")) {
	        errorMessage.setText("Please select a valid service request to approve.");
	        return;
	    }
	    String[] requestDetails = selectedRequest.split(", ");
	    int serviceId = Integer.parseInt(requestDetails[0].split(": ")[1]);
	    boolean updated = false;
	    for (ServiceTransaction transaction : Main.System.serviceTransactions) {
	        if (transaction.technicianName.equals(username) && transaction.serviceId == serviceId) {
	            transaction.approved = true; 
	            updated = true;
	            break;
	        }
	    }
	    if (updated) {
	        list.getItems().remove(selectedRequest);
	        errorMessage.setText("Service request approved successfully.");
	    } else {
	        errorMessage.setText("Unable to approve the service request. Please try again.");
	    }
	}
	
}