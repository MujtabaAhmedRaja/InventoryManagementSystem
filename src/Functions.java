import java.util.*;

import java.util.Scanner;
class Functions {
    public static HashMap<Integer, Product> inventory = new HashMap<>();
    public Queue<Order> orderQueue = new LinkedList<>();
    public int nextOrderId = 1;

    // Add a new product to the inventory
    public void addProduct(int id, String name, int quantity, double price, Supplier supplier) {
        if (inventory.containsKey(id)) {
            System.out.println("Product with this ID already exists.");
            return;
        }
        Product product = new Product(id, name, quantity, price, supplier);
        inventory.put(id, product);
        System.out.println("Product added successfully.");
    }

    // Search for a product by ID or name
    public Product searchProduct(String query) {
        try {
            int id = Integer.parseInt(query);
            return inventory.get(id);
        } catch (Exception e) {
            for (Product product : inventory.values()) {
                if (product.name.equalsIgnoreCase(query)) {
                    return product;
                }
            }
        }
        return null;
    }

    // Display the list of all products
    public ArrayList<Product> displayProducts() {
        ArrayList<Product> p=new ArrayList<>();
        if (inventory.isEmpty()) {
            System.out.println("No products in the inventory.");

        }
        for (Product product : inventory.values()) {
            System.out.println("ID: " + product.id + ", Name: " + product.name + ", Quantity: " + product.quantity + ", Price: " + product.price + ", Supplier: " + product.supplier.name);
            p.add(product);
        }
        return p;
    }

    // Update the quantity of an existing product
    public void updateProductQuantity(int id, int newQuantity) {
        Product product = inventory.get(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("1.Add Quantity" +
                    " \n2.Initialize Again");
            int ch = sc.nextInt();
            if (ch == 1) {
                product.quantity = product.quantity + newQuantity;
            } else if (ch == 2)
                product.quantity = newQuantity;
            System.out.println("Quantity updated successfully.");
        }
    }

    // Delete a product from the inventory
    public void deleteProduct(int id) {
        if (inventory.remove(id) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Add a new order
    public void addOrder(int productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        if (product.quantity < quantity) {
            System.out.println("Insufficient stock.");
            return;
        }
        Order order = new Order(nextOrderId++, product, quantity, "Pending");
        orderQueue.add(order);
        System.out.println("Order added successfully. Order ID: " + order.orderId);
    }
    public void displayorders(){
        if(!orderQueue.isEmpty()){
            for(Order o:orderQueue){
                System.out.println(o.orderId+"|| "+"||"+o.product.name+"||"+o.quantity);
            }
        }
    }
    // Process the next order in the queue
    public void processOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }
        int bill;
        Order order = orderQueue.poll();
        bill= (int) (order.quantity*order.product.price);
        if (order.product.quantity >= order.quantity) {
            order.product.quantity -= order.quantity;
            order.status = "Processed";
            System.out.println("Order processed successfully. Order ID: " + order.orderId);
            System.out.println("Bill: "+bill);
        } else {
            System.out.println("Insufficient stock to process the order. Order ID: " + order.orderId);
        }
    }

    // Cancel an order
    public void cancelOrder(int orderId) {
        boolean found = false;
        Queue<Order> tempQueue = new LinkedList<>();

        while (!orderQueue.isEmpty()) {
            Order currentOrder = orderQueue.poll();
            if (currentOrder.orderId == orderId) {
                found = true;
                System.out.println("Order cancelled successfully. Order ID: " + orderId);
            } else {
                tempQueue.add(currentOrder);
            }
        }

        orderQueue.addAll(tempQueue);

        if (!found) {
            System.out.println("Order not found. Order ID: " + orderId);
        }
    }





