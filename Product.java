package budget;


public class Product {
    String category;
    String name;
    double price;

    public Product(String category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
       return this.price;
    }
}
