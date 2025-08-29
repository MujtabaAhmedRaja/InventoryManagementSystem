//import java.util.*;
//public class InventoryManagementSystem {
//    public static void main(String[] args) {
//        Functions functions = new Functions();
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n--- Inventory Management System ---");
//            System.out.println("1. Press 1 to Add Product");
//            System.out.println("2. Press 2 to Search Product");
//            System.out.println("3. Press 3 to Display All Products");
//            System.out.println("4. Press 4 to Update Product Quantity");
//            System.out.println("5. Press 5 to Delete Product");
//            System.out.println("6. Press 6 to Place Order");
//            System.out.println("7. Press 7 to Process Order");
//            System.out.println("8. Press 8 to Cancel Order");
//            System.out.println("9. Press 9 to Display All Orders");
//            System.out.println("10.Press 10 to Exit");
//            System.out.print("Enter your choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter Product ID: ");
//                    int productId = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Enter Product Name: ");
//                    String productName = scanner.nextLine();
//                    System.out.print("Enter Quantity: ");
//                    int productQuantity = scanner.nextInt();
//                    System.out.print("Enter Price: ");
//                    double productPrice = scanner.nextDouble();
//                    scanner.nextLine();
//                    System.out.print("Enter Supplier ID: ");
//                    int supplierId = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.print("Enter Supplier Name: ");
//                    String supplierName = scanner.nextLine();
//                    System.out.print("Enter Supplier Contact: ");
//                    String supplierContact = scanner.nextLine();
//                    Supplier supplier = new Supplier(supplierId, supplierName, supplierContact);
//                    functions.addProduct(productId, productName, productQuantity, productPrice, supplier);
//                    break;
//
//                case 2:
//                    System.out.print("Enter Product ID or Name: ");
//                    String query = scanner.nextLine();
//                    Product product = functions.searchProduct(query);
//                    if (product != null) {
//                        System.out.println("Product Found: ID: " + product.id + ", Name: " + product.name + ", Quantity: " + product.quantity + ", Price: " + product.price + ", Supplier: " + product.supplier.name);
//                    } else {
//                        System.out.println("Product not found.");
//                    }
//                    break;
//
//                case 3:
//                    functions.displayProducts();
//                    break;
//
//                case 4:
//                    System.out.print("Enter Product ID: ");
//                    int updateId = scanner.nextInt();
//                    System.out.print("Enter New Quantity: ");
//                    int newQuantity = scanner.nextInt();
//                    functions.updateProductQuantity(updateId, newQuantity);
//                    break;
//
//                case 5:
//                    System.out.print("Enter Product ID: ");
//                    int deleteId = scanner.nextInt();
//                    functions.deleteProduct(deleteId);
//                    break;
//
//                case 6:
//                    System.out.print("Enter Product ID: ");
//                    int orderProductId = scanner.nextInt();
//                    System.out.print("Enter Quantity: ");
//                    int orderQuantity = scanner.nextInt();
//                    functions.addOrder(orderProductId, orderQuantity);
//                    break;
//
//                case 7:
//                    functions.processOrder();
//                    break;
//
//                case 8:
//                    System.out.print("Enter Order ID: ");
//                    int cancelOrderId = scanner.nextInt();
//                    functions.cancelOrder(cancelOrderId);
//                    break;
//                case 9:
//                    functions.displayorders();
//                    break;
//                case 10:
//                    System.out.println("Exiting the system. Goodbye!");
//                    scanner.close();
//                    return;
//
//
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
//            }
//        }
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryManagementSystem extends JFrame {
    private Functions functions;
    private JTextArea outputArea;
    private JTextField productIdField, productNameField, productQuantityField, productPriceField, supplierIdField, supplierNameField, supplierContactField, orderProductIdField, orderQuantityField, cancelOrderIdField;

    public InventoryManagementSystem() {
        functions = new Functions();
        setTitle("Inventory Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        // Product fields
        inputPanel.add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        inputPanel.add(productIdField);

        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);

        inputPanel.add(new JLabel("Quantity:"));
        productQuantityField = new JTextField();
        inputPanel.add(productQuantityField);

        inputPanel.add(new JLabel("Price:"));
        productPriceField = new JTextField();
        inputPanel.add(productPriceField);

        inputPanel.add(new JLabel("Supplier ID:"));
        supplierIdField = new JTextField();
        inputPanel.add(supplierIdField);

        inputPanel.add(new JLabel("Supplier Name:"));
        supplierNameField = new JTextField();
        inputPanel.add(supplierNameField);

        inputPanel.add(new JLabel("Supplier Contact:"));
        supplierContactField = new JTextField();
        inputPanel.add(supplierContactField);

        inputPanel.add(new JLabel("Order Product ID:"));
        orderProductIdField = new JTextField();
        inputPanel.add(orderProductIdField);

        inputPanel.add(new JLabel("Order Quantity:"));
        orderQuantityField = new JTextField();
        inputPanel.add(orderQuantityField);

        inputPanel.add(new JLabel("Cancel Order ID:"));
        cancelOrderIdField = new JTextField();
        inputPanel.add(cancelOrderIdField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        // Buttons for actions
        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        buttonPanel.add(addButton);

        JButton searchButton = new JButton("Search Product");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });
        buttonPanel.add(searchButton);

        JButton displayButton = new JButton("Display All Products");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProducts();
            }
        });
        buttonPanel.add(displayButton);

        JButton updateButton = new JButton("Update Product Quantity");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProductQuantity();
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });
        buttonPanel.add(deleteButton);

        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
        buttonPanel.add(orderButton);

        JButton processButton = new JButton("Process Order");
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processOrder();
            }
        });
        buttonPanel.add(processButton);

        JButton cancelButton = new JButton("Cancel Order");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelOrder();
            }
        });
        buttonPanel.add(cancelButton);

        JButton displayOrdersButton = new JButton("Display All Orders");
        displayOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                functions.displayorders();
            }
        });
        buttonPanel.add(displayOrdersButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addProduct() {
        int productId = Integer.parseInt(productIdField.getText());
        String productName = productNameField.getText();
        int productQuantity = Integer.parseInt(productQuantityField.getText());
        double productPrice = Double.parseDouble(productPriceField.getText());
        int supplierId = Integer.parseInt(supplierIdField.getText());
        String supplierName = supplierNameField.getText();
        String supplierContact = supplierContactField.getText();
        Supplier supplier = new Supplier(supplierId, supplierName, supplierContact);
        functions.addProduct(productId, productName, productQuantity, productPrice, supplier);
        outputArea.append("Product added: " + productName + "\n");
    }

    private void searchProduct() {
        String query = productIdField.getText();
        Product product = functions.searchProduct(query);
        if (product != null) {
            outputArea.append("Product Found: ID: " + product.id + ", Name: " + product.name + ", Quantity: " + product.quantity + ", Price: " + product.price + ", Supplier: " + product.supplier.name + "\n");
        } else {
            outputArea.append("Product not found.\n");
        }
    }

    private void displayProducts() {
        outputArea.append("Displaying all products:\n");
        ArrayList<Product> x=functions.displayProducts();
        for(Product produc:x)
        outputArea.append("Product Found: ID: " + produc.id + ", Name: " + produc.name + ", Quantity: " + produc.quantity + ", Price: " + produc.price + ", Supplier: " + produc.supplier.name + "\n");

    }

    private void updateProductQuantity() {
        int updateId = Integer.parseInt(productIdField.getText());
        int newQuantity = Integer.parseInt(productQuantityField.getText());
        functions.updateProductQuantity(updateId, newQuantity);
        outputArea.append("Updated quantity for Product ID: " + updateId + "\n");
    }

    private void deleteProduct() {
        int deleteId = Integer.parseInt(productIdField.getText());
        functions.deleteProduct(deleteId);
        outputArea.append("Deleted Product ID: " + deleteId + "\n");
    }

    private void placeOrder() {
        int orderProductId = Integer.parseInt(orderProductIdField.getText());
        int orderQuantity = Integer.parseInt(orderQuantityField.getText());
        functions.addOrder(orderProductId, orderQuantity);
        outputArea.append("Order placed for Product ID: " + orderProductId + "\n");
    }

    private void processOrder() {
        functions.processOrder();
        outputArea.append("Order processed.\n");
    }

    private void cancelOrder() {
        int cancelOrderId = Integer.parseInt(cancelOrderIdField.getText());
        functions.cancelOrder(cancelOrderId);
        outputArea.append("Order canceled: ID " + cancelOrderId + "\n");
    }

    private void displayOrders() {
        outputArea.append("Displaying all orders:\n");
        displayOrders(); // Assuming this method prints to the outputArea
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InventoryManagementSystem gui = new InventoryManagementSystem();
                gui.setVisible(true);
            }
        });
    }
}



