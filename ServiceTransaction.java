package application;

public class ServiceTransaction{
	public String technicianName;
	public String customerName;
	public int serviceId;
	public boolean approved;
	public double rating;
	ServiceTransaction(String technicianName , String customerName , int serviceId){
		this.technicianName = technicianName;
		this.customerName = customerName;
		this.serviceId = serviceId;
		approved = false;
		rating =0.0;
	}
	void setRating(double Rating) {
		this.rating = Rating;
	}
	void setApproved(boolean a) {
		this.approved =a;
	}
}