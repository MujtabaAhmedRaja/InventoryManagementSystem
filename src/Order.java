class Order {
    public int orderId;
    public Product product;
    public int quantity;
    public String status; // "Pending", "Processed", "Cancelled"



    public Order(int orderId, Product product, int quantity, String status) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.status = status;


    }
}