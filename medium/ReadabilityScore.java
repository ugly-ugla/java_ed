package readability;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        final File file = new File(args[0]);
        StringBuilder text = new StringBuilder();
        String[] age = {"5-6", "6-7", "7-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18", "18-24", "24+"};

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                text.append(sc.nextLine() + " ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.println("The text is:");
        System.out.println(text);
        System.out.println("");

        String t = text.toString();

        int words = t.split("\\s").length;
        System.out.println("Words: " + words);

        int sentences = t.split("[.!?] ?").length;
        System.out.println("Sentences: " + sentences);

        int chars = t.replaceAll("[\\s\\n]", "").length();
        System.out.println("Characters: " + chars);

        int syllables = syllablesCount(words);
        System.out.println("Syllables: " + syllables);

        int polysyllables = polysyllablesCount(words);
        System.out.println("Polysyllables: " + polysyllables);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        printScore

        double score = getARI(sentences, words, chars);
        System.out.printf("The score is: %.2f\n", score);

        System.out.println("This text should be understood by " + age[(int) Math.ceil(score) - 1] + " year olds.");
    }

    static int isPolysyllable(String word) {
        int l = word.length();

        for (int i = 0; i < l; i++) {
            if ("[aeiuoy]".equals(word[i])) {
                temp++;
            if (i > 0 && "[aeiuoy]".equals(word[i - 1])) {
                temp--;
            if ("e".equals(word[i]) && i == word.length() - 1) {
                temp--;
            }
        }
        return temp == 0 ? 1 : temp;
    }

    static int syllablesCount(String[] words) {
        int result = 0;

        for (String word : words) {
            int syllables = isPolysyllable(word);
            result += syllables;
        }

        return result;
    }

    static int polysyllablesCount(String[] words) {
        int result = 0;

        for (String word : words) {
            result += isPolysyllable(word) == 1 ? 0 : 1;
        }

        return result;
    }

    static double getARI(int sentences, int words, int chars) {
        return (double) 4.71 * chars / words + 0.5 * words / sentences - 21.43;
    }

    static double getFK(int sentences, int polysyllables) {
        return (double) 1.043 * Math.sqrt(polysyllables * 30 / sentences) + 3.1291;
    }

    static double getSMOG(int words, int sentences, int chars) {
        return (double) 4.71 * chars / words + 0.5 * words / sentences - 21.43;
    }

    static double getCL(int sentences, int words, int chars) {
        return (double) 0.0588 * chars / words * 100 - 0.296 * sentences / words * 100 - 15.8;
    }
}
