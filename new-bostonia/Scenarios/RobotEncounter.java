package Scenarios;
import java.util.Random;
import java.util.Scanner;

import Choices.CombatChoice;
import Choices.CommunicateChoice;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class RobotEncounter implements Scenario {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nAs you navigate through the desolate landscape, you notice a solitary robot approaching. Its design is unfamiliar, and its intentions are not immediately clear.");
        int robotStrength = randomizeRobotStrength(player.getWeapon().getValue());
        player.setCurrentEnemyStrength(robotStrength);
        TypewriterPrinter.typeWithTypewriterEffect("\nThe robot's strength appears to be " + robotStrength);
        System.err.println("\n  o   o\n" + //
                "     )-(\n" + //
                "    (O O)\n" + //
                "     \\=/\n" + //
                "    .-\"-.\n" + //
                "   //\\ /\\\\\n" + //
                " _// / \\ \\\\_\n" + //
                "=./ {,-.} \\.=\n" + //
                "    || ||\n" + //
                "    || ||    \n" + //
                "  __|| ||__  \n" + //
                " `---\" \"---'");
        while (true) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Communicate with the robot");
            System.out.println("4. Fight the robot");
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
                    new CommunicateChoice().execute(player);
                    return; 
                case 4:
                    new CombatChoice().execute(player);
                    return; 
                default:
                    TypewriterPrinter.typeWithTypewriterEffect("\nInvalid choice. The robot prepares to engage!");
                    new CombatChoice().execute(player);
                    return; 
            }
        }
    }

    private int randomizeRobotStrength(int playerWeaponDamage) {
        int lowerBound = playerWeaponDamage / 3;
        int upperBound = (int) (playerWeaponDamage * 1.1);
        return lowerBound + random.nextInt(upperBound - lowerBound + 1);
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