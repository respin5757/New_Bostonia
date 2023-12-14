package Choices;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class AvoidExploration extends Choice {

    @Override
    public void execute(Player player) {
        TypewriterPrinter.typeWithTypewriterEffect("\nYou decide to avoid exploring this area and continue on your journey.");
    }
}