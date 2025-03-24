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

public class sellerMain_Controller{
	
	@FXML
    private Button listProductButton;
	
	@FXML
    private Button cancellationRequestButton;
	
	@FXML
	private Button backButton;
	
	private String username;
	
	@FXML
	private Button acceptOrderButton;
	
	@FXML
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	public void handleListProductButton(ActionEvent event) throws Exception{
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerListProduct.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setTitle("List Product");
	        stage.show();
	        sellerListProduct_Controller controller = loader.getController();
  	        controller.setUsername(username);
	}

	@FXML
	public void handleCancellationRequestButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerApproveCancellationRequest.fxml"));
		Parent root = loader.load();
        
		sellerApproveCancellationRequest_Controller controller = loader.getController();
	    controller.setUsername(username);
	    
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Acceptorder");
        stage.show();
		return;
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
	
	
	public void handleAcceptOrderButton(ActionEvent event) throws Exception {
		
	    
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerAcceptOrder.fxml"));
		
		Parent root = loader.load();
        
		sellerAcceptOrder_Controller controller = loader.getController();
	    controller.setUsername(username);
	    
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Acceptorder");
        stage.show();
       
	}
}
