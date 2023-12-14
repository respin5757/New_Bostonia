package Scenarios;
import java.util.Random;
import java.util.Scanner;

import Choices.CombatChoice;
import Choices.FleeChoice;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class WildAnimalAttack implements Scenario {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nAs you cautiously navigate the rugged terrain, a mutated wild animal, its form twisted and unfamiliar, suddenly leaps out from the bushes!");
        System.err.println("\n        _\n" + //
                "       / \\      _-'\n" + //
                "     _/|  \\-''- _ /\n" + //
                "__-' { |          \\\n" + //
                "    /             \\\n" + //
                "    /       \"o.  |o }\n" + //
                "    |            \\ ;\n" + //
                "                  ',\n" + //
                "       \\_         __\\\n" + //
                "         ''-_    \\.//\n" + //
                "           / '-____'\n" + //
                "          /\n" + //
                "        _'\n" + //
                "      _-'");
        int animalStrength = randomizeAnimalStrength(player.getWeapon().getValue());
        player.setCurrentEnemyStrength(animalStrength);
        TypewriterPrinter.typeWithTypewriterEffect("\nThe wild animal's strength appears to be " + animalStrength);

        while (true) {
            // Player choices
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Fight the animal");
            System.out.println("4. Flee from the animal");
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
                    new CombatChoice().execute(player);
                    return; 
                case 4:
                    new FleeChoice().execute(player);
                    return; 
                default:
                    TypewriterPrinter.typeWithTypewriterEffect("\nInvalid choice. The animal attacks!");
                    new CombatChoice().execute(player);
                    return; 
            }
        }
    }

    private int randomizeAnimalStrength(int playerWeaponDamage) {
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