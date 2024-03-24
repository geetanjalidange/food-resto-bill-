import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}

class Order {
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}

public class OrderReceipt {
    public static void printReceipt(Order order) {
        System.out.println("--------- Receipt ---------");
        System.out.println("Item\t\t\tPrice\t\tQuantity\tTotal");
        for (OrderItem item : order.getItems()) {
            MenuItem menuItem = item.getMenuItem();
            System.out.printf("%-20s$%.2f\t%d\t\t$%.2f\n", menuItem.getName(), menuItem.getPrice(),
                    item.getQuantity(), item.getTotalPrice());
        }
        System.out.println("\nTotal: $" + order.getTotalPrice());
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some menu items
        MenuItem burger = new MenuItem("Burger", 5.99);
        MenuItem fries = new MenuItem("Fries", 2.49);
        MenuItem drink = new MenuItem("Drink", 1.99);

        // Create an order
        Order order = new Order();

        // Ask user for order items
        while (true) {
            System.out.print("Enter item (burger/fries/drink) or 'done' to finish order: ");
            String itemName = scanner.nextLine().toLowerCase();
            if (itemName.equals("done")) {
                break;
            }

            MenuItem menuItem = null;
            switch (itemName) {
                case "burger":
                    menuItem = burger;
                    break;
                case "fries":
                    menuItem = fries;
                    break;
                case "drink":
                    menuItem = drink;
                    break;
                default:
                    System.out.println("Invalid item. Please enter 'burger', 'fries', 'drink', or 'done'.");
                    continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            order.addItem(new OrderItem(menuItem, quantity));
        }

        // Print receipt
        printReceipt(order);

        scanner.close();
    }
}
