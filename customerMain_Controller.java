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

public class customerMain_Controller{
	
	public String username;
	
	@FXML
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	private Button buyProductButton;
	
	@FXML
	private Button rateProductButton;
	
	@FXML
	private Button hireTechnicianButton;
	
	@FXML
	private Button rateTechnicianButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button cancelOrderButton;
	
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
	
	public void handlebuyProductButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerBuyProduct.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BuyProduct-BuyTopia");
        stage.show();
        customerBuyProduct_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
	public void handlerateProductButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRateProduct.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("RateProduct-BuyTopia");
        stage.show();
        customerRateProduct_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
	
	public void handlehireTechnicianButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerHireTechnician.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HireTechnician-BuyTopia");
        stage.show();
        customerHireTechnician_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
	public void handlerateTechnicianButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerRateService.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("RateService-BuyTopia");
        stage.show();
        customerRateService_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
	public void handlecancelOrderButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("customerCancelOrder.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CancelOrder-BuyTopia");
        stage.show();
        customercancelOrder_Controller controller = loader.getController();
	    controller.setUsername(username);
	}
	
}