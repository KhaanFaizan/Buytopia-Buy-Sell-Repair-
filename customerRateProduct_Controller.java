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

public class customerRateProduct_Controller{
	
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
    public void setUsername(String username) {
        this.username = username;
        lazyInitialize(); 
    }

    private void lazyInitialize() {
        
        ratingComboBox.getItems().clear();
        for (double i = 1.0; i <= 5.0; i++) {
            ratingComboBox.getItems().add(String.format("%.1f", i));
        }
        list.getItems().clear();
        if (Main.System.productTransactions.isEmpty()) {
            list.getItems().add("No products available for rating.");
            return;
        }

        boolean hasProductsToRate = false;
        for (ProductTransaction transaction : Main.System.productTransactions) {
            if (transaction.customerName != null 
                    && transaction.customerName.equals(username) 
                    && transaction.approved 
                    && transaction.rating == 0.0) {
                hasProductsToRate = true;
                String listItem = "ID: " + transaction.productId + ", Seller: " + transaction.sellerName;
                list.getItems().add(listItem);
            }
        }

        if (!hasProductsToRate) {
            list.getItems().add("No products available for rating.");
        }
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
	private Label errorMessage;

	@FXML
	public void handlerateButtonClick() {
		select();
	}
	
	public void select() {
		errorMessage.setText("");

	    // Check if a product is selected
	    String selectedProduct = list.getSelectionModel().getSelectedItem();
	    if (selectedProduct == null || selectedProduct.equals("No products available for rating.")) {
	        errorMessage.setText("Please select a product to rate.");
	        return;
	    }

	    // Check if a rating is selected
	    String selectedRating = ratingComboBox.getValue();
	    if (selectedRating == null) {
	        errorMessage.setText("Please select a rating.");
	        return;
	    }

	    // Parse selected product details
	    String[] productDetails = selectedProduct.split(", ");
	    int productId = Integer.parseInt(productDetails[0].split(": ")[1]);
	    String sellerName = productDetails[1].split(": ")[1];
	   // Main.System.updateRating(sellerName, productId, sellerName);
	    boolean updated = false;
	    for (ProductTransaction transaction : Main.System.productTransactions) {
	        if (transaction.productId == productId 
	                && transaction.customerName.equals(username) 
	                && transaction.sellerName.equals(sellerName)) {
	            transaction.rating = Double.parseDouble(selectedRating); // Update rating
	            updated = true;
	            break;
	        }
	    }
	    if (updated) {
	        errorMessage.setText("Rating updated successfully!");
	        list.getItems().remove(selectedProduct); // Remove the product from the list
	    } else {
	        errorMessage.setText("Unable to update rating. Please try again.");
	    }
	}

}