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

public class customerBuyProduct_Controller{
	
	public String username;
	
	@FXML
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	private ListView<String> list; 
	
	@FXML
	private Label errorMessage;
	@FXML
	public void initialize() {
	    if (Main.System.products.isEmpty()) {
	        list.getItems().add("No products available at the moment.");
	    } 
	    else {
	        for (Product product : Main.System.products) {
	            String productDetails = "ID: " + product.productId + 
	                                    ", Name: " + product.getName() + 
	                                    ", Price: $" + product.getPrice() + 
	                                    ", Type: " + product.getType() + 
	                                    ", Seller: " + product.sellerName;
	            list.getItems().add(productDetails);
	        }
	    }
	}

	
	@FXML
	private Button backButton;
	@FXML
	public void handleBackButton(ActionEvent event) throws Exception {
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
	
	@FXML
	private Button buyButton;
	
	@FXML
	public void handlebuyButtonclick(ActionEvent event) throws Exception {
		    selectProduct(0);
	}


	public void selectProduct(int productId1) {
		errorMessage.setVisible(false);

	    String selectedItem = list.getSelectionModel().getSelectedItem();
	    if (selectedItem == null || selectedItem.equals("No products available at the moment.")) {
	        errorMessage.setText("Please select a product to proceed.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    try {
	        
	        String[] parts = selectedItem.split(","); 

	        String idPart = parts[0]; 
	        int productId = Integer.parseInt(idPart.split(":")[1].trim()); 

	        String sellerPart = parts[4];
	        String sellerName = sellerPart.split(":")[1].trim();  


	        System.out.println("Product ID " + productId + " has been selected by " + username);
	        System.out.println("Seller Name: " + sellerName);

	        errorMessage.setText("Product purchased successfully from " + sellerName + "!");
	        errorMessage.setVisible(true);
	       // Main.System.addProductTransaction(sellerName, username,productId);
	        Main.System.productTransactions.add(new ProductTransaction(sellerName , username , productId ));
	        System.out.print(Main.System.productTransactions.size());
	        System.out.print(username);
	        
	    } catch (Exception e) {
	        errorMessage.setText("Error processing your selection. Please try again.");
	        errorMessage.setVisible(true);
	    }
	}
	
	
}