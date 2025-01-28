package snippet;

import java.io.*;
import java.util.*;

public class ECommerceSystem {

    private List<Product> products;
    private List<Order> orders;
    private List<Customer> customers;

    private static final String ORDER_HISTORY_FILE = "order_history.txt";

    public ECommerceSystem() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // Add a product to the system
    public void addProduct(Product product) {
        products.add(product);
    }

    // Remove a product from the system
    public void removeProduct(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
    }

    // Create an order for a customer
    public void createOrder(Customer customer, List<Product> orderedProducts) {
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, customer, orderedProducts);
        orders.add(order);
        saveOrderHistory(order);
    }

    // Calculate the total cost of an order
    public double calculateTotalCost(Order order) {
        return order.calculateTotalCost();
    }

    // Save the order history to a file
    private void saveOrderHistory(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_HISTORY_FILE, true))) {
            writer.write(order.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

    // Display all products
    public void displayProducts() {
        System.out.println("Products available:");
        for (Product product : products) {
            System.out.println(product.getProductId() + " - " + product.getProductName() + " - $" + product.getPrice());
        }
    }

    // Display all orders
    public void displayOrders() {
        System.out.println("Orders placed:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        // Create the e-commerce system
        ECommerceSystem system = new ECommerceSystem();

        // Add some products
        system.addProduct(new Product("P101", "Laptop", 800));
        system.addProduct(new Product("P102", "Smartphone", 500));

        // Create customers
        Customer customer1 = new Customer("C001", "vineeth", "vineeth@example.com");
        Customer customer2 = new Customer("C002", "vikas", "bob@example.com");

        // Create orders
        List<Product> order1Products = Arrays.asList(system.products.get(0), system.products.get(1));
        system.createOrder(customer1, order1Products);

        // Display products and orders
        system.displayProducts();
        system.displayOrders();
    }
}

// Product class
class Product {
    private String productId;
    private String productName;
    private double price;

    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return productId + "," + productName + "," + price;
    }
}

// Customer class
class Customer {
    private String customerId;
    private String customerName;
    private String customerEmail;

    public Customer(String customerId, String customerName, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    @Override
    public String toString() {
        return customerId + "," + customerName + "," + customerEmail;
    }
}

// Order class
class Order {
    private String orderId;
    private Customer customer;
    private List<Product> products;

    public Order(String orderId, Customer customer, List<Product> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
    }

    public double calculateTotalCost() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        for (Product product : products) {
            orderDetails.append(product.getProductName()).append(", ");
        }
        return "Order ID: " + orderId + ", Customer: " + customer.getCustomerName() +
                ", Products: " + orderDetails + "Total Cost: $" + calculateTotalCost();
    }
}

