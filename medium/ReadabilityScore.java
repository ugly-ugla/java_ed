package readability;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        final File file = new File(args[0]);
        StringBuilder text = new StringBuilder();
        String[] ageARI = {"5-6", "6-7", "7-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18", "18-24", "24+"};

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

        String[] wordsArray = t.split("[,.:;?!]?\\s");
        int syllables = syllablesCount(wordsArray);
        System.out.println("Syllables: " + syllables);

        int polysyllables = polysyllablesCount(wordsArray);
        System.out.println("Polysyllables: " + polysyllables);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();
        double score = 0;

        switch (userChoice) {
            case "ARI":
                score = getARI(sentences, words, chars);
                System.out.printf("The score is: %.2f\n", score);
                break;
            case "FK":
                score = getFK(sentences, words, syllables);
                System.out.printf("The score is: %.2f\n", score);
                break;
            case "SMOG":
                score = getSMOG(sentences, polysyllables);
                System.out.printf("The score is: %.2f\n", score);
                break;
            case "CL":
                score = getCL(sentences, words, chars);
                System.out.printf("The score is: %.2f\n", score);
                break;
            case "all":
                score = getARI(sentences, words, chars);
                System.out.printf("The score is: %.2f\n", score);
                break;
        }

        System.out.println("This text should be understood by " + ageARI[(int) Math.ceil(score) - 1] + " year olds.");
    }

    static int isPolysyllable(String word) {
        int l = word.length();
        String vowelsRegex = "[aeiouy]";
        int result = 0;
        String[] temp = word.split("");

        for (int i = 0; i < l; i++) {
            if (temp[i].matches(vowelsRegex)) {
                result++;
            }
            if (i > 0 && temp[i].matches(vowelsRegex) && temp[i - 1].matches(vowelsRegex)) {
                result--;
            }
            if (i == l - 1 && temp[i].matches("e")) {
                result--;
            }
        }
        return result == 0 ? 1 : result;
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
            result += isPolysyllable(word) > 2 ? 1 : 0;
        }
        return result;
    }

    static double getARI(int sentences, int words, int chars) {
        return (double) 4.71 * chars / words + 0.5 * words / sentences - 21.43;
    }

    static double getSMOG(int sentences, int polysyllables) {
        return (double) 1.043 * Math.sqrt(polysyllables * 30 / (double) sentences) + 3.1291;
    }

    static double getFK(int sentences, int words, int syllables) {
        return (double) 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    static double getCL(int sentences, int words, int chars) {
        return (double) 0.0588 * chars / words * 100 - 0.296 * sentences / words * 100 - 15.8;
    }
}
