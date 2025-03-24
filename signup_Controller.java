package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class signup_Controller {

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
    
   
    String username1;
    String password1;
    String role1;
    
    @FXML
    private Label signupPageerrorlabel ;
    
    public void initialize() {
        roleComboBox.getItems().addAll("Customer", "Seller", "Technician");        
    }
    
    
    @FXML
    public void handleloginButtonClick(ActionEvent event) throws Exception {
        System.out.println("login");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login to BuyTopia");
        stage.show();
        
    }
    
    @FXML
    public void handlesignUpButtonClick() {
        System.out.println("signup"); 
        
        
        String username = usernameText.getText().trim();
        String password = passwordText.getText().trim();
        String role = roleComboBox.getValue();
        
        
        if (username.isEmpty() || password.isEmpty() || roleComboBox == null) {
        	 signupPageerrorlabel.setText("Username and Password cannot be empty!");
        	 signupPageerrorlabel.setVisible(true);
        }
        else {
        	 if(Main.System.isUsernamePresent(username)) {
        		 signupPageerrorlabel.setText("Username already Exsists!");
            	 signupPageerrorlabel.setVisible(true);
        		 return;
        	 }
        	 
        	 submitRegistrationForm(role, username , password);
        	 signupPageerrorlabel.setVisible(false);
             usernameText.clear();
             passwordText.clear();
             roleComboBox.setValue(null); 
        }
        
        
    }
    public void submitRegistrationForm(String role , String username, String password) {
    	Main.System.addUser(role, username, password);
    }
    public void enterdetails(String a , String b, String c) {
    	 username1 = a;
         password1 = b;
         role1 = c;
         return;
    }
    
    
    
}



