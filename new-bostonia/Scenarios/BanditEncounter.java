package Scenarios;
import Choices.CombatChoice;
import Choices.CommunicateChoice;
import Entities.Player;
import Utilities.TypewriterPrinter;

import java.util.Random;
import java.util.Scanner;

public class BanditEncounter implements Scenario {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        int banditStrength = calculateBanditStrength(player.getWeapon().getValue());
        player.setCurrentEnemyStrength(banditStrength);
        System.err.println("\n    ,'-',\n" + //
                "          :-----:\n" + //
                "      (''' , - , ''')\n" + //
                "      \\   ' .  , `  /\n" + //
                "       \\  '   ^  ? /\n" + //
                "        \\ `   -  ,'\n" + //
                "         `j_ _,'\n" + //
                "    ,- -`\\ \\  /f\n" + //
                "  ,-      \\_\\/_/'-\n" + //
                " ,                 `,\n" + //
                " ,                   ,     \n" + //
                "      /\\          \\\n" + //
                "|    /             \\   ',\n" + //
                ",   f  :           :`,  ,\n" + //
                "<...\\  ,           : ,- '\n" + //
                "\\,,,,\\ ;           : j  '\n" + //
                " \\    \\            :/^^^^'\n" + //
                "  \\    \\            ; ''':\n" + //
                "    \\   -,         -`.../\n" + //
                "     '    - -,`,--`\n" + //
                "      \\_._'-- '---:\n");
        TypewriterPrinter.typeWithTypewriterEffect("\nA bandit blocks your path. Their strength seems to be " + banditStrength);

        while (true) {
            // Player choices
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Communicate");
            System.out.println("4. Fight");
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
            TypewriterPrinter.typeWithTypewriterEffect("\nYou don't have any food to eat.");
        }
    }

    private int calculateBanditStrength(int playerWeaponDamage) {
        int lowerBound = playerWeaponDamage / 3;
        int upperBound = (int) (playerWeaponDamage * 1.1);
        return lowerBound + random.nextInt(upperBound - lowerBound + 1);
    }
}