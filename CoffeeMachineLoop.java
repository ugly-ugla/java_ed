package machine;

mport java.util.*;

public class CoffeeMachine {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine;
        machine.cycle();
    }

    void cycle() {
        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        while (action.equals("exit")) {
            switch (action) {
                case "buy":
                    machine.buy();
                    break;
                case "fill":
                    machine.fill();
                    break;
                case "take":
                    machine.take();
                    break;
                case "remaining":
                    machine.remaining();
                    break;
            }
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();
        }
    }

    void remaining(int water, int milk, int beans, int cups, int money) {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", beans);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("$%d of money\n", money);
    }
    static void take(money) {
        System.out.printf("\nI gave you $%d", money);
        money = 0;
    }
}
