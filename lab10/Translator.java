import java.io.*;
import java.util.*;

public class Translator {
    private static final int MAX_HAMMING_DISTANCE = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] dictionary = loadDictionary("dictionary.txt");

        if (dictionary == null) {
            System.out.println("Blad podczas wczytywania słownika.");
            return;
        }

        boolean continueTranslating = true;

        while (continueTranslating) {
            System.out.println("Wybierz jezyk zrodlowy: 1 - angielski, 2 - hiszpanski, 3 - wloski");
            int languageIndex = scanner.nextInt() - 1;
            scanner.nextLine();

            if (languageIndex < 0 || languageIndex >= 3) {
                System.out.println("Niepoprawny wybor jezyka.");
                continue;
            }

            System.out.println("Wprowadz słowo do przetlumaczenia:");
            String word = scanner.nextLine().toLowerCase();

            String[] translations = findTranslations(dictionary, word, languageIndex);
            if (translations == null) {
                System.out.println("Nie znaleziono dokladnego tlumaczenia.");
                String similarWord = findSimilarWord(dictionary, word, languageIndex);
                if (similarWord != null) {
                    System.out.println("Czy miales na mysli: " + similarWord + "? (tak/nie)");
                    String response = scanner.nextLine().toLowerCase();
                    if (response.equals("tak")) {
                        translations = findTranslations(dictionary, similarWord, languageIndex);
                    }
                }
            }

            if (translations != null) {
                System.out.println("Tlumaczenia:");
                if (languageIndex != 0) {
                    System.out.println("Angielski: " + translations[0]);
                }
                if (languageIndex != 1) {
                    System.out.println("Hiszpanski: " + translations[1]);
                }
                if (languageIndex != 2) {
                    System.out.println("Wloski: " + translations[2]);
                }
            } else {
                System.out.println("Nie znaleziono tlumaczenia.");
            }

            System.out.println("Czy chcesz przetlumaczyć kolejne słowo? (tak/nie)");
            String response = scanner.nextLine().toLowerCase();
            continueTranslating = response.equals("tak");
        }

        scanner.close();
    }

    // metoda do wczytywania z pliku
    private static String[][] loadDictionary(String fileName) {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                rows.add(tokens);
            }
        } catch (IOException e) {
            System.out.println("Blad podczas odczytu pliku: " + e.getMessage());
            return null;
        }

        return rows.toArray(new String[0][]);
    }

    // metoda do szukania dokladnego tlumaczania
    private static String[] findTranslations(String[][] dictionary, String word, int languageIndex) {
        for (String[] entry : dictionary) {
            if (entry[languageIndex].equalsIgnoreCase(word)) {
                return entry;
            }
        }
        return null;
    }

    // metoda do szukania podobnego tlumaczenia
    private static String findSimilarWord(String[][] dictionary, String word, int languageIndex) {
        for (int i = 1; i <= MAX_HAMMING_DISTANCE; i++) {
            for (String[] entry : dictionary) {
                String dictionaryWord = entry[languageIndex];
                if (calculateHammingDistance(word, dictionaryWord) <= i) {
                    return dictionaryWord;
                }
            }
        }

        return null;
    }

    // metoda do liczenia odleglosci
    private static int calculateHammingDistance(String word1, String word2) {
        int lengthDifference = Math.abs(word1.length() - word2.length());
        int minLength = Math.min(word1.length(), word2.length());
        int distance = lengthDifference;

        for (int i = 0; i < minLength; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }
}