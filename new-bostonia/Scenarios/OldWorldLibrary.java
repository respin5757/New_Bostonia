package Scenarios;
import java.util.Scanner;

import Choices.AvoidExploration;
import Choices.ExploreChoice;
import Entities.Player;
import Factories.RandomScenarioFactory;
import Utilities.TypewriterPrinter;
public class OldWorldLibrary implements Scenario {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nYou come across a dilapidated building. Upon closer inspection, you realize it's an old library, its shelves still stocked with books. The musty air hints at forgotten knowledge waiting to be rediscovered.");
        RandomScenarioFactory factory = new RandomScenarioFactory(1);
        while (true) {
            // Player choices
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Explore the library");
            System.out.println("4. Avoid exploring and move on");
            System.out.print("Enter choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TypewriterPrinter.typeWithTypewriterEffect("\nYour current equipment: " + player.getInventory());
                    break;
                case 2:
                    consumeFoodIfAvailable(player);
                    break;
                case 3:
                    new ExploreChoice(factory).execute(player); 
                    return;
                case 4:
                    new AvoidExploration().execute(player); 
                    return;
                default:
                    TypewriterPrinter.typeWithTypewriterEffect("\nInvalid choice. Please choose again.");
                    break;
            }
        }
    }

    private void consumeFoodIfAvailable(Player player) {
        if (player.hasFood()) {
            player.eatFood();
            TypewriterPrinter.typeWithTypewriterEffect("\nYou consume some food and feel better.");
        } else {
            TypewriterPrinter.typeWithTypewriterEffect("\nYou have no food to eat.");
        }
    }

}