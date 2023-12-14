package Scenarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Choices.CombatChoice;
import Choices.FleeChoice;
import Choices.CommunicateChoice;
import Choices.ExploreChoice;
import Choices.TradeChoice;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class MutantMonsterAttack implements Scenario {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initiate(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nSuddenly, a grotesque mutant creature lunges at you from the darkness!");
        System.err.println("\n                                             ,--,  ,.-.\n" + //
                "               ,                   \\,       '-,-`,'-.' | ._\n" + //
                "              /|           \\    ,   |\\         }  )/  / `-,',\n" + //
                "              [ ,          |\\  /|   | |        /  \\|  |/`  ,`\n" + //
                "              | |       ,.`  `,` `, | |  _,...(   (      .',\n" + //
                "              \\  \\  __ ,-` `  ,  , `/ |,'      Y     (   /_L\\\n" + //
                "               \\  \\_\\,``,   ` , ,  /  |         )         _,/\n" + //
                "                \\  '  `  ,_ _`_,-,<._.<        /         /\n" + //
                "                 ', `>.,`  `  `   ,., |_      |         /\n" + //
                "                   \\/`  `,   `   ,`  | /__,.-`    _,   `\\\n" + //
                "               -,-..\\  _  \\  `  /  ,  / `._) _,-\\`       \\\n" + //
                "                \\_,,.) /\\    ` /  / ) (-,, ``    ,        |\n" + //
                "               ,` )  | \\_\\       '-`  |  `(               \\\n" + //
                "              /  /```(   , --, ,' \\   |`<`    ,            |\n" + //
                "             /  /_,--`\\   <\\  V /> ,` )<_/)  | \\      _____)\n" + //
                "       ,-, ,`   `   (_,\\ \\    |   /) / __/  /   `----`\n" + //
                "      (-, \\           ) \\ ('_.-._)/ /,`    /\n" + //
                "      | /  `          `/ \\\\ V   V, /`     /\n" + //
                "   ,--\\(        ,     <_/`\\\\     ||      /\n" + //
                "  (   ,``-     \\/|         \\-A.A-`|     /\n" + //
                " ,>,_ )_,..(    )\\          -,,_-`  _--`\n" + //
                "(_ \\|`   _,/_  /  \\_            ,--`\n" + //
                " \\( `   <.,../`     `-.._   _,-`");
        int monsterStrength = randomizeMonsterStrength(player.getWeapon().getValue());
        player.setCurrentEnemyStrength(monsterStrength);
        TypewriterPrinter.typeWithTypewriterEffect("\nThe mutant's strength appears to be " + monsterStrength);

        while (true) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Check equipment");
            System.out.println("2. Eat food");
            System.out.println("3. Fight");
            System.out.println("4. Flee");
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
                    TypewriterPrinter.typeWithTypewriterEffect("\nInvalid choice. The mutant attacks!");
                    new CombatChoice().execute(player);
                    return; 
            }
        }
    }

    private int randomizeMonsterStrength(int playerWeaponDamage) {
        int lowerBound = playerWeaponDamage / 3;
        int upperBound = (int)(playerWeaponDamage * 1.1);
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