package Scenarios;

import java.util.Scanner;

import Choices.TradeChoice;

import java.util.Arrays;
import Entities.Player;
import Utilities.TypewriterPrinter;

public class TraderMeeting implements Scenario {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nWandering through the ruins, you come across a well-equipped trader. Their cart is filled with various goods, offering a rare chance to replenish your supplies.");
        while (true) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Trade with the trader");
            System.out.println("2. Check equipment");
            System.out.println("3. Eat food");
            System.out.println("4. Move on without trading");
            System.out.print("Enter choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new TradeChoice().execute(player);
                    return; 
                case 2:
                    TypewriterPrinter.typeWithTypewriterEffect("\nYour current equipment: " + player.getInventory());
                    break;
                case 3:
                    consumeFoodIfAvailable(player);
                    break;
                case 4:
                    TypewriterPrinter.typeWithTypewriterEffect("\nYou decide to move on without trading.");
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