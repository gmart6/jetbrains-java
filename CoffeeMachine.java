package machine;
import java.util.Scanner;

public class CoffeeMachine {
        
    private Scanner scanner;
    private int water;
    private int milk;
    private int beans;
    private int disCups;
    private int money;
    private boolean isRunning;
    
    private CoffeeMachine() {
        this.scanner = new Scanner(System.in);
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.disCups = 9;
        this.money = 550;
        this.isRunning = true;
    }

    public void fill() {
        
        System.out.println("Write how many ml of water do you want to add:");
        this.water += scanner.nextInt();
        
        System.out.println("Write how many ml of milk do you want to add:");
        this.milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.beans += scanner.nextInt();
        
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.disCups += scanner.nextInt();
        
        System.out.println();
    
    }
    
    public void status() {
        
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water%n", water);
        System.out.printf("%d of milk%n", milk);
        System.out.printf("%d of coffee beans%n", beans);
        System.out.printf("%d of disposable cups%n", disCups);
        System.out.printf("%d of money%n", money);
        System.out.println();

    }
    
    public void take() {
        System.out.printf("I gave you $%d%n", money);
        money = 0;
        System.out.println();

    }
    
    public void buy() {
        
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String selectionStr = scanner.nextLine();
        int selection = 0;
        
        int noMilk = 1;
        
        int[]possibles = new int[4];
        
        if (selectionStr.equals("back")) {
            return;
        } else {
            selection = Integer.parseInt(selectionStr);
        }
    
        if (selection == 1) {
            possibles[0] = (water / 250);
            possibles[1] = (beans / 16);
            possibles[2] = (disCups);
        } else if (selection == 2) {
            possibles[0] = (water / 350);
            possibles[1] = (beans / 20);
            possibles[2] = (disCups);
            possibles[3] = (milk / 75);

            noMilk = 0;
        } else if (selection == 3) {
            possibles[0] = (water / 200);
            possibles[1] = (beans / 12);
            possibles[2] = (disCups);
            possibles[3] = (milk / 100);

            noMilk = 0;
        }
        
        int possCups = possibles[0];
        
        for (int i = 1; i < possibles.length - noMilk; i++) {
            if (possibles[i] <= possCups) {
                possCups = possibles[i];
            }
        }
        
        if (possCups > 0) {
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println();
        } else {
            int limitingFac = 0;
            for (int i = 0; i < possibles.length - noMilk; i++) {
                if (possibles[i] == 0) {
                    limitingFac = i;
                    break;
                }
            }
            String[] limitingFacs = {"water", "beans", "disposable cups", "milk"};
        
            System.out.printf("Sorry, not enough %s!%n", limitingFacs[limitingFac]);
            System.out.println();

            return;
        }
        
        if (selection == 1) {
            water -= 250;
            beans -= 16;
            disCups--;
            money += 4;
        } else if (selection == 2) {
            water -= 350;
            milk -= 75;
            beans -= 20;
            disCups--;
            money += 7;
        } else if (selection == 3) {
            water -= 200;
            milk -= 100;
            beans -=12;
            disCups--;
            money += 6;
        }
    }
    
    public void run() {
        
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            System.out.println();
            String action = scanner.nextLine();

            if (action.equals("remaining")) {
                this.status();
            } else if (action.equals("buy")) {
                this.buy();
            } else if (action.equals("fill")) {
                this.fill();
            } else if (action.equals("take")) {
                this.take();
            } else if (action.equals("exit")) {
                this.isRunning = false;
                break;
            }
        }
        
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        
        while (coffeeMachine.isRunning) {
            coffeeMachine.run();
        }
    
    }
    
}
