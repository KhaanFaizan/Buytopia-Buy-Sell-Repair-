package application;

public class ProductTransaction{
	public String sellerName;
	public String customerName;
	public int productId;
	public boolean approved;
	public double rating;
	public boolean userCancelled;
	public boolean adminCancelledApproved;
	ProductTransaction(String sellername , String customerName , int productId){
		this.sellerName = sellername;
		this.customerName = customerName;
		this.productId = productId;
		approved = false;
		rating =0.0;
		userCancelled = false;
		adminCancelledApproved = false;
	}
	public void setRating(Double Rating) {
		this.rating = Rating;
	}
	
	public void setCancel(boolean a) {
		userCancelled = a;
	}
	public void setadminCancelledApproved(boolean a) {
		adminCancelledApproved = a;
	}
	public void setapproved(boolean a) {
		approved =a;
	}
}