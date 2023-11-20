import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Abstract class representing a Pet
abstract class Pet {
    private String name;
    private int age;
    private double weight;
    private Random random = new Random();
    protected int eatCutoff;
    protected int sleepCutoff;
    protected int attentionCutoff;

    // Constructor
    public Pet(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    // Getter 
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public abstract String getType();

    protected Random getRandom() {
        return random;
    }

    // Method to check if the pet is sleeping
    public boolean isSleeping() {
        return random.nextInt(100) < sleepCutoff;
    }

    // Method to check if the pet is eating
    public boolean isEating() {
        return random.nextInt(100) < eatCutoff;
    }

    // Method to check if the pet is seeking attention
    public boolean isSeekingAttention() {
        return random.nextInt(100) < attentionCutoff;
    }

    // Abstract method to represent the pet's action
    public abstract String act();

    // Method to provide a formatted string representation of the pet
    @Override
    public String toString() {
        return String.format("%s - %s, Age: %d, Weight: %.2f", getType(), name, age, weight);
    }
}

// Class representing a Dog, extending from Pet. Same comments, methods, and ideas for other classes.
class Dog extends Pet {
    public Dog(String name, int age, double weight) {
        super(name, age, weight);
        setBehaviorCutoffs(30, 60, 70);
    }

    // Method to set behavior cutoffs for a Dog
    private void setBehaviorCutoffs(int eat, int sleep, int attention) {
        super.eatCutoff = eat;
        super.sleepCutoff = sleep;
        super.attentionCutoff = attention;
    }

    // Overridden method to get the type of pet
    @Override
    public String getType() {
        return "Dog";
    }

    // Overridden method to represent the dog's action
    @Override
    public String act() {
        String[] activities = { "Play fetch", "Bark at strangers", "Take a nap" };
        return activities[getRandom().nextInt(activities.length)];
    }
}

class Cat extends Pet {
    public Cat(String name, int age, double weight) {
        super(name, age, weight);
        setBehaviorCutoffs(40, 70, 30);
    }

    private void setBehaviorCutoffs(int eat, int sleep, int attention) {
        super.eatCutoff = eat;
        super.sleepCutoff = sleep;
        super.attentionCutoff = attention;
    }

    @Override
    public String getType() {
        return "Cat";
    }

    @Override
    public String act() {
        String[] activities = { "Play with a ball of yarn", "Take a nap", "Chase imaginary mice" };
        return activities[getRandom().nextInt(activities.length)];
    }
}

class Fish extends Pet {
    public Fish(String name, int age, double weight) {
        super(name, age, weight);
        setBehaviorCutoffs(80, 10, 5);
    }

    private void setBehaviorCutoffs(int eat, int sleep, int attention) {
        super.eatCutoff = eat;
        super.sleepCutoff = sleep;
        super.attentionCutoff = attention;
    }

    @Override
    public String getType() {
        return "Fish";
    }

    @Override
    public String act() {
        String[] activities = { "Swim around bowl", "Explore aquarium", "Stare at the outside world" };
        return activities[getRandom().nextInt(activities.length)];
    }
    }


// Class for reading pets from file
class PetReader {
     // Method to read pets from file and return an ArrayList
    public static ArrayList<Pet> readPetsFromFile(String filePath) {
        ArrayList<Pet> pets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 4) {
                    char petType = parts[0].charAt(0);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);

                    // Create pet types
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
                            System.out.println("Invalid pet type in file: " + petType);
                    }
                } else {
                    System.out.println("Invalid data in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return pets;
    }
}

// Class for writing pets to file
class PetWriter {
    public static void writeToScreen(ArrayList<Pet> pets) {
        for (Pet pet : pets) {
            System.out.println(pet);
        }
    }

    public static void writeToFile(ArrayList<Pet> pets, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Pet pet : pets) {
                String line = String.format("%s\t%s\t%d\t%.2f%n", pet.getType().charAt(0), pet.getName(),
                        pet.getAge(), pet.getWeight());
                writer.write(line);
            }
            System.out.println("Pets written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
