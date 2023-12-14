package States;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class InjuredState implements PlayerState {
    @Override
    public void handleState(Player player) {
        double weaponDamage = player.getWeapon().getValue(); 
        player.setCombatEfficiency(weaponDamage * getSuccessMultiplier());
        TypewriterPrinter.typeWithTypewriterEffect("\nYou are injured. Your abilities are reduced.");
    }

    @Override
    public double getSuccessMultiplier() {
        return 0.7; // Reduced strength
    }
}