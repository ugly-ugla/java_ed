package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        MachineState ms = MachineState.IDLE;
        while (ms != MachineState.EXIT) {
            ms = ms.nextState();
        }
    }
}

enum CoffeeTypes {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    CoffeeTypes(int w, int m, int c, int p) {
        this.requiredWater = w;
        this.requiredMilk = m;
        this.requiredCoffee = c;
        this.price = p;
    }

    int requiredWater;
    int requiredMilk;
    int requiredCoffee;
    int price;
}

enum MachineState {

    IDLE() {
        @Override
        public MachineState nextState() {

            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String input = getInput();

            switch (input) {
                case "buy":
                    return BUY_CHOICE;
                case "fill":
                    return FILL_WATER;
                case "take":
                    return TAKE;
                case "remaining":
                    return REMAINING;
                case "exit":
                    return EXIT;
                default:
                    break;
            }
            return null;
        }
    },
    BUY_CHOICE() {
        @Override
        public MachineState nextState() {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String choice = getInput();
            switch (choice) {
                case "1":
                    makeBeverage(CoffeeTypes.ESPRESSO);
                    break;
                case "2":
                    makeBeverage(CoffeeTypes.LATTE);
                    break;
                case "3":
                    makeBeverage(CoffeeTypes.CAPPUCCINO);
                    break;
                case "back":
                default:
                    System.out.println("learn to type!");
            }
            return IDLE;
        }
    },
    FILL_WATER {
        @Override
        public MachineState nextState() {
            System.out.println("Write how many ml of water do you want to add: ");
            availableWater += Integer.parseInt(getInput());
            return FILL_MILK;
        }

    },
    FILL_MILK {
        @Override
        public MachineState nextState() {
            System.out.println("Write how many ml of milk do you want to add: ");
            availableMilk += Integer.parseInt(getInput());
            return FILL_COFFEE;
        }
    },
    FILL_COFFEE {
        @Override
        public MachineState nextState() {
            System.out.println("Write how many grams of coffee beans do you want to add: ");
            availableCoffee += Integer.parseInt(scn.nextLine());
            return FILL_CUPS;
        }
    },
    FILL_CUPS {
        @Override
        public MachineState nextState() {
            System.out.println("Write how many disposable cups of coffee do you want to add: ");
            availableCups += Integer.parseInt(getInput());
            System.out.println();
            return IDLE;
        }
    },
    TAKE {
        @Override
        public MachineState nextState() {
            System.out.println("I gave you $" + availableMoney);
            System.out.println();
            availableMoney = 0;
            return IDLE;
        }
    },
    REMAINING {
        @Override
        public MachineState nextState() {
            System.out.println("The coffee machine has");
            System.out.println(availableWater + " of water");
            System.out.println(availableMilk + " of milk");
            System.out.println(availableCoffee + " of coffee beans");
            System.out.println(availableCups + " of disposable cups");
            System.out.println("$" + availableMoney + " of money");
            System.out.println();
            return IDLE;
        }
    },
    EXIT {
        @Override
        public MachineState nextState() {
            return null;
        }
    };

    public abstract MachineState nextState();

    String getInput() {
        return scn.nextLine();
    }

    Scanner scn = new Scanner(System.in);

    static int availableWater = 400;
    static int availableMilk = 540;
    static int availableCoffee = 120;
    static int availableCups = 9;
    static int availableMoney = 550;


    private static void makeBeverage(CoffeeTypes beverage) {
        int w = beverage.requiredWater;
        int m = beverage.requiredMilk;
        int c = beverage.requiredCoffee;
        int p = beverage.price;
        int cups = 1;

        if (availableWater - w < 0) {
            System.out.println("Sorry, not enough water!\n");
            return;
        }
        if (availableMilk - m < 0) {
            System.out.println("I have negative milk, go figure...\n");
            return;
        }
        if (availableCoffee - c < 0) {
            System.out.println("Sorry, not enough coffee!\n");
            return;
        }
        if (availableCups - cups < 0) {
            System.out.println("Sorry, not enough disposable cups!\n");
            return;
        }
        System.out.println("I have enough resources, making you a coffee!\n");
        availableCups -= cups;
        availableWater -= w;
        availableMilk -= m;
        availableCoffee -= c;
        availableMoney += p;
    }
}
