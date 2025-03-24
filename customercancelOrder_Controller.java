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

public class customercancelOrder_Controller {

	
	@FXML
	private Button rateButton;
	
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
	}
    
	
	@FXML
	private ListView<String> list; 
	
	@FXML
	private Label errorMessage;
	
	public String username;
    @FXML
    public void setUsername(String username) {
        this.username = username;
        lazyInitialize();
    }
    
    @FXML
    public void lazyInitialize() {
        list.getItems().clear();
        boolean hasPendingOrders = false;
        for (ProductTransaction transaction : Main.System.productTransactions) {
            if (transaction.customerName.contentEquals(username) && !transaction.approved) {
                String productDetails = "Product ID: " + transaction.productId 
                        + ", Seller: " + transaction.sellerName;
                list.getItems().add(productDetails);
                hasPendingOrders = true;
            }
        }
        if (!hasPendingOrders) {
            list.getItems().add("No pending orders available.");
        }
    }
    
    @FXML
    public void handlecancelButtonClick() {
    	cancelOrder(0);
    }

    public void cancelOrder(int orderId) {
    	String selectedProduct = list.getSelectionModel().getSelectedItem();
        if (selectedProduct == null || selectedProduct.equals("No pending orders available.")) {
            errorMessage.setText("Please select an order to cancel.");
            return;
        }
        String[] productDetails = selectedProduct.split(", ");
        int productId = Integer.parseInt(productDetails[0].split(": ")[1]);
        String sellerName = productDetails[1].split(": ")[1];
       // Main.System.cancelOrder(username, sellerName, productId);
        boolean cancelled = false;
        for (ProductTransaction transaction : Main.System.productTransactions) {
            if (transaction.productId == productId 
                    && transaction.customerName.equals(username) 
                    && transaction.sellerName.equals(sellerName)) {
                transaction.userCancelled = true;
                cancelled = true;
                break;
            }
        }

        if (cancelled) {
            errorMessage.setText("Order cancelled successfully.");
            list.getItems().remove(selectedProduct);
        } else {
            errorMessage.setText("Unable to cancel the order. Please try again.");
        }
    }
}