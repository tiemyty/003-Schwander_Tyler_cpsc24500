import java.io.*;
import java.util.*;

//Represents a book with title and author information
class Book implements Comparable<Book> {
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author;
    }

    @Override
    public int compareTo(Book other) {
        //Compare by title in ascending order
        return this.title.compareTo(other.title);
    }
}

//Manages the book library, providing functionality for adding, listing, deleting, and sorting books
class BookManager {
    List<Book> library;

    public BookManager() {
        library = new LinkedList<>();
    }

    //Adds a book to the library
    public void addBook(String title, String author) {
        library.add(new Book(title, author));
        System.out.println("Book added: " + library.get(library.size() - 1));
    }

    //Lists all books in the library
    public void listBooks() {
        for (Book book : library) {
            System.out.println(book);
        }
    }

    //Deletes a book from the library
    public void deleteBook(String title) {
        Iterator<Book> iterator = library.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.title.equals(title)) {
                iterator.remove();
                System.out.println("Book removed: " + book);
                break;
            }
        }
    }
    //Saves the library data to a text file
    public void saveLibrary() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("library.txt"))) {
            for (Book book : library) {
                writer.println(book.title + "," + book.author);
            }
            System.out.println("Library data saved to library.txt");
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }
    //Loads library from file
    public void loadLibrary() {
        try (Scanner scanner = new Scanner(new File("library.txt"))) {
            library.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    library.add(new Book(parts[0], parts[1]));
                }
            }
            System.out.println("Library data loaded from library.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Library file not found. Creating a new one.");
        }
    }

    //Sorts based on specific criteria
    public void sortBooks(String sortBy, boolean ascending) {
        switch (sortBy.toLowerCase()) {
            case "title":
                Collections.sort(library);
                break;
            case "author":
                Collections.sort(library, Comparator.comparing(book -> book.author));
                break;
            default:
                System.out.println("Invalid sorting option.");
                return;
        }

        if (!ascending) {
            Collections.reverse(library);
        }

        System.out.println("Library Contents:");
        listBooks();
    }
}

//Main class
public class BookManagerApplication {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.loadLibrary();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("****************************************************");
            System.out.println("             Book Management Program");
            System.out.println("****************************************************");
            System.out.println("This program is designed to manage Lewis Library Data.");
            System.out.println("Here you can add a book title and authors, ");
            System.out.println("display present book list,");
            System.out.println("delete a book information,");
            System.out.println("save the book list, ");
            System.out.println("and exit the program.");
            System.out.println("Choose an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Display books");
            System.out.println("3. Delete a book");
            System.out.println("4. Save it and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author: ");
                    String author = scanner.nextLine();
                    bookManager.addBook(title, author);
                    break;
                case 2:
                    System.out.println("Choose a sorting option:");
                    System.out.println("1. Sort by Title ascending");
                    System.out.println("2. Sort by Author ascending");
                    System.out.println("3. Sort by Title descending");
                    System.out.println("4. Sort by Author descending");
                    System.out.println("5. Display unsorted list");
                    System.out.print("Enter your choice: ");
                    int sortOption = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (sortOption) {
                        case 1:
                            bookManager.sortBooks("title", true);
                            break;
                        case 2:
                            bookManager.sortBooks("author", true);
                            break;
                        case 3:
                            bookManager.sortBooks("title", false);
                            break;
                        case 4:
                            bookManager.sortBooks("author", false);
                            break;
                        case 5:
                            System.out.println("Unsorted Library Contents:");
                            bookManager.listBooks();
                            break;
                        default:
                            System.out.println("Invalid sorting option.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title of the book to remove: ");
                    String deleteTitle = scanner.nextLine();
                    bookManager.deleteBook(deleteTitle);
                    break;
                case 4:
                    bookManager.saveLibrary();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 4);

        scanner.close();
        System.out.println("Goodbye!");
    }
}
