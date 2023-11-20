import java.util.*;

public class Menagerie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pet> pets = new ArrayList<>();

        System.out.println("************************************************************");
        System.out.println("             Menagerie V1.0: The Pet Simulator              ");
        System.out.println("************************************************************");
        // Main menu
        while (true) {
            System.out.println("What would you like to do? ");
            System.out.println("1. Add a new pet.");
            System.out.println("2. Print list of pets.");
            System.out.println("3. Save pets to a file.");
            System.out.println("4. Load pets from a file.");
            System.out.println("5. Simulate a day in the life of your pets.");
            System.out.println("6. Clear your list of pets.");
            System.out.println("7. Exit");

            System.out.print("Enter the number of your choice: ");
            int choice = scanner.nextInt();

            // Switch method to determine what to do
            switch (choice) {
                case 1:
                    addNewPet(scanner, pets);
                    break;
                case 2:
                    printListOfPets(pets);
                    break;
                case 3:
                    savePetsToFile(pets);
                    break;
                case 4:
                    loadPetsFromFile(pets, scanner);
                    break;
                case 5:
                    simulateDay(pets);
                    break;
                case 6:
                    clearListOfPets(pets);
                    break;
                case 7:
                    System.out.println("Thank you for using Menagerie. We hope you had fun");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add new pet based on user input
    private static void addNewPet(Scanner scanner, ArrayList<Pet> pets) {
        System.out.print("Enter d for dog, c for cat, or f for fish: ");
        char petType = scanner.next().charAt(0);
        System.out.print("Enter name, age, and weight: ");
        String name = scanner.next();
        int age = scanner.nextInt();
        double weight = scanner.nextDouble();

        switch (petType) {
            case 'd':
                pets.add(new Dog(name, age, weight));
                break;
            case 'c':
                pets.add(new Cat(name, age, weight));
                break;
            case 'f':
                pets.add(new Fish(name, age, weight));
                break;
            default:
                System.out.println("Invalid pet type. Please try again.");
        }
    }

    // Prints list
    private static void printListOfPets(ArrayList<Pet> pets) {
        pets.sort(Comparator.comparing(Pet::getName));
        PetWriter.writeToScreen(pets);
    }

    // Saves file
    private static void savePetsToFile(ArrayList<Pet> pets) {
        pets.sort(Comparator.comparing(Pet::getName));
        PetWriter.writeToFile(pets, "pets.txt");
    }

    // Loads file
    private static void loadPetsFromFile(ArrayList<Pet> pets, Scanner scanner) {
        pets.clear();
        pets.addAll(PetReader.readPetsFromFile("pets.txt"));
        System.out.println("Pets loaded from file.");
    }

    // Simulates actions in day
    private static void simulateDay(ArrayList<Pet> pets) {
        System.out.println("Here is a simulation of a day in the life of your pets: \n");
    
        // Loop for each hour in the day
        for (int hour = 1; hour <= 24; hour++) {
            System.out.println("HOUR " + hour);
            System.out.println("-------");
    
            // Iterate through each pet and simulate their actions for the current hour
            for (Pet pet : pets) {
                // Display pet's activity for the current hour
                System.out.println(pet.getType() + ", " + pet.getName() + ", " + pet.act());
            }
            System.out.println();
        }
    }

    // Clears list
    private static void clearListOfPets(ArrayList<Pet> pets) {
        pets.clear();
        System.out.println("List of pets cleared.");
    }
}
