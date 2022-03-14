package budget;

public class Type {
    String type;
    double total;
public Type(String type, double total) {
    this.type = type;
    this.total = total;
}
public String getType() {
    return this.type;
}
public double getTotal() {
    return this.total;
}
}
