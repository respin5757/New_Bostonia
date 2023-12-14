package Choices;
import java.util.Random;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class FleeChoice extends Choice {
    private Random random = new Random();

    @Override
    public void execute(Player player) {
        if (random.nextBoolean()) {
            // Player flees successfully
            TypewriterPrinter.typeWithTypewriterEffect("\nYou successfully fled from the enemy!");
        } else {
            // Failure - Player must fight
            TypewriterPrinter.typeWithTypewriterEffect("\nYou failed to flee and must face the enemy!");
            new CombatChoice().execute(player);
        }
    }
}