package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int sourceRadix = scanner.nextInt();
            String[] sourceNumber = scanner.next().split("\\.");
            int targetRadix = scanner.nextInt();
            int integerPart = sourceRadix == 1 ? sourceNumber[0].length() : Integer.parseInt(sourceNumber[0], sourceRadix);
            if (targetRadix < 1 || targetRadix > 36) {
                System.out.println("error");
            } else if (targetRadix == 1) {
                for (int i = 0; i < integerPart; i++) {
                    System.out.print("1");
                }
                System.out.println();
            } else {
                System.out.print(Integer.toString(integerPart, targetRadix));

                if (sourceNumber.length == 2) {
                    char[] fractionalPartChars = sourceNumber[1].toCharArray();
                    double fractionalPart = 0;

                    for (int i = 0; i < fractionalPartChars.length; i++) {
                        fractionalPart += Character.getNumericValue(fractionalPartChars[i]) / Math.pow(sourceRadix, i + 1);
                    }
                    StringBuilder fractionalPartOut = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        fractionalPart *= targetRadix;
                        fractionalPartOut.append(Character.forDigit((int) fractionalPart, targetRadix));
                        fractionalPart -= (int) fractionalPart;
                    }
                    System.out.print("." + fractionalPartOut.toString());
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
