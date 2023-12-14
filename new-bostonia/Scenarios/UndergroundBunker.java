package Scenarios;
import java.util.Scanner;

import Choices.AvoidExploration;
import Choices.ExploreChoice;
import Entities.Player;
import Factories.RandomScenarioFactory;
import Utilities.TypewriterPrinter;

public class UndergroundBunker implements Scenario {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nWhile wandering through the desolate landscape, you stumble upon a concealed entrance to an underground bunker. The air is still, and the silence suggests it may have been untouched for years.");
        RandomScenarioFactory factory = new RandomScenarioFactory(1);
        while (true) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Explore the bunker");
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
