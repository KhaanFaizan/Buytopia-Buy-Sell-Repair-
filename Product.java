package application;

public class Product {
	public int productId;
    private String name;
    private String type;
    private int items;
    private int price;
    private String description;
    public String sellerName;
    public Product(String name, String type, int items, int price, String description, String sellerName , int id) {
        this.name = name;
        this.type = type;
        this.items = items;
        this.price = price;
        this.description = description;
        this.sellerName = sellerName;
        this.productId = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getItems() {
        return items;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product {" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", items=" + items +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
