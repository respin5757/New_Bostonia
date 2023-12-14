package Choices;
import java.util.List;
import java.util.Random;
import Entities.Item;
import Entities.Player;
import Utilities.LootGenerator;
import Utilities.TypewriterPrinter;
import Scenarios.BanditEncounter;
import Scenarios.MutantMonsterAttack;
import Scenarios.RobotEncounter;
import Scenarios.Scenario;
import Scenarios.WildAnimalAttack;
import Factories.RandomScenarioFactory;

public class ExploreChoice extends Choice {
    private Random random = new Random();
    private RandomScenarioFactory scenarioFactory;

    public ExploreChoice(RandomScenarioFactory scenarioFactory) {
        this.scenarioFactory = scenarioFactory;
    }

    @Override
    public void execute(Player player) {
        if (random.nextDouble() < 0.60) {
            // Player finds loot
            TypewriterPrinter.typeWithTypewriterEffect("\nYou found something useful while exploring!");
            List<Item> loot = LootGenerator.generateLoot();
            for (Item item : loot) {
                player.addItemToInventory(item);
                TypewriterPrinter.typeWithTypewriterEffect("\nFound " + item.getName() + ": " + item.getType() + ", Value: " + item.getValue());
            }
        } else {
            // Player encounters an enemy
            TypewriterPrinter.typeWithTypewriterEffect("\nYou've encountered an enemy!");
            Scenario enemyEncounter = selectSpecificScenario();
            enemyEncounter.initiate(player);
        }
    }

    private Scenario selectSpecificScenario() {
        int[] specificScenarios = {0, 3, 4, 6}; // Only scenarios with enemies
        int selectedScenario = specificScenarios[random.nextInt(specificScenarios.length)];

        switch (selectedScenario) {
            case 0: return new BanditEncounter();
            case 2: return new MutantMonsterAttack();
            case 3: return new RobotEncounter();
            case 5: return new WildAnimalAttack();
            default: return new BanditEncounter(); 
        }
    }
}