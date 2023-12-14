package Choices;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Utilities.LootGenerator;
import Utilities.TypewriterPrinter;
import Entities.Item;
import Entities.Player;
import States.InjuredState;
import States.NearDeathState;
public class TradeChoice extends Choice {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute(Player player) {
        List<Item> itemsForSale = LootGenerator.generateTradingLoot(5); // generate 5 random loot
        displayTraderOffers(itemsForSale);
        TypewriterPrinter.typeWithTypewriterEffect("\nYour current gold: " + player.getGold());
        TypewriterPrinter.typeWithTypewriterEffect("\nChoose an item to buy (1-" + itemsForSale.size() + "), or 0 to exit: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= itemsForSale.size()) {
            Item selectedItem = itemsForSale.get(choice - 1);
            processPurchase(player, selectedItem, selectedItem.getValue());
        } else {
            TypewriterPrinter.typeWithTypewriterEffect("\nYou decide not to trade and continue on your journey.");
        }
    }

    private void displayTraderOffers(List<Item> itemsForSale) {
        TypewriterPrinter.typeWithTypewriterEffect("\nTrader's offers:");
        for (int i = 0; i < itemsForSale.size(); i++) {
            Item item = itemsForSale.get(i);
            String offerDetails = (i + 1) + ". " + item.getName() + " - " + item.getValue() + " gold";
            if(item.getType().equals("weapon")) {
                offerDetails += " (Damage: " + item.getValue() + ")";
            }
            TypewriterPrinter.typeWithTypewriterEffect("\n" + offerDetails);
        }
    }

    private void processPurchase(Player player, Item item, int price) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            TypewriterPrinter.typeWithTypewriterEffect("\nYou purchased: " + item.getName() + " for " + price + " gold.");

            if (item.getType().equals("weapon")) {
                player.setWeapon(item); // Replace weapon if it's better
            } else if (item.getType().equals("food")) {
                // Check if player needs to eat, otherwise add to inventory
                if (player.getState() instanceof InjuredState || player.getState() instanceof NearDeathState) {
                    player.eatFood(item);
                } else {
                    player.addItemToInventory(item);
                }
            }
        } else {
            TypewriterPrinter.typeWithTypewriterEffect("\nYou don't have enough gold to purchase this item.");
        }
    }
}
