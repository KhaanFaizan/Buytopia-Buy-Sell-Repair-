package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class system{
	
	 int productId;
	 int serviceId;
	 
	 private static final String URL = "jdbc:mysql://localhost:3306/BuyTopiaDataBase";
	 private static final String USER = "root";
	 private static final String PASSWORD = "salmansaleem123";
	    
	 system(){
		 productId=getMaxProductId();
		 serviceId=getMaxServiceId();
		 loadAllData();
	 }
	 
	 public List<Customer> customers = new ArrayList<>();
	 public List<Seller> sellers = new ArrayList<>();
	 public List<Technician> technicians = new ArrayList<>();
	 public Admin admin= new Admin("BuyTopia", "12345678");
	 public List<Product> products = new ArrayList<>();
	 public List<Service> services = new ArrayList<>();
	 public List<ProductTransaction> productTransactions = new ArrayList<>();
	 public List<ServiceTransaction> serviceTransactions = new ArrayList<>();
	 
 	 public void addUser(String role, String username, String password) {
	        switch (role) {
	            case "Customer" -> customers.add(new Customer(username, password));
	            case "Seller" -> sellers.add(new Seller(username, password));
	            case "Technician" -> technicians.add(new Technician(username, password));
	        }
	        System.out.print("Added Successfully!");
	 }

	 public boolean isUserPresent(String role, String username, String password) {
		    switch (role) {
		        case "Customer":
		            for (Customer customer : customers) {
		                if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
		                    return true;
		                }
		            }
		            break;
		        case "Seller":
		            for (Seller seller : sellers) {
		                if (seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
		                    return true;
		                }
		            }
		            break;
		        case "Technician":
		            for (Technician technician : technicians) {
		                if (technician.getUsername().equals(username) && technician.getPassword().equals(password)) {
		                    return true;
		                }
		            }
		            break;
		    }
		    return false;
		}
	 
	 public boolean isUsernamePresent(String username) {
		 for (Customer customer : customers) {
             if (customer.getUsername().equals(username)) {
                 return true;
             }
         }
		 for (Seller seller : sellers) {
             if (seller.getUsername().equals(username)) {
                 return true;
             }
         }
         
         for (Technician technician : technicians) {
             if (technician.getUsername().equals(username)) {
                 return true;
             }
         }
         return false;
	 }
	
	 public void addProduct(String name, String type, int items, int price, String description, String sellerName) {
		 products.add(new Product(name , type, items, price , description, sellerName,++productId));
		 System.out.print(sellerName);
	 }
	 
	 public void addService(String name, int price, String description , String technicianName) {
		 services.add(new Service( name,price,description,++serviceId ,technicianName));
		 System.out.print(technicianName);
	 }
	 
	 
	 public void loadAllData() {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            loadCustomers(connection);
	            loadSellers(connection);
	            loadTechnicians(connection);
	            loadProducts(connection);
	            loadServices(connection);
	            loadProductTransactions(connection);
	            loadServiceTransactions(connection);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 private void loadCustomers(Connection connection) throws SQLException {
	        String query = "SELECT * FROM customers";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                customers.add(new Customer(rs.getString("username"), rs.getString("password")));
	            }
	        }
	    }

	 private void loadSellers(Connection connection) throws SQLException {
	        String query = "SELECT * FROM sellers";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Seller seller = new Seller(rs.getString("username"), rs.getString("password"));
	                seller.approved = rs.getBoolean("approved");
	                sellers.add(seller);
	            }
	        }
	    }

	 private void loadTechnicians(Connection connection) throws SQLException {
	        String query = "SELECT * FROM technicians";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                Technician technician = new Technician(rs.getString("username"), rs.getString("password"));
	                technician.approved = rs.getBoolean("approved");
	                technicians.add(technician);
	            }
	        }
	    }

	 private void loadProducts(Connection connection) throws SQLException {
	        String query = "SELECT * FROM products";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                products.add(new Product(
	                        rs.getString("name"),
	                        rs.getString("type"),
	                        rs.getInt("items"),
	                        rs.getInt("price"),
	                        rs.getString("description"),
	                        rs.getString("seller_name"),
	                        rs.getInt("product_id")
	                ));
	                productId = Math.max(productId, rs.getInt("product_id"));
	            }
	        }
	    }

	 private void loadServices(Connection connection) throws SQLException {
	        String query = "SELECT * FROM services";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                services.add(new Service(
	                        rs.getString("name"),
	                        rs.getInt("price"),
	                        rs.getString("description"),
	                        rs.getInt("service_id"),
	                        rs.getString("technician_name")
	                ));
	                serviceId = Math.max(serviceId, rs.getInt("service_id"));
	            }
	        }
	    }

	 private void loadProductTransactions(Connection connection) throws SQLException {
	        String query = "SELECT * FROM product_transactions";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                ProductTransaction transaction = new ProductTransaction(
	                        rs.getString("seller_name"),
	                        rs.getString("customer_name"),
	                        rs.getInt("product_id")
	                );
	                transaction.approved = rs.getBoolean("approved");
	                transaction.rating = rs.getDouble("rating");
	                transaction.userCancelled = rs.getBoolean("user_cancelled");
	                transaction.adminCancelledApproved = rs.getBoolean("admin_cancelled_approved");
	                productTransactions.add(transaction);
	            }
	        }
	    }

	 private void loadServiceTransactions(Connection connection) throws SQLException {
	        String query = "SELECT * FROM service_transactions";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                ServiceTransaction transaction = new ServiceTransaction(
	                        rs.getString("technician_name"),
	                        rs.getString("customer_name"),
	                        rs.getInt("service_id")
	                );
	                transaction.approved = rs.getBoolean("approved");
	                transaction.rating = rs.getDouble("rating");
	                serviceTransactions.add(transaction);
	            }
	        }
	    }
	 public int getMaxProductId() {
	        int maxProductId = 0;
	        String query = "SELECT MAX(product_id) AS max_id FROM products";

	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                maxProductId = resultSet.getInt("max_id");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return maxProductId;
	    }
	 public int getMaxServiceId() {
	        int maxServiceId = 0;
	        String query = "SELECT MAX(service_id) AS max_id FROM services";

	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            if (resultSet.next()) {
	                maxServiceId = resultSet.getInt("max_id");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return maxServiceId;
	    }

	public void deLoader() {
		    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
		        // Clear all data from the tables
		        clearAllTables(connection);

		        // Load data from arrays into the tables
		        saveCustomers(connection);
		        saveSellers(connection);
		        saveTechnicians(connection);
		        saveProducts(connection);
		        saveServices(connection);
		        saveProductTransactions(connection);
		        saveServiceTransactions(connection);

		        System.out.println("Data successfully cleared and reloaded into the database.");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	private void clearAllTables(Connection connection) throws SQLException {
		    String[] tables = {
		            "service_transactions", "product_transactions", "services",
		            "products", "technicians", "sellers", "customers"
		    };

		    try (Statement stmt = connection.createStatement()) {
		        for (String table : tables) {
		            stmt.executeUpdate("DELETE FROM " + table);
		        }
		    }
		}

	private void saveCustomers(Connection connection) throws SQLException {
		    String query = "INSERT INTO customers (username, password) VALUES (?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (Customer customer : customers) {
		            pstmt.setString(1, customer.getUsername());
		            pstmt.setString(2, customer.getPassword());
		            pstmt.executeUpdate();
		        }
		    }
		}


	private void saveSellers(Connection connection) throws SQLException {
		    String query = "INSERT INTO sellers (username, password, approved) VALUES (?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (Seller seller : sellers) {
		            pstmt.setString(1, seller.getUsername());
		            pstmt.setString(2, seller.getPassword());
		            pstmt.setBoolean(3, seller.approved);
		            pstmt.executeUpdate();
		        }
		    }
		}

	private void saveTechnicians(Connection connection) throws SQLException {
		    String query = "INSERT INTO technicians (username, password, approved) VALUES (?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (Technician technician : technicians) {
		            pstmt.setString(1, technician.getUsername());
		            pstmt.setString(2, technician.getPassword());
		            pstmt.setBoolean(3, technician.approved);
		            pstmt.executeUpdate();
		        }
		    }
		}
	private void saveProducts(Connection connection) throws SQLException {
		    String query = "INSERT INTO products (product_id, name, type, items, price, description, seller_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (Product product : products) {
		            pstmt.setInt(1, product.productId);
		            pstmt.setString(2, product.getName());
		            pstmt.setString(3, product.getType());
		            pstmt.setInt(4, product.getItems());
		            pstmt.setInt(5, product.getPrice());
		            pstmt.setString(6, product.getDescription());
		            pstmt.setString(7, product.sellerName);
		            pstmt.executeUpdate();
		        }
		    }
		}

	private void saveServices(Connection connection) throws SQLException {
		    String query = "INSERT INTO services (service_id, name, price, description, technician_name) VALUES (?, ?, ?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (Service service : services) {
		            pstmt.setInt(1, service.serviceId);
		            pstmt.setString(2, service.getName());
		            pstmt.setInt(3, service.getPrice());
		            pstmt.setString(4, service.getDescription());
		            pstmt.setString(5, service.technicianName);
		            pstmt.executeUpdate();
		        }
		    }
		}

	private void saveProductTransactions(Connection connection) throws SQLException {
		    String query = "INSERT INTO product_transactions (transaction_id, seller_name, customer_name, product_id, approved, rating, user_cancelled, admin_cancelled_approved) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (ProductTransaction transaction : productTransactions) {
		            pstmt.setString(1, transaction.sellerName);
		            pstmt.setString(2, transaction.customerName);
		            pstmt.setInt(3, transaction.productId);
		            pstmt.setBoolean(4, transaction.approved);
		            pstmt.setDouble(5, transaction.rating);
		            pstmt.setBoolean(6, transaction.userCancelled);
		            pstmt.setBoolean(7, transaction.adminCancelledApproved);
		            pstmt.executeUpdate();
		        }
		    }
		}

	private void saveServiceTransactions(Connection connection) throws SQLException {
		    String query = "INSERT INTO service_transactions (transaction_id, technician_name, customer_name, service_id, approved, rating) VALUES (?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
		        for (ServiceTransaction transaction : serviceTransactions) {
		           
		            pstmt.setString(1, transaction.technicianName);
		            pstmt.setString(2, transaction.customerName);
		            pstmt.setInt(3, transaction.serviceId);
		            pstmt.setBoolean(4, transaction.approved);
		            pstmt.setDouble(5, transaction.rating);
		            pstmt.executeUpdate();
		        }
		    }
		}

	boolean isSellerTechnicianAproved(String username) {
		for (Seller seller : sellers) {
            if (seller.getUsername().equals(username)) {
                if(seller.approved == true) 
                	return true;
                else
                	return false;
            }
        }
        for (Technician technician : technicians) {
            if (technician.getUsername().equals(username)) {
            	if(technician.approved == true) 
                	return true;
                else
                	return false;
            }
        }
        return false;
	}


	public void addProductTransaction(String sellerName , String username , int productId) {
		productTransactions.add(new ProductTransaction(sellerName , username , productId ));
	}
	
	public void updateRating(String username , double Rating, String sellerName) {
		for (ProductTransaction transaction : Main.System.productTransactions) {
	        if (transaction.productId == productId 
	                && transaction.customerName.equals(username) 
	                && transaction.sellerName.equals(sellerName)) {
	            transaction.rating = Rating; 
	            break;
	        }
	    }
	}

	public void updateServiceRating(String username , double Rating, String technicianName) {
		for (ServiceTransaction transaction : Main.System.serviceTransactions) {
            if (transaction.serviceId == serviceId 
                    && transaction.customerName.equals(username) 
                    && transaction.technicianName.equals(technicianName)) {
                transaction.rating = Rating; // Update the rating
                break;
            }
        }
	}
	public void addServiceTransaction(String technicianName , String username , int productId) {
		this.serviceTransactions.add(new ServiceTransaction(technicianName , username , productId ));
	}
	public void cancelOrder(String username, String sellerName , int productid) {
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

	}
	public void selleracceptCancellationRequest(String username, String sellerName , int productid) {
		for (ProductTransaction transaction : Main.System.productTransactions) {
            if (transaction.productId == productId && transaction.sellerName.equals(username)) {
                transaction.adminCancelledApproved = true;
                return;
            }
        }
	}
	
	public void selleracceptorder(String username, String sellerName , int productid) {
		for (ProductTransaction transaction : Main.System.productTransactions) {
            if (transaction.productId == productId && transaction.sellerName.equals(username)) {
                transaction.approved = true;
                return;
            }
        }
	}
	public void approveSellerTech(String selectedUsername) {
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
	}
	public void technicianapproveservice(String username) {
		boolean updated = false;
	    for (ServiceTransaction transaction : Main.System.serviceTransactions) {
	        if (transaction.technicianName.equals(username) && transaction.serviceId == serviceId) {
	            transaction.approved = true; 
	            updated = true;
	            break;
	        }
	    }
	}
	
	public void validate(String name , String priceText, String description) {
		 try {
		        Integer.parseInt(priceText); 
		    } catch (NumberFormatException e) {
		        
		        return;
		    }
	}
}