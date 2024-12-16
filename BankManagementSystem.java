import java.util.*;

class Customer {
    private String name;
    private int accountNumber;
    private int age;
    private double balance;

    public Customer(String name, int accountNumber, int age, double initialBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.age = age;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= 1000) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }
}

public class BankManagementSystem {
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Update Account Details");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            System.out.println("8. View All Accounts");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    balanceInquiry();
                    break;
                case 5:
                    updateAccountDetails();
                    break;
                case 6:
                    deleteAccount();
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                case 8:
                    viewAllAccounts();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        if (initialBalance < 1000) {
            System.out.println("Initial balance must be at least ₹1000.");
            return;
        }

        Customer customer = new Customer(name, accountNumber, age, initialBalance);
        customers.put(accountNumber, customer);
        System.out.println("Account created successfully.");
    }

    private static void depositMoney() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        Customer customer = customers.get(accountNumber);

        if (customer != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = scanner.nextDouble();
            customer.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        Customer customer = customers.get(accountNumber);

        if (customer != null) {
            System.out.print("Enter Withdrawal Amount: ");
            double amount = scanner.nextDouble();
            customer.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void balanceInquiry() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        Customer customer = customers.get(accountNumber);

        if (customer != null) {
            System.out.println("Current Balance: ₹" + customer.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void updateAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        Customer customer = customers.get(accountNumber);

        if (customer != null) {
            System.out.print("Enter New Name: ");
            scanner.nextLine(); // Consume newline
            String name = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int age = scanner.nextInt();
            customer.setName(name);
            customer.setAge(age);
            System.out.println("Account details updated successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        if (customers.remove(accountNumber) != null) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAllAccounts() {
        if (customers.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("\n--- List of All Accounts ---");
            for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
                Customer customer = entry.getValue();
                System.out.println("Account Number: " + customer.getAccountNumber());
                System.out.println("Name: " + customer.getName());
                System.out.println("Age: " + customer.getAge());
                System.out.println("Balance: ₹" + customer.getBalance());
                System.out.println("-----------------------------");
            }
        }
    }
}
