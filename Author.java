import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Author {
    private List<String> nouns;
    private List<String> verbs;
    private List<String> adjectives;
    private List<String> adverbs;
    private List<String> prepositions;

    private Random random;

    // ArrayLists to create the story
    public Author() {
        nouns = new ArrayList<>();
        verbs = new ArrayList<>();
        adjectives = new ArrayList<>();
        adverbs = new ArrayList<>();
        prepositions = new ArrayList<>();
        random = new Random();
    }

    // Initializing a class of WordFileReader to parse file and add to seperate stringbuilder lists
    public void initialize(String wordFileName) {
        WordFileReader wordFileReader = new WordFileReader();
        wordFileReader.loadWords(wordFileName, nouns, verbs, adjectives, adverbs, prepositions);
    }

    public String generateStory(int adjectiveFrequency, int adverbFrequency, int prepositionFrequency,
            int numSentences) {
        StringBuilder story = new StringBuilder();

        // Generates sentences based on how many user selected
        for (int i = 0; i < numSentences; i++) {
            String sentence = generateSentence(adjectiveFrequency, adverbFrequency, prepositionFrequency);
            story.append(sentence).append("\n");
        }
        return story.toString();
    }

    private String generateSentence(int adjectiveFrequency, int adverbFrequency, int prepositionFrequency) {
        StringBuilder sentence = new StringBuilder("The");

        if (random.nextInt(10) < adjectiveFrequency) {
            sentence.append(" ").append(getRandomWord(adjectives));
        }

        sentence.append(" ").append(getRandomWord(nouns));
        sentence.append(" ").append(getRandomWord(verbs));

        if (random.nextInt(10) < adverbFrequency) {
            sentence.append(" ").append(getRandomWord(adverbs));
        }

        if (random.nextInt(10) < prepositionFrequency) {
            sentence.append(" ").append(getRandomWord(prepositions));
            sentence.append(" the ").append(getRandomWord(nouns));
        }

        sentence.append(".");
        return sentence.toString();
    }

    private String getRandomWord(List<String> wordList) {
        if (wordList.isEmpty()) {
            return "";
        }
        return wordList.get(random.nextInt(wordList.size()));
    }
}
