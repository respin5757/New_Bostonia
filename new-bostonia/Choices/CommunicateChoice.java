package Choices;
import java.util.Random;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class CommunicateChoice extends Choice {
    private static final double SUCCESS_PROBABILITY = 0.7;
    private Random random = new Random();

    @Override
    public void execute(Player player) {
        if (random.nextDouble() < SUCCESS_PROBABILITY) {
            // Success - Player communicates successfully
            TypewriterPrinter.typeWithTypewriterEffect("\nYou successfully communicate with the enemy and avoid combat.");
            // Handle successful communication logic (e.g., gain information, make an ally, etc.)
        } else {
            // Failure - Player must fight
            TypewriterPrinter.typeWithTypewriterEffect("\nYour attempt to communicate fails, and the enemy attacks!");
            // Trigger combat logic
            new CombatChoice().execute(player);
        }
    }
}
