package application;

public class Service {
	public int serviceId;
    private String name;
    private int price;
    private String description;
    public String technicianName;

    public Service(String name, int price, String description, int serviceId , String technicianName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.serviceId = serviceId;
        this.technicianName = technicianName;
        
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

   
}
