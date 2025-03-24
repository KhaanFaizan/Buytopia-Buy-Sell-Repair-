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

public class sellerApproveCancellationRequest_Controller{
	
	
	@FXML
	private ListView<String> list;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button acceptButton;
	
	@FXML
	private Label errorMessage;
	
	public String username;
	
	@FXML
	public void initialize() {
	    list.getItems().clear();
	    list.getItems().add("Loading orders...");
	}

	public void setUsername(String username) {
	    this.username = username;
	    list.getItems().clear();
	    if (Main.System.productTransactions.isEmpty()) {
	        list.getItems().add("No cancel orders available for approval.");
	        return;
	    }

	    boolean hasPendingOrders = false;
	    for (ProductTransaction transaction : Main.System.productTransactions) {
	        if (transaction.sellerName != null && transaction.sellerName.contentEquals(username) && transaction.userCancelled) {
	            hasPendingOrders = true;
	            String listItem = "ID: " + transaction.productId + 
	                              ", Customer: " + transaction.customerName + 
	                              ", Approved: " + transaction.approved;
	            list.getItems().add(listItem);
	        }
	    }

	    if (!hasPendingOrders) {
	        list.getItems().add("No cancel orders available for approval.");
	    }
	}
	@FXML
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerMain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Seller-BuyTopia");
        stage.show();
	}
	
	
	public void handleacceptButtonClick() {
		acceptRequest(0);
	}
	public void acceptRequest(int orderId1) {
		errorMessage.setVisible(false);

	    String selectedItem = list.getSelectionModel().getSelectedItem();

	    if (selectedItem == null || selectedItem.equals("No cancel orders available for approval.")) {
	        errorMessage.setText("Please select a valid transaction to approve.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    try {
	        String[] parts = selectedItem.split(","); 
	        String idPart = parts[0]; 
	        int productId = Integer.parseInt(idPart.split(":")[1].trim()); 
	       // Main.System.selleracceptCancellationRequest(selectedItem, idPart, productId);
	        for (ProductTransaction transaction : Main.System.productTransactions) {
	            if (transaction.productId == productId && transaction.sellerName.equals(username)) {
	                transaction.adminCancelledApproved = true;
	                list.getItems().remove(selectedItem);
	                if (list.getItems().isEmpty()) {
	                    list.getItems().add("No cancel orders available for approval.");
	                }
	                errorMessage.setText("Transaction approved successfully!");
	                errorMessage.setVisible(true);
	                return;
	            }
	        }
	        errorMessage.setText("Transaction could not be found. Please try again.");
	        errorMessage.setVisible(true);
	    } catch (Exception e) {
	        errorMessage.setText("An error occurred while processing the transaction.");
	        errorMessage.setVisible(true);
	    }
	}
}

