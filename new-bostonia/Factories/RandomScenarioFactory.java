package Factories;
import java.util.Random;
import Scenarios.Scenario;
import Scenarios.BanditEncounter;
import Scenarios.MutantMonsterAttack;
import Scenarios.OldWorldLibrary;
import Scenarios.RobotEncounter;
import Scenarios.TraderMeeting;
import Scenarios.UndergroundBunker;
import Scenarios.WildAnimalAttack;
//factory method



// Random Scenario Factory
public class RandomScenarioFactory implements ScenarioFactory {
    private Random random = new Random();
    private int roundsSinceLastTrader = 0;
    private final int gameLength;

    public RandomScenarioFactory(int gameLength) {
        this.gameLength = gameLength;
    }

    public Scenario createScenario() {
        int scenarioType = random.nextInt(10); // Total number of scenarios
        roundsSinceLastTrader++;

        // Increase probability of encountering a trader if it has been a while
        if (roundsSinceLastTrader >= getThresholdForTraderEncounter()) {
            roundsSinceLastTrader = 0;
            return new TraderMeeting();
        }

        switch (scenarioType) {
            case 0: return new BanditEncounter();
            case 1: return new TraderMeeting();
            case 2: return new MutantMonsterAttack();
            case 3: return new RobotEncounter();
            case 4: return new UndergroundBunker();
            case 5: return new WildAnimalAttack();
            case 6: return new OldWorldLibrary();
            default: return new BanditEncounter(); // Default scenario
        }
    }

    private int getThresholdForTraderEncounter() {
        // Adjust the threshold based on the game length
        if (gameLength == 10) { // Short game
            return 1; 
        } else if (gameLength == 20) { // Long game
            return 2; 
        } else { // Regular game
            return 3;
        }
    }
}