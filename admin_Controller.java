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

public class admin_Controller{
	@FXML
    private Button approveButton;
	
	
	@FXML
	private ListView<String> list; 

	public void initialize() {
	    List<String> pendingUsernames = new ArrayList<>();
	    for (Seller seller : Main.System.sellers) {
	        if (!seller.approved) {
	            pendingUsernames.add(seller.getUsername());
	        }
	    }

	    for (Technician technician : Main.System.technicians) {
	        if (!technician.approved) {
	            pendingUsernames.add(technician.getUsername());
	        }
	    }
	    if (pendingUsernames.isEmpty()) {
	        list.getItems().add("No pending approvals.");
	    } else {
	        list.getItems().addAll(pendingUsernames);
	    }
	}


	
	@FXML
	public void handleapproveButtonClick() {
		acceptRequest("BuyTopia");
	}
	
	public void acceptRequest(String username1) {
		String selectedUsername = list.getSelectionModel().getSelectedItem();
	    if (selectedUsername == null || selectedUsername.equals("No pending approvals.")) {
	        System.out.println("No user selected or no pending approvals.");
	        return;
	    }
	    boolean userFound = false;
	    for (Seller seller : Main.System.sellers) {
	        if (seller.getUsername().equals(selectedUsername) && !seller.approved) {
	            seller.approved = true;
	            userFound = true;
	            break;
	        }
	    }
	    for (Technician technician : Main.System.technicians) {
	        if (technician.getUsername().equals(selectedUsername) && !technician.approved) {
	            technician.approved = true;
	            userFound = true;
	            break;
	        }
	    }
	    if (userFound) {
	        System.out.println("User " + selectedUsername + " approved successfully.");
	        list.getItems().remove(selectedUsername); 
	        if (list.getItems().isEmpty()) {
	            list.getItems().add("No pending approvals.");
	        }
	    } else {
	        System.out.println("User not found or already approved.");
	    }
	}

}