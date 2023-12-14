package Choices;

import java.util.List;
import java.util.Random;
import Entities.Item;
import Entities.Player;
import States.HealthyState;
import States.InjuredState;
import States.NearDeathState;
import States.PlayerState;
import Utilities.LootGenerator;
import Utilities.TypewriterPrinter;

public class CombatChoice extends Choice {
    private Random random = new Random();

    @Override
    public void execute(Player player) {
        
        double playerEfficiency = player.getCombatEfficiency();
        TypewriterPrinter.typeWithTypewriterEffect("\nYour combat efficiency is " + playerEfficiency);
    
        int enemyStrength = player.getCurrentEnemyStrength();
        TypewriterPrinter.typeWithTypewriterEffect("\nYou are facing an enemy with strength " + enemyStrength);
    
        double worseningProbability = calculateWorseningProbability(playerEfficiency, enemyStrength);
    
        if (random.nextDouble() < worseningProbability) {
            // The player's condition worsens
            TypewriterPrinter.typeWithTypewriterEffect("\nThe combat is challenging, and your condition worsens.");
            transitionToWorseState(player);
        } else {
            // The player takes no damage in the fight
            TypewriterPrinter.typeWithTypewriterEffect("\nYou successfully defend against the enemy!");
        }

        // loot generation
        generateAndHandleLoot(player);
    }
    private double calculateWorseningProbability(double playerEfficiency, int enemyStrength) {
        return 1.0 - playerEfficiency / (playerEfficiency + enemyStrength);
    }

    private void transitionToWorseState(Player player) {
        PlayerState currentState = player.getState();
        if (currentState instanceof HealthyState) {
            player.setState(new InjuredState());
            TypewriterPrinter.typeWithTypewriterEffect("\nYou have been injured in the fight.");
        } else if (currentState instanceof InjuredState) {
            player.setState(new NearDeathState());
            TypewriterPrinter.typeWithTypewriterEffect("\nYour injuries worsen. You are near death.");
        } else if (currentState instanceof NearDeathState) {
            TypewriterPrinter.typeWithTypewriterEffect("\nYou succumb to your injuries and die.");
        }
    }

    private void generateAndHandleLoot(Player player) {
        List<Item> loot = LootGenerator.generateLoot();
        for (Item item : loot) {
            if (item.getType().equals("weapon") && item.getValue() > player.getWeapon().getValue()) {
                player.setWeapon(item);
                TypewriterPrinter.typeWithTypewriterEffect("\nYou have found a better weapon: " + item.getName());
            } else if (item.getType().equals("food")) {
                player.addItemToInventory(item);
                TypewriterPrinter.typeWithTypewriterEffect("\nYou found some food: " + item.getName());
            } else if (item.getType().equals("gold")) {
                player.addGold(item.getValue());
                TypewriterPrinter.typeWithTypewriterEffect("\nYou found " + item.getValue() + " gold coins.");
            }
        }
    }

}