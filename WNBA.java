import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WNBA {
    // Split into different conferences and both conferences
    private static ArrayList<String> easternConference = new ArrayList<>();
    private static ArrayList<String> westernConference = new ArrayList<>();
    private static ArrayList<Team> teams = new ArrayList<>();

    class Team {
        // I couldn't think of another way to read and seperate the files into the
        // eastern and western conferences without creating a class for the teams that I
        // can split all this information into.
        // Getters and Setters
        private String name;
        private int wins;
        private int losses;

        public Team(String name, int wins, int losses) {
            this.name = name;
            this.wins = wins;
            this.losses = losses;
        }

        public String getName() {
            return name;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public double getwrpct() {
            return calculatewrpct();

        }

        public double calculatewrpct() {
            return (double) wins / (wins + losses);

        }

        public double getGamesBack(Team leadingTeam) {
            double gb;
            gb = (leadingTeam.getWins() - wins);
            return gb;
        }
    }

    public void run() {
        // Heading
        System.out.println("***************************************************");
        System.out.println("               2023 WNBA STANDINGS");
        System.out.println("***************************************************");

        Scanner myobj = new Scanner(System.in);
        System.out.print("Enter the standings filename: ");
        String fileName = myobj.nextLine();

        // Try catch reads the inputted file and parses each character between spaces as
        // team, wins, and losses into seperate variables.
        Scanner inputFile = null;
        try {
            File file = new File(fileName);
            inputFile = new Scanner(file);
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                String[] parts = line.split("\t");
                if (parts.length >= 3) {
                    String teamName = parts[0];
                    int wins = Integer.parseInt(parts[1]);
                    int losses = Integer.parseInt(parts[2]);
                    Team team = new Team(teamName, wins, losses);
                    // adds this information into team objects in the team class.
                    teams.add(team);
                    // I was having trouble splitting the teams into seperate conferences, but this
                    // function splits them depending on the eastern team names.
                    if (isEasternTeam(teamName)) {
                        easternConference.add(teamName);
                    } else {
                        westernConference.add(teamName);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            myobj.close();
            return;
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
        }
        display();
        myobj.close();
    }

    // Seperates the teams into conferences
    private boolean isEasternTeam(String teamName) {
        // makes it simpler
        teamName = teamName.toLowerCase();
        // writing specific names for all the eastern teams made it easier for me to
        // call out specific teams and anything that doesn't match goes to western.
        return  teamName.contains("chicago") ||
                teamName.contains("connecticut") ||
                teamName.contains("washington") ||
                teamName.contains("new york") ||
                teamName.contains("atlanta") ||
                teamName.contains("indiana");
    }

    public void display() {
        // displays the menu
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.print("The teams have been read.");
        // do while lets me repeat the selections until user exits. Switch case lets me
        // determine the outcome based on input.
        do {
            System.out.println("What would you like to see?");
            System.out.println("1. Eastern Conference");
            System.out.println("2. Western Conference");
            System.out.println("3. Combined");
            System.out.println("4. Exit");
            System.out.print("Enter the number of your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayEasternConference();
                    break;
                case 2:
                    displayWesternConference();
                    break;
                case 3:
                    displayConference();
                    break;
                case 4:
                    System.out.println("Thank you for using this program.");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");

            }
        } while (choice != 4);
    }

    // This function displays eastern teams
    // In all 3 functions I use printf to format 20 characters and then 8 for the rest.
    public void displayEasternConference() {
        System.out.println("Standings for the Eastern Conference:");
        System.out.printf("%-20s%-8s%-8s%-8s%-8s\n", "Team Name", "Wins", "Losses", "PCT", "GB");
        //index 0 is leading team and lets me calculate games back for eastern and western
        Team leadingTeam = teams.get(0);
        for (String teamName : easternConference) {
            for (Team team : teams) {
                if (team.getName().equals(teamName)) {
                    String wrs = String.format("%.2f", team.getwrpct());
                    System.out.printf("%-20s%-8d%-8d%-8s%-8.2f\n", team.getName(), team.getWins(), team.getLosses(), wrs, team.getGamesBack(leadingTeam));
                }
            }
        }
    }

    public void displayWesternConference() {
        System.out.println("Standings for the Western Conference:");
        System.out.printf("%-20s%-8s%-8s%-8s%-8s\n", "Team Name", "Wins", "Losses", "PCT", "GB");
        Team leadingTeam = teams.get(0);
        for (String teamName : westernConference) {
            for (Team team : teams) {
                if (team.getName().equals(teamName)) {
                    String wrs = String.format("%.2f", team.getwrpct());
                    System.out.printf("%-20s%-8d%-8d%-8s%-8.2f\n", team.getName(), team.getWins(), team.getLosses(), wrs, team.getGamesBack(leadingTeam));
                }
            }
        }
    }

    // displays both
    public void displayConference() {
        System.out.println("Combined Conference Standings:");
        System.out.printf("%-20s%-8s%-8s%-8s%-8s\n", "Team Name", "Wins", "Losses", "PCT", "GB");

        //I have to use collections and index 0 to sort by winrate percentage. 
        Collections.sort(teams, (team1, team2) -> Double.compare(team2.getwrpct(), team1.getwrpct()));
        Team leadingTeam = teams.get(0);
        for (Team team : teams) {
            String teamName = team.getName();
            String wrs = String.format("%.2f", team.getwrpct());
            System.out.printf("%-20s%-8d%-8d%-8s%-8.2f\n", teamName, team.getWins(), team.getLosses(), wrs, team.getGamesBack(leadingTeam));
        }

    }

    // I couldn't make the function work without creating and running a new instance
    // of the class outside of main. Any chance I can get an explaination on this?
    public static void main(String[] args) {
        WNBA wnba = new WNBA();
        wnba.run();
    }
}