package snippet;
import java.io.*;
import java.util.*;

class LibraryManagementSystem {

    // Book Class
    static class Book {
        String bookId;
        String title;
        String author;
        boolean isAvailable;

        public Book(String bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.isAvailable = true; // Initially, the book is available
        }

        @Override
        public String toString() {
            return bookId + "," + title + "," + author + "," + isAvailable;
        }
    }

    // Member Class
    static class Member {
        String memberId;
        String name;
        String email;

        public Member(String memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return memberId + "," + name + "," + email;
        }
    }

    // Transaction Class
    static class Transaction {
        String transactionId;
        String bookId;
        String memberId;
        String type; // "Issue" or "Return"
        Date date;

        public Transaction(String transactionId, String bookId, String memberId, String type, Date date) {
            this.transactionId = transactionId;
            this.bookId = bookId;
            this.memberId = memberId;
            this.type = type;
            this.date = date;
        }

        @Override
        public String toString() {
            return transactionId + "," + bookId + "," + memberId + "," + type + "," + date;
        }
    }

    // File handling methods
    private static void saveToFile(String filename, List<?> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Object obj : data) {
                writer.write(obj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    private static List<Book> loadBooksFromFile(String filename) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Book book = new Book(parts[0], parts[1], parts[2]);
                    book.isAvailable = Boolean.parseBoolean(parts[3]);
                    books.add(book);
                } else {
                    System.out.println("Skipping invalid line in books file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
        return books;
    }

    private static List<Member> loadMembersFromFile(String filename) {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Member member = new Member(parts[0], parts[1], parts[2]);
                    members.add(member);
                } else {
                    System.out.println("Skipping invalid line in members file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading members from file: " + e.getMessage());
        }
        return members;
    }

    private static List<Transaction> loadTransactionsFromFile(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Date date = new Date(parts[4]);
                    Transaction transaction = new Transaction(parts[0], parts[1], parts[2], parts[3], date);
                    transactions.add(transaction);
                } else {
                    System.out.println("Skipping invalid line in transactions file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions from file: " + e.getMessage());
        }
        return transactions;
    }

    // Main Application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load initial data from files
        List<Book> books = loadBooksFromFile("books.txt");
        List<Member> members = loadMembersFromFile("members.txt");
        List<Transaction> transactions = loadTransactionsFromFile("transactions.txt");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. View Members");
            System.out.println("7. View Transactions");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    books.add(new Book(bookId, title, author));
                    saveToFile("books.txt", books); // Save to file
                    System.out.println("Book added successfully.");
                    break;

                case 2: // Add Member
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member email: ");
                    String email = scanner.nextLine();
                    members.add(new Member(memberId, name, email));
                    saveToFile("members.txt", members); // Save to file
                    System.out.println("Member added successfully.");
                    break;

                case 3: // Issue Book
                    System.out.print("Enter book ID to issue: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();
                    boolean bookFound = false;
                    boolean memberFound = false;
                    for (Book book : books) {
                        if (book.bookId.equals(bookId) && book.isAvailable) {
                            bookFound = true;
                            for (Member member : members) {
                                if (member.memberId.equals(memberId)) {
                                    memberFound = true;
                                    // Create transaction for issuing the book
                                    String transactionId = UUID.randomUUID().toString();
                                    Transaction transaction = new Transaction(transactionId, bookId, memberId, "Issue", new Date());
                                    transactions.add(transaction);
                                    book.isAvailable = false; // Mark book as not available
                                    saveToFile("books.txt", books); // Save books after transaction
                                    saveToFile("transactions.txt", transactions); // Save transaction
                                    System.out.println("Book issued successfully.");
                                    break;
                                }
                            }
                            if (!memberFound) {
                                System.out.println("Member not found.");
                            }
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("Book not available or not found.");
                    }
                    break;

                case 4: // Return Book
                    System.out.print("Enter book ID to return: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    memberId = scanner.nextLine();
                    for (Book book : books) {
                        if (book.bookId.equals(bookId) && !book.isAvailable) {
                            for (Member member : members) {
                                if (member.memberId.equals(memberId)) {
                                    // Create transaction for returning the book
                                    String transactionId = UUID.randomUUID().toString();
                                    Transaction transaction = new Transaction(transactionId, bookId, memberId, "Return", new Date());
                                    transactions.add(transaction);
                                    book.isAvailable = true; // Mark book as available
                                    saveToFile("books.txt", books); // Save books after transaction
                                    saveToFile("transactions.txt", transactions); // Save transaction
                                    System.out.println("Book returned successfully.");
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;

                case 5: // View Books
                    System.out.println("Books in the Library:");
                    for (Book book : books) {
                        System.out.println(book);
                    }
                    break;

                case 6: // View Members
                    System.out.println("Members of the Library:");
                    for (Member member : members) {
                        System.out.println(member);
                    }
                    break;

                case 7: // View Transactions
                    System.out.println("Library Transactions:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;

                case 8: // Exit
                    exit = true;
                    System.out.println("Exiting the Library Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

