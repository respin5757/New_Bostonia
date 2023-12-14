package States;
import Entities.Player;
import Utilities.TypewriterPrinter;
public class NearDeathState implements PlayerState {
    @Override
    public void handleState(Player player) {
        double weaponDamage = player.getWeapon().getValue(); 
        player.setCombatEfficiency(weaponDamage * getSuccessMultiplier());
        TypewriterPrinter.typeWithTypewriterEffect("\nYou are near death. Your abilities are greatly reduced.");
    }

    @Override
    public double getSuccessMultiplier() {
        return 0.3; // Significantly reduced strength due to the near-death state
    }
}