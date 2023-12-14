package Utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import Entities.Item;
public class LootGenerator {
    private static final List<Item> LOOT_POOL = Arrays.asList(
        new Item("Sword", "weapon", 15),
        new Item("Axe", "weapon", 20),
        new Item("Bread", "food", 5),
        new Item("Apple", "food", 3),
        new Item("Makeshift Spear", "weapon", 10),
        new Item("Rusty Knife", "weapon", 8),
        new Item("Reinforced Club", "weapon", 12),
        new Item("Handmade Bow", "weapon", 14),
        new Item("Pipe Wrench", "weapon", 11),
        new Item("Baseball Bat with Nails", "weapon", 15),
        new Item("Crowbar", "weapon", 13),
        new Item("Machete", "weapon", 17),
        new Item("Improvised Sledgehammer", "weapon", 20),
        new Item("Canned Beans", "food", 4),
        new Item("Dried Jerky", "food", 6),
        new Item("Preserved Fruit", "food", 3),
        new Item("Energy Bar", "food", 5),
        new Item("Bottled Water", "food", 2),
        new Item("Canned Soup", "food", 7),
        new Item("Instant Noodles", "food", 4),
        new Item("Packed Rice", "food", 3),
        new Item("MRE (Meal, Ready-to-Eat)", "food", 8),
        new Item("Crossbow", "weapon", 18),
        new Item("Slingshot", "weapon", 7),
        new Item("Handmade Rifle", "weapon", 22),
        new Item("Thrown Rocks", "weapon", 5),
        new Item("Flare Gun", "weapon", 10),
        new Item("Improvised Shotgun", "weapon", 20),
        new Item("Javelin", "weapon", 15),
        new Item("Boomerang", "weapon", 8),
        new Item("Firecracker Launcher", "weapon", 12),
        new Item("Smoked Fish", "food", 6),
        new Item("Pickled Vegetables", "food", 4),
        new Item("Honey", "food", 5),
        new Item("Hardtack", "food", 3),
        new Item("Almonds", "food", 2),
        new Item("Trail Mix", "food", 4),
        new Item("Dried Fruits", "food", 3),
        new Item("Peanut Butter", "food", 7),
        new Item("Granola Bars", "food", 5),
        new Item("Laser Pistol", "weapon", 25),
        new Item("Plasma Rifle", "weapon", 30),
        new Item("Electro-Mace", "weapon", 22),
        new Item("Ion Blaster", "weapon", 28),
        new Item("Pulse Shotgun", "weapon", 27),
        new Item("Gravity Hammer", "weapon", 24),
        new Item("Photon Sniper", "weapon", 32),
        new Item("Arc Thrower", "weapon", 26),
        new Item("Nano-Blade", "weapon", 23),
        new Item("Sonic Grenade", "weapon", 20)
    );
    private static final int MAX_GOLD_DROP = 15; // Max gold that can be dropped
    private static final Random random = new Random();

    public static List<Item> generateLoot() {
        List<Item> droppedItems = new ArrayList<>();
        // Randomly add items from LOOT_POOL
        int numberOfItems = 1 + random.nextInt(2); // Randomly decide to add 1 or 2 items
        for (int i = 0; i < numberOfItems; i++) {
            Item randomItem = LOOT_POOL.get(random.nextInt(LOOT_POOL.size()));
            droppedItems.add(randomItem);
        }
        
        // Add random gold amount
        int goldDrop = random.nextInt(MAX_GOLD_DROP + 1);
        if (goldDrop > 0) {
            droppedItems.add(new Item("Gold", "gold", goldDrop));
        }

        return droppedItems;
    }
    public static List<Item> generateTradingLoot(int numberOfItems) {
        List<Item> tradingItems = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            Item randomItem = LOOT_POOL.get(random.nextInt(LOOT_POOL.size()));
            if (!"gold".equals(randomItem.getType())) { // Exclude gold from trading items
                tradingItems.add(randomItem);
            }
        }
        return tradingItems;
    }
}
