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

public class sellerListProduct_Controller{
	
	private String username;
	
	@FXML
	public void setUsername(String username) {
        this.username = username;
    }
	
	@FXML
	private TextField nameField;
	
	@FXML
	private ComboBox<String> typeComboBox;
    
	@FXML
	private TextField itemsField;
	
	@FXML
	private TextField priceField;
	
	@FXML
	private TextField descriptionField;
	
	@FXML
	private Button listProductButton;
	
	@FXML
	private Label errorMessage;
	
	@FXML
	private Button backButton;
	
	
	public void initialize() {
		typeComboBox.getItems().addAll("Electronics", "SparePart");        
    }
	
	@FXML
	public void handleListProductButton() {
		listProduct();
	}
	
	public void handleBackButton(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sellerMain.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Seller-BuyTopia");
        stage.show();
	}
	String name1;
	int Price1;
	String description1;
	public void enterdetails(String name , int Price , String description) {
		this.name1 = name;
		this.Price1 = Price;
		this.description1 = description;
		Main.System.validate(name, name, description);
	}
	
	public void listProduct() {
		errorMessage.setVisible(false);

	    String name = nameField.getText().trim();
	    if (name.isEmpty()) {
	        errorMessage.setText("Name cannot be empty.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    String type = typeComboBox.getValue();
	    if (type == null || type.isEmpty()) {
	        errorMessage.setText("Please select a product type.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    String itemsText = itemsField.getText().trim();
	    int items;
	    try {
	        items = Integer.parseInt(itemsText);
	        if (items <= 0) {
	            throw new NumberFormatException();
	        }
	    } catch (NumberFormatException e) {
	        errorMessage.setText("Items must be a positive integer.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    String priceText = priceField.getText().trim();
	    int price;
	    try {
	        price = Integer.parseInt(priceText);
	        if (price <= 0) {
	            throw new NumberFormatException();
	        }
	    } catch (NumberFormatException e) {
	        errorMessage.setText("Price must be a positive integer.");
	        errorMessage.setVisible(true);
	        return;
	    }
	    String description = descriptionField.getText().trim();
	    if (description.isEmpty()) {
	        errorMessage.setText("Description cannot be empty.");
	        errorMessage.setVisible(true);
	        return;
	    }

	    System.out.println("Product Listed Successfully!");
	    System.out.println("Name: " + name);
	    System.out.println("Type: " + type);
	    System.out.println("Items: " + items);
	    System.out.println("Price: " + price);
	    System.out.println("Description: " + description);

	    nameField.clear();
	    typeComboBox.setValue(null);
	    itemsField.clear();
	    priceField.clear();
	    descriptionField.clear();

	    errorMessage.setText("Product listed successfully!");
	    errorMessage.setVisible(true);
	    
	    Main.System.addProduct(name, type, items, price, description, username);
	
	}
	
}
