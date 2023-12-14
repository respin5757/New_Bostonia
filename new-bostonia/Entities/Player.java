package Entities;

import States.PlayerState;
import Utilities.TypewriterPrinter;
import States.HealthyState;
import States.InjuredState;
import States.NearDeathState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private PlayerState state;
    private Item weapon;
    private int gold;
    private List<Item> inventory; 
    private double combatEfficiency;
    private int currentEnemyStrength;

    public Player() {
        this.state = new HealthyState(); // Initial state
        this.weapon = new Item("Spiked Bat", "weapon", 5); // Initial weapon with starting damage
        this.gold = 0;
        this.inventory = new ArrayList<>();
        this.combatEfficiency = this.weapon.getValue();
        // Add an apple to the inventory
        this.inventory.add(new Item("Apple", "food", 1)); 
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getState() {
        return state;
    }

    public String getStateAsString() {
        if (state instanceof HealthyState) {
            return "Healthy";
        } else if (state instanceof InjuredState) {
            return "Injured";
        } else if (state instanceof NearDeathState) {
            return "Near Death";
        } else {
            // Default 
            return "Healthy";
        }
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void subtractGold(int amount) {
        this.gold -= amount;
    }

    public int getGold() {
        return gold;
    }

    public void setWeapon(Item newWeapon) {
        if (newWeapon.getValue() > weapon.getValue()) {
            this.weapon = newWeapon;
            TypewriterPrinter.typeWithTypewriterEffect("\nEquipped new weapon: " + newWeapon.getName());
        }
    }

    public Item getWeapon() {
        return weapon;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void eatFood(Item food) {
        if (state instanceof InjuredState || state instanceof NearDeathState) {
            // Heal the player state up
            if (state instanceof InjuredState) {
                setState(new HealthyState());
            } else if (state instanceof NearDeathState) {
                setState(new InjuredState());
            }
            System.out.println("\nYou ate " + food.getName() + " and feel better.");
        } else {
            // If the player is already healthy, store the food
            addItemToInventory(food);
        }
    }

    public boolean hasFood() {
        return inventory.stream().anyMatch(item -> item.getType().equals("food"));
    }

    public void eatFood() {
        Optional<Item> foodItem = inventory.stream()
                                           .filter(item -> item.getType().equals("food"))
                                           .findFirst();

        if (foodItem.isPresent()) {
            Item food = foodItem.get();
            inventory.remove(food);
            TypewriterPrinter.typeWithTypewriterEffect("\nYou consume " + food.getName());

            // Heal the player state up
            if (state instanceof InjuredState) {
                setState(new HealthyState());
            } else if (state instanceof NearDeathState) {
                setState(new InjuredState());
            }
        } else {
            TypewriterPrinter.typeWithTypewriterEffect("\nYou have no food to eat.");
        }
    }
    public String getInventory() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nWeapon: ").append(weapon.getName())
          .append(", Damage: ").append(weapon.getValue()).append("\n");

        sb.append("Gold: ").append(gold).append("\n");
 
        sb.append("\nCurrent State: ").append(getStateAsString()).append("\n");

        if (inventory.isEmpty()) {
            sb.append("\nInventory is empty.\n");
        } else {
            sb.append("\nInventory:\n");
            for (Item item : inventory) {
                sb.append("- ").append(item.getName())
                  .append(", Type: ").append(item.getType())
                  .append(", Value: ").append(item.getValue()).append("\n");
            }
        }
    
        return sb.toString();
    }

    public void setCombatEfficiency(double d) {
        this.combatEfficiency = d;
    }

    public double getCombatEfficiency() {
        return combatEfficiency;
    }
    public void setCurrentEnemyStrength(int strength) {
        this.currentEnemyStrength = strength;
    }

    public int getCurrentEnemyStrength() {
        return currentEnemyStrength;
    }
    
}
