package oop.lab.task.pkg3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Garment {
    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;

    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }

    public double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return price - discount;
    }
}

class Fabric {
    public String id;
    public String type;
    public String color;
    public double pricePerMeter;

    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

    public double calculateCost(double meters) {
        return pricePerMeter * meters;
    }
}

class Supplier {
    public String id;
    public String name;
    public String contactInfo;
    List<Fabric> suppliedFabric = new ArrayList<>();

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void addFabric(Fabric fabric) {
        suppliedFabric.add(fabric);
    }

    public List<Fabric> getSuppliedFabrics() {
        return suppliedFabric;
    }
}

class Order {
    public String orderId;
    public Date orderDate;
    public List<Garment> garments = new ArrayList<>();
    private double totalAmount;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public double calculateTotalAmount() {
        totalAmount = 0;
        for (Garment g : garments) {
            totalAmount += g.price;
        }
        return totalAmount;
    }

    public void printOrderDetails() {
        System.out.println("--------------------------");
        System.out.println("Order Details");
        System.out.println("--------------------------");
        for (Garment g : garments) {
            System.out.println("ID: " + g.id + ", Name: " + g.name + ", Price: " + g.price + ", Description: " + g.description);
        }
        System.out.println("Total Amount: " + calculateTotalAmount());
        System.out.println("--------------------------");
    }
}

class Customer {
    public String customerId;
    public String name;
    public String email;
    public String phone;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void placeOrder(Order order) {
        order.printOrderDetails();
        System.out.println("Order Placed");
    }
}

class Inventory {
    List<Garment> garments = new ArrayList<>();

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public void removeGarment(String id) {
        garments.removeIf(g -> g.id.equals(id));
    }

    public Garment findGarment(String id) {
        for (Garment g : garments) {
            if (g.id.equals(id)) return g;
        }
        return null;
    }

    public void viewGarments() {
        if (garments.isEmpty()) {
            System.out.println("No garments available.");
        } else {
            System.out.println("List of Garments:");
            for (Garment g : garments) {
                System.out.println("ID: " + g.id + ", Name: " + g.name + ", Stock: " + g.stockQuantity);
            }
        }
    }
}

public class OopLabTask3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice;

        do {
            System.out.println("Garment Management System Menu");
            System.out.println("1. Add Garment");
            System.out.println("2. View Garments");
            System.out.println("3. Add Supplier");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Garment ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int stockQuantity = scanner.nextInt();
                    inventory.addGarment(new Garment(id, name, description, size, color, price, stockQuantity));
                    System.out.println("Garment added successfully!");
                    break;

                case 2:
                    inventory.viewGarments();
                    break;

                case 3:
                    System.out.print("Enter Supplier ID: ");
                    String supplierId = scanner.nextLine();
                    System.out.print("Enter Supplier Name: ");
                    String supplierName = scanner.nextLine();
                    System.out.print("Enter Contact Info: ");
                    String contactInfo = scanner.nextLine();
                    Supplier supplier = new Supplier(supplierId, supplierName, contactInfo);
                    System.out.println("Supplier added successfully!");
                    break;

                case 4:
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    Order order = new Order(orderId, new Date());
                    System.out.print("How many garments do you want to add to the order? ");
                    int numberOfGarments = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numberOfGarments; i++) {
                        System.out.print("Enter Garment ID: ");
                        String garmentId = scanner.nextLine();
                        Garment garment = inventory.findGarment(garmentId);
                        if (garment != null) {
                            order.addGarment(garment);
                        } else {
                            System.out.println("Garment not found!");
                        }
                    }
                    order.printOrderDetails();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
