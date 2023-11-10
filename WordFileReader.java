import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class WordFileReader {

    //Parses file and seperates words based on code words in the text file being "n", "v", etc.
    public void loadWords(String wordFileName, List<String> nouns, List<String> verbs, List<String> adjectives,
            List<String> adverbs, List<String> prepositions) {
        try (BufferedReader reader = new BufferedReader(new FileReader(wordFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String pos = parts[1].trim().toLowerCase();

                    switch (pos) {
                        case "n":
                            nouns.add(word);
                            break;
                        case "v":
                            verbs.add(word);
                            break;
                        case "adj":
                            adjectives.add(word);
                            break;
                        case "adv":
                            adverbs.add(word);
                            break;
                        case "prep":
                            prepositions.add(word);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
