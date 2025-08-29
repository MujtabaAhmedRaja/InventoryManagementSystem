
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




