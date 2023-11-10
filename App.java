import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Main method that draws from the other two classes
        System.out.println("****************************************************");
        System.out.println("                  STORYTELLER V1.0                  ");
        System.out.println("****************************************************");
        System.out.println(
                "\nWelcome to StoryTeller, a sophisticated electronic author. \nThis program reads from a list of words of various parts of speech and creates a story from them.");
        System.out.println(
                "You can tune the richness of the writing by changing how frequently adjectives, adverbs, and prepositions should be included.\n");

        // Takes input for reading file
        System.out.print("Enter the name of the word file: ");
        String wordFileName = scanner.next();

        // Initializing Author class to create array lists which will then use
        // wordfilereader
        Author author = new Author();
        author.initialize(wordFileName);

        // Determining frequency of choices.
        while (true) {
            System.out.print("\nHow many sentences would you like in your story? ");
            int numSentences = scanner.nextInt();

            // I kinda wrote myself into a corner and I don't know how to make an error
            // catch without rewriting all of this.
            System.out.println("On a scale of 0 to 10 ...");
            System.out.print("  How frequently should adjectives be used? ");
            int adjectiveFrequency = scanner.nextInt();

            System.out.print("  How frequently should adverbs be used? ");
            int adverbFrequency = scanner.nextInt();

            System.out.print("  How frequently should prepositions be used? ");
            int prepositionFrequency = scanner.nextInt();

            // Using Author class to generate the story
            String story = author.generateStory(adjectiveFrequency, adverbFrequency, prepositionFrequency,
                    numSentences);
            System.out.println("\nHere it is:\n" + story);

            // Saying y continues and n breaks.
            System.out.print("Do you want to create another story? (y or n): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("y")) {
                break;
            }
        }

        System.out.println("Thank you for using StoryTeller!");
        scanner.close();
    }
}
