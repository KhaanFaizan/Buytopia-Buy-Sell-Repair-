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

public class customerRateService_Controller{
	
	public String username;
	
	@FXML
	private Button rateButton;
	
	@FXML
	private Button backButton;
	@FXML
	private ComboBox<String> ratingComboBox;
	
	@FXML
	private ListView<String> list; 
	@FXML
	private Label errorMessage;
	
    @FXML
    public void setUsername(String username) {
        this.username = username;
        lazyinitialize();
        
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
    public void lazyinitialize() {
        ratingComboBox.getItems().clear();
        ratingComboBox.getItems().addAll("1.0", "2.0", "3.0", "4.0", "5.0");
        list.getItems().clear();
        boolean hasServices = false;
        for (ServiceTransaction transaction : Main.System.serviceTransactions) {
            if (transaction.customerName.equals(username) && transaction.approved) {
                String item = "Service ID: " + transaction.serviceId +
                              ", Technician: " + transaction.technicianName +
                              ", Rating: " + (transaction.rating == 0.0 ? "Not Rated" : transaction.rating);
                list.getItems().add(item);
                hasServices = true;
            }
        }
        if (!hasServices) {
            list.getItems().add("No services available for rating.");
        }
    }

    @FXML
    public void handlerateButtonClick() {
    	 select();
    }
    public void select() {
    	String selectedService = list.getSelectionModel().getSelectedItem();
        if (selectedService == null || selectedService.equals("No services available for rating.")) {
            errorMessage.setText("Please select a service to rate.");
            return;
        }

        String selectedRating = ratingComboBox.getValue();
        if (selectedRating == null) {
            errorMessage.setText("Please select a rating.");
            return;
        }

        String[] serviceDetails = selectedService.split(", ");
        int serviceId = Integer.parseInt(serviceDetails[0].split(": ")[1]);
        String technicianName = serviceDetails[1].split(": ")[1];
        //Main.System.updateServiceRating(username, serviceId, technicianName);
        boolean updated = false;
        for (ServiceTransaction transaction : Main.System.serviceTransactions) {
            if (transaction.serviceId == serviceId 
                    && transaction.customerName.equals(username) 
                    && transaction.technicianName.equals(technicianName)) {
                transaction.rating = Double.parseDouble(selectedRating); // Update the rating
                updated = true;
                break;
            }
        }

        if (updated) {
            errorMessage.setText("Rating updated successfully!");
            list.getItems().remove(selectedService); // Remove the rated service from the list
        } else {
            errorMessage.setText("Failed to update the rating. Please try again.");
        }
    }

}