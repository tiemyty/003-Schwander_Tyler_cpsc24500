import java.util.ArrayList;
import java.util.Scanner;

//Class for comments
class Comment {
    private String commenterName;
    private String date;
    private String commentText;

    //Constructor
    public Comment(String commenterName, String date, String commentText) {
        this.commenterName = commenterName;
        this.date = date;
        this.commentText = commentText;
    }

    //Getters
    public String getCommenterName() {
        return commenterName;
    }

    public String getDate() {
        return date;
    }

    public String getCommentText() {
        return commentText;
    }

    @Override
    public String toString() {
        return "Comment by " + commenterName + " on " + date + ":\n" + commentText;
    }
}

//Artwork
class ArtisticWork {
    private String creatorName;
    private String datePosted;
    private String title;
    private String description;
    private ArrayList<Comment> comments;

    //Constructor
    public ArtisticWork(String creatorName, String datePosted, String title, String description) {
        this.creatorName = creatorName;
        this.datePosted = datePosted;
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    //Getters
    public String getCreatorName() {
        return creatorName;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Creator: " + creatorName + "\nDate Posted: " + datePosted +
                "\nTitle: " + title + "\nDescription: " + description +
                "\nComments: " + comments.size() + " comments";
    }
}
//Recorded
class RecordedArtisticWork extends ArtisticWork {
    private int durationInSeconds;
    private String filename;
    private double fileSizeInMegabytes;

    //Constructor
    public RecordedArtisticWork(String creatorName, String datePosted, String title, String description,
                                int durationInSeconds, String filename, double fileSizeInMegabytes) {
        super(creatorName, datePosted, title, description);
        this.durationInSeconds = durationInSeconds;
        this.filename = filename;
        this.fileSizeInMegabytes = fileSizeInMegabytes;
    }

    //Getters
    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public String getFilename() {
        return filename;
    }

    public double getFileSizeInMegabytes() {
        return fileSizeInMegabytes;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDuration: " + durationInSeconds + " seconds" +
                "\nFilename: " + filename + "\nFile Size: " + fileSizeInMegabytes + " MB";
    }
}
//Songs
class Song extends RecordedArtisticWork {
    private int beatsPerMinute;
    private String key;

    //Constructor
    public Song(String creatorName, String datePosted, String title, String description,
                int durationInSeconds, String filename, double fileSizeInMegabytes,
                int beatsPerMinute, String key) {
        super(creatorName, datePosted, title, description, durationInSeconds, filename, fileSizeInMegabytes);
        this.beatsPerMinute = beatsPerMinute;
        this.key = key;
    }

    //Getters
    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return super.toString() + "\nBeats Per Minute: " + beatsPerMinute + "\nKey: " + key;
    }
}
//Movie class
class Movie extends RecordedArtisticWork {
    private int frameRate;
    private String resolution;

    //Constructor
    public Movie(String creatorName, String datePosted, String title, String description,
                 int durationInSeconds, String filename, double fileSizeInMegabytes,
                 int frameRate, String resolution) {
        super(creatorName, datePosted, title, description, durationInSeconds, filename, fileSizeInMegabytes);
        this.frameRate = frameRate;
        this.resolution = resolution;
    }

    //Getters
    public int getFrameRate() {
        return frameRate;
    }

    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFrame Rate: " + frameRate + "\nResolution: " + resolution;
    }
}

class WrittenArtisticWork extends ArtisticWork {
    private String language;
    private String text;

    //Constructor
    public WrittenArtisticWork(String creatorName, String datePosted, String title, String description,
                               String language, String text) {
        super(creatorName, datePosted, title, description);
        this.language = language;
        this.text = text;
    }

    //Getters
    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLanguage: " + language + "\nText: " + text;
    }
}
//By this point its the same general pattern of code, class per object; poem, movie, comment, etc, and then the constructors and getters.
class Poem extends WrittenArtisticWork {
    private String meter;

    public Poem(String creatorName, String datePosted, String title, String description,
                String language, String text, String meter) {
        super(creatorName, datePosted, title, description, language, text);
        this.meter = meter;
    }

    public String getMeter() {
        return meter;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMeter: " + meter;
    }
}

class ShortStory extends WrittenArtisticWork {
    private String settingDescription;

    public ShortStory(String creatorName, String datePosted, String title, String description,
                      String language, String text, String settingDescription) {
        super(creatorName, datePosted, title, description, language, text);
        this.settingDescription = settingDescription;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSetting Description: " + settingDescription;
    }
}
//Main parent class
public class Muse{
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<ArtisticWork> posts = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMessage();

        int choice;
        do {
            //Menu for all options on Muse
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    createNewPost();
                    break;
                case 2:
                    commentOnPost();
                    break;
                case 3:
                    System.out.println("Thank you for using MUSE. Be inspired to inspire all everywhere always");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 3);
    }

    private static void displayWelcomeMessage() {
        System.out.println("********************************************************************************");
        System.out.println("                        MUSE Social Media Platform, v1.0                        ");
        System.out.println("********************************************************************************");
        System.out.println("\nWelcome to MUSE, the social media platform where artists and authors of all");
        System.out.println("kinds and from all over the world come together to share their inspirations and");
        System.out.println("ideas. Post your own original works and comment on what others are doing. Only");
        System.out.println("one rule: be kind!\n");
    }

    private static void displayMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create a new post");
        System.out.println("2. Comment on a post");
        System.out.println("3. Quit");
        System.out.print("Enter the number of your choice: ");
    }

    //Starts sequence of what to post and direction to take you
    private static void createNewPost() {
        System.out.println("\nWhat would you like to a post?");
        System.out.println("1. Song");
        System.out.println("2. Movie");
        System.out.println("3. Poem");
        System.out.println("4. Short story");
        System.out.print("Enter the number of your choice: ");

        int postType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the name of the creator: ");
        String creatorName = scanner.nextLine();

        System.out.print("Enter the date: ");
        String datePosted = scanner.nextLine();

        System.out.print("Enter the title: ");
        String title = scanner.nextLine();

        System.out.print("Enter a description: ");
        String description = scanner.nextLine();

        switch (postType) {
            case 1:
                createSong(creatorName, datePosted, title, description);
                break;
            case 2:
                createMovie(creatorName, datePosted, title, description);
                break;
            case 3:
                createPoem(creatorName, datePosted, title, description);
                break;
            case 4:
                createShortStory(creatorName, datePosted, title, description);
                break;
            default:
                System.out.println("Invalid post type.");
        }
    }

    private static void createSong(String creatorName, String datePosted, String title, String description) {
        System.out.print("Enter duration: ");
        int durationInSeconds = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        System.out.print("Enter file size: ");
        double fileSizeInMegabytes = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Enter beats per minute: ");
        int beatsPerMinute = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        Song song = new Song(creatorName, datePosted, title, description, durationInSeconds, filename,
                fileSizeInMegabytes, beatsPerMinute, key);
        posts.add(song);

        System.out.println("\nSong created successfully!\n");
    }

    private static void createMovie(String creatorName, String datePosted, String title, String description) {
        System.out.print("Enter duration: ");
        int durationInSeconds = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();

        System.out.print("Enter file size: ");
        double fileSizeInMegabytes = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter framerate: ");
        int frameRate = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter resolution: ");
        String resolution = scanner.nextLine();

        Movie movie = new Movie(creatorName, datePosted, title, description, durationInSeconds, filename,
                fileSizeInMegabytes, frameRate, resolution);
        posts.add(movie);

        System.out.println("\nMovie created successfully!\n");
    }

    private static void createPoem(String creatorName, String datePosted, String title, String description) {
        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        System.out.print("Enter text of the post: ");
        String text = scanner.nextLine();

        System.out.print("Enter meter: ");
        String meter = scanner.nextLine();

        Poem poem = new Poem(creatorName, datePosted, title, description, language, text, meter);
        posts.add(poem);

        System.out.println("\nPoem created successfully!\n");
    }

    private static void createShortStory(String creatorName, String datePosted, String title, String description) {
        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        System.out.print("Enter text of the post: ");
        String text = scanner.nextLine();

        System.out.print("Enter description of the setting: ");
        String settingDescription = scanner.nextLine();

        ShortStory shortStory = new ShortStory(creatorName, datePosted, title, description, language, text, settingDescription);
        posts.add(shortStory);

        System.out.println("\nShort Story created successfully!\n");
    }

    private static void commentOnPost() {
        if (posts.isEmpty()) {
            System.out.println("No posts available to comment on.\n");
            return;
        }

        System.out.println("\nSelect a post to comment on:");
        displayPosts();

        int postIndex = scanner.nextInt();
        scanner.nextLine();

        if (postIndex >= 0 && postIndex < posts.size()) {
            ArtisticWork selectedPost = posts.get(postIndex);

            System.out.print("Enter your name: ");
            String commenterName = scanner.nextLine();

            System.out.print("Enter the date: ");
            String date = scanner.nextLine();

            System.out.print("Enter your comment: ");
            String commentText = scanner.nextLine();

            Comment comment = new Comment(commenterName, date, commentText);
            selectedPost.addComment(comment);

            System.out.println("\nComment added successfully!\n");
        } else {
            System.out.println("Invalid post selection.\n");
        }
    }

    private static void displayPosts() {
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(i + ". " + posts.get(i).getTitle());
        }
    }
}
