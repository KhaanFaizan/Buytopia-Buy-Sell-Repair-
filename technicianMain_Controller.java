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

public class technicianMain_Controller{
	
	public String username;
	
	@FXML
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
    private Button listServiceButton;
	
	@FXML
    private Button serviceRequestButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	public void handleListServiceButton(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("technicianListService.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ListService-BuyTopia");
        stage.show();
        technicianListService_Controller controller = loader.getController();
	    controller.setUsername(username);
        
	
	}
	
	@FXML
	public void handleServiceRequestButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("technicianApproveServiceRequest.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Approve Service Request-BuyTopia");
        stage.show();
        technicianApproveServiceRequest_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
	@FXML
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login-BuyTopia");
        stage.show();
	}
	
}