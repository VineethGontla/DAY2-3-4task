package snippet;

import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem {

    private Map<Integer, Product> products;  // To store products using product_id as the key
    private int lowStockThreshold;

    // Constructor
    public InventoryManagementSystem(int lowStockThreshold) {
        this.products = new HashMap<>();
        this.lowStockThreshold = lowStockThreshold;
    }

    // Product class to represent each product
    private class Product {
        int productId;
        String name;
        double price;
        int quantity;
        String supplier;

        // Constructor for Product class
        public Product(int productId, String name, double price, int quantity, String supplier) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.supplier = supplier;
        }

        // Update the product quantity
        public void updateQuantity(int amount) {
            this.quantity += amount;
        }

        // Get the stock status of the product
        public String getStockStatus() {
            return "Product: " + this.name + ", Stock: " + this.quantity + ", Price: $" + this.price;
        }
    }

    // Add or update product in the inventory
    public void addProduct(int productId, String name, double price, int quantity, String supplier) {
        if (products.containsKey(productId)) {
            // Update existing product quantity
            Product existingProduct = products.get(productId);
            existingProduct.updateQuantity(quantity);
            System.out.println("Updated " + name + " in inventory. New quantity: " + existingProduct.quantity);
        } else {
            // Add new product
            Product newProduct = new Product(productId, name, price, quantity, supplier);
            products.put(productId, newProduct);
            System.out.println("Added " + name + " to the inventory.");
        }
    }

    // Remove a product from the inventory
    public void removeProduct(int productId) {
        if (products.containsKey(productId)) {
            Product removedProduct = products.remove(productId);
            System.out.println("Removed " + removedProduct.name + " from inventory.");
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    // Update the stock quantity of a product
    public void updateProductQuantity(int productId, int quantity) {
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            product.updateQuantity(quantity);
            System.out.println("Updated " + product.name + "'s stock to " + product.quantity + ".");
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    // Check and display low stock products
    public void checkLowStock() {
        for (Product product : products.values()) {
            if (product.quantity <= lowStockThreshold) {
                System.out.println("Low stock alert: " + product.name + " - Only " + product.quantity + " left.");
            }
        }
    }

    // Display all products in the inventory
    public void getAllProducts() {
        if (!products.isEmpty()) {
            System.out.println("Inventory List:");
            for (Product product : products.values()) {
                System.out.println(product.getStockStatus());
            }
        } else {
            System.out.println("Inventory is empty.");
        }
    }

    // Simulate supplying a product (increasing stock)
    public void supplyProduct(int productId, int quantity) {
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            product.updateQuantity(quantity);
            System.out.println("Supplied " + quantity + " more of " + product.name + ". New stock: " + product.quantity);
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        // Create an instance of the inventory system with a low stock threshold of 5
        InventoryManagementSystem inventory = new InventoryManagementSystem(5);

        // Add products to the inventory
        inventory.addProduct(1, "Apple", 0.5, 20, "Supplier1");
        inventory.addProduct(2, "Banana", 0.3, 2, "Supplier2");

        // Display all products and check for low stock
        inventory.getAllProducts();
        inventory.checkLowStock();

        // Update stock of Banana and supply more
        inventory.updateProductQuantity(2, 10);
        inventory.supplyProduct(2, 5);

        // Display all products again and check low stock
        inventory.getAllProducts();
        inventory.checkLowStock();

        // Remove Apple from inventory
        inventory.removeProduct(1);

        // Display remaining products
        inventory.getAllProducts();
    }
}
