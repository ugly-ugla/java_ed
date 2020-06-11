package machine;

import java.util.*;

public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;

        int espressoWater = 250;
        int espressoMilk = 0;
        int espressoBeans = 16;
        int espressoMoney = 4;

        int latteWater = 350;
        int latteMilk = 75;
        int latteBeans = 20;
        int latteMoney = 7;

        int cappuccinoWater = 200;
        int cappuccinoMilk = 100;
        int cappuccinoBeans = 12;
        int cappuccinoMoney = 6;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        while (!(action.equals("exit"))) {
            switch (action) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String choice = scanner.next();
                    switch (choice) {
                    case "1":
                        if (water >= espressoWater && milk >= espressoMilk && beans >= espressoBeans && cups > 0) {
                            System.out.println("I have enough resources, making you a coffee!\n");
                            water -= espressoWater;
                            milk -= espressoMilk;
                            beans -= espressoBeans;
                            cups -= 1;
                            money += espressoMoney;
                        } else if (water < espressoWater) {
                            System.out.println("Sorry, not enough water!\n");
                        } else if (milk < espressoMilk) {
                            System.out.println("Sorry, not enough milk!\n");
                        } else if (beans < espressoBeans) {
                            System.out.println("Sorry, not enough beans!\n");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups!\n");
                        }
                        break;
                    case "2":
                        if (water >= latteWater && milk >= latteMilk && beans >= latteBeans && cups > 0) {
                            System.out.println("I have enough resources, making you a coffee!\n");
                            water -= latteWater;
                            milk -= latteMilk;
                            beans -= latteBeans;
                            cups -= 1;
                            money += latteMoney;
                        } else if (water < latteWater) {
                            System.out.println("Sorry, not enough water!\n");
                        } else if (milk < latteMilk) {
                            System.out.println("Sorry, not enough milk!\n");
                        } else if (beans < latteBeans) {
                            System.out.println("Sorry, not enough beans!\n");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups!\n");
                        }
                        break;
                    case "3":
                        if (water >= cappuccinoWater && milk >= cappuccinoMilk && beans >= cappuccinoBeans && cups > 0) {
                            System.out.println("I have enough resources, making you a coffee!\n");
                            water -= cappuccinoWater;
                            milk -= cappuccinoMilk;
                            beans -= cappuccinoBeans;
                            cups -= 1;
                            money += cappuccinoMoney;
                        } else if (water < cappuccinoWater) {
                            System.out.println("Sorry, not enough water!\n");
                        } else if (milk < cappuccinoMilk) {
                            System.out.println("Sorry, not enough milk!\n");
                        } else if (beans < cappuccinoBeans) {
                            System.out.println("Sorry, not enough beans!\n");
                        } else if (cups < 1) {
                            System.out.println("Sorry, not enough cups!\n");
                        }
                        break;
                    case "back":
                        break;
                }
                    break;
                case "fill":
                    System.out.println("\nWrite how many ml of water do you want to add:");
                    water += scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    milk += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    beans += scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    cups += scanner.nextInt();
                    System.out.printf("\n");
                    break;
                case "take":
                    System.out.printf("\nI gave you $%d\n\n", money);
                    money = 0;
                    break;
                case "remaining":
                    System.out.printf("\nThe coffee machine has:\n");
                    System.out.printf("%d of water\n", water);
                    System.out.printf("%d of milk\n", milk);
                    System.out.printf("%d of coffee beans\n", beans);
                    System.out.printf("%d of disposable cups\n", cups);
                    System.out.printf("$%d of money\n\n", money);
                    break;
            }
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();
        }
    }
}
