package States;

import Entities.Player;
import Utilities.TypewriterPrinter;

public class HealthyState implements PlayerState {
    @Override
    public void handleState(Player player) {
        double weaponDamage = player.getWeapon().getValue(); 
        player.setCombatEfficiency(weaponDamage * getSuccessMultiplier());
        TypewriterPrinter.typeWithTypewriterEffect("\nYou are healthy and at full strength.");
    }

    @Override
    public double getSuccessMultiplier() {
        return 1.0; 
    }
}