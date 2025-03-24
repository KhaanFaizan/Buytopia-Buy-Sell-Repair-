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
import javafx.stage.Stage;

public class login_Controller{
	 	@FXML
	    private Button loginButton;  
	    
	    @FXML
	    private Button signUpButton;
	    
	    @FXML
	    private TextField usernameText;
	    
	    @FXML
	    private PasswordField passwordText;
	    
	    @FXML
	    private ComboBox<String> roleComboBox;
	    
	    
	    @FXML
	    private Label loginPageerrorlabel ;
	    
	    public void initialize() {
	        roleComboBox.getItems().addAll("Customer", "Seller", "Technician", "Admin");        
	    }
	    
	    public void handleloginButtonClick(ActionEvent event) throws Exception {
	        System.out.println("login");
	        
	        String username = usernameText.getText().trim();
	        String password = passwordText.getText().trim();
	        String role = roleComboBox.getValue();
	        
	        if (username.isEmpty() || password.isEmpty() || roleComboBox == null) {
	        	loginPageerrorlabel.setText("Username and Password cannot be empty!");
	        	loginPageerrorlabel.setVisible(true);
	        }
	        else {
	        	
	        	if(role.contentEquals("Admin") && username.contentEquals("BuyTopia") && password.contentEquals("12345678")) {
	        	System.out.print("Admin");
	        		
	        	 FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
	   	         Parent root = loader.load();
	   	         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	   	         Scene scene = new Scene(root);
	   	         stage.setScene(scene);
	   	         stage.setTitle("Admin-BuyTopia");
	   	         stage.show();
	   	         
	   	       //  Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	   	        // currentStage.close();
	        	
	        	}
	        	else if(Main.System.isUserPresent(role, username, password)) {
	        		System.out.print("Login Successfully!");
	        		loginPageerrorlabel.setVisible(false);
	        		
	        		
	        		if(role.contentEquals("Seller")) {
	        			 FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerMain.fxml"));
	    	   	         Parent root = loader.load();
	    	   	         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	    	   	         Scene scene = new Scene(root);
	    	   	         stage.setScene(scene);
	    	   	         stage.setTitle("Seller-BuyTopia");
	    	   	         stage.show();
	    	   	         sellerMain_Controller controller = loader.getController();
	    	   	         controller.setUsername(username);
	        		}
	        		else if(role.contentEquals("Technician")) {
	        			 FXMLLoader loader = new FXMLLoader(getClass().getResource("technicianMain.fxml"));
	    	   	         Parent root = loader.load();
	    	   	         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	    	   	         Scene scene = new Scene(root);
	    	   	         stage.setScene(scene);
	    	   	         stage.setTitle("Technician-BuyTopia");
	    	   	         stage.show();
	    	   	         technicianMain_Controller controller = loader.getController();
	    	   	         controller.setUsername(username);
	        		}
	        		else if(role.contentEquals("Customer")) {
	        			 FXMLLoader loader = new FXMLLoader(getClass().getResource("customerMain.fxml"));
	    	   	         Parent root = loader.load();
	    	   	         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	    	   	         Scene scene = new Scene(root);
	    	   	         stage.setScene(scene);
	    	   	         stage.setTitle("Customer-BuyTopia");
	    	   	         stage.show();
	    	   	         customerMain_Controller controller = loader.getController();
	    	   	         controller.setUsername(username);
	        		}
	        	}
	        	else {
	        		loginPageerrorlabel.setText("Username or Password incorrect!");
		        	loginPageerrorlabel.setVisible(true);
	        	}
	        	
	        }
	        usernameText.clear();
            passwordText.clear();
            roleComboBox.setValue(null);
	    }
	    
	    @FXML
	    public void handlesignUpButtonClick(ActionEvent event) throws Exception {
	    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp1.fxml"));
	         Parent root = loader.load();
	         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	         Scene scene = new Scene(root);
	         stage.setScene(scene);
	         stage.setTitle("Sign-Up to BuyTopia");
	         stage.show();
	         
	         //Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	        // currentStage.close();
	    }
}