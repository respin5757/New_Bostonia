import Factories.RandomScenarioFactory;
import java.util.Scanner;
import Scenarios.Scenario;
import Utilities.TypewriterPrinter;
import Factories.ScenarioFactory;
import Entities.Player;

public class GameEngine {
    private static GameEngine instance = null;

    private Player player;
    private Scenario currentScenario;
    private ScenarioFactory scenarioFactory;

    private boolean gameRunning;
    private int gameLength;
    private int currentRound;

    private Scanner scanner = new Scanner(System.in);

    private GameEngine() {
        player = new Player(); // Initialize player
        gameRunning = true;
        currentRound = 0;
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void startGame() {
        if (askToSkipIntro()) {
            showGameDescription();
        }
        waitForStartCommand();
        chooseGameLength();

        scenarioFactory = new RandomScenarioFactory(gameLength); // Initialize scenario factory

        currentScenario = scenarioFactory.createScenario();
        while (gameRunning && currentRound < gameLength) {
            playTurn();
        }

        if (currentRound >= gameLength) {
            System.err.println("\n                                                           |>>>\n" + //
                    "                   _                      _                |\n" + //
                    "    ____________ .' '.    _____/----/-\\ .' './========\\   / \\\n" + //
                    "   //// ////// /V_.-._\\  |.-.-.|===| _ |-----| u    u |  /___\\\n" + //
                    "  // /// // ///==\\ u |.  || | ||===||||| |T| |   ||   | .| u |_ _ _ _ _ _\n" + //
                    " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" + //
                    " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" + //
                    " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" + //
                    " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" + //
                    " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" + //
                    " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" + //
                    "                __(_)___         .::::::::::::::.         ___(_)__\n" + //
                    "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" + //
                    "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" + //
                    "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" + //
                    "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" + //
                    "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" + //
                    "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" + //
                    " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" + //
                    "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n" + //
                    "   |       |  [ === =] /.::::::;;::::::::::::::;;;:::::::.\\ [===  =]   |\n" + //
                    "___|_______|__[ == ==]/.::::::;;;:::::::::::::::;;;:::::::.\\[=  == ]___|_____");
            TypewriterPrinter.typeWithTypewriterEffect("\nCongratulations!\n" + //
                    "\n" + //
                    "After a harrowing journey through the treacherous wastelands, you finally see the walls of New Bostonia rising in the distance. Your heart swells with a mixture of relief and triumph as you approach the gates of this sanctuary, the symbol of new beginnings and the hope of mankind.\n" + //
                    "\n" + //
                    "As you step through the gates, you are greeted by the sights and sounds of a community reborn from the ashes of the old world. Children's laughter echoes through the streets, gardens bloom with new life, and the faces around you reflect a hard-earned peace. You realize that New Bostonia is more than just a safe haven; it's a testament to human resilience, a beacon of hope in a world that had nearly succumbed to despair.\n" + //
                    "\n" + //
                    "Your journey, fraught with danger and fraught with challenges, has not been in vain. You have not only survived but have also contributed to the rebirth of civilization. In New Bostonia, you find a new purpose and the promise of a brighter future. Here, in this bastion of humanity, you will help build a new world.\n" + //
                    "\n" + //
                    "Thank you for playing \"New Bostonia.\" Your courage and perseverance have carved a path to a new era for humanity. May your story inspire all who dare to dream of a better tomorrow.");
        }
    }

    private void showGameDescription() {
        TypewriterPrinter.typeWithTypewriterEffect("\nIn the aftermath of a global catastrophe, you find yourself in a rugged wilderness, far from the safety of human habitation. With only a spiked bat for protection and an apple for sustenance, you embark on a perilous journey towards the fabled safe haven known as New Bostonia. This elusive sanctuary, controlled by the newly reformed government, represents the last bastion of order in a world overrun by lawlessness and danger.\n" + //
                "\n" + 
                "The journey will not be easy. The land is scarred with the remnants of a once-thriving society, now reduced to ruins and overgrown with relentless vegetation. Abandoned cities stand as silent witnesses to the fall of humanity, their skeletal structures looming over a landscape riddled with hazards. Survivors, driven by desperation, can be friend or foe, and the line between salvation and peril is razor-thin. In this new world, every decision could mean the difference between life and death. Your ultimate goal: reach New Bostonia, where hope of a new beginning and a return to civilization await. But remember, the road to salvation is fraught with challenges, and only the most resilient will prevail in the quest for a new dawn in New Bostonia.");
    }

    private boolean askToSkipIntro() {
        TypewriterPrinter.typeWithTypewriterEffect("\nWelcome to the desolate world of New Bostonia, a post-apocalyptic survival game set in a landscape where civilization has crumbled and chaos reigns.\nDo you want to see the intro message? (1: Yes, 2: No): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        return choice == 1;
    }
    private void waitForStartCommand() {
        TypewriterPrinter.typeWithTypewriterEffect("\nPress 'Enter' to start...");
        scanner.nextLine();
    }

    private void chooseGameLength() {
        TypewriterPrinter.typeWithTypewriterEffect("\nChoose game length (1: Short, 2: Regular, 3: Long): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: gameLength = 10; break;
            case 2: gameLength = 15; break;
            case 3: gameLength = 20; break;
            default: gameLength = 15; 
        }
    }

    private void playTurn() {
        System.out.println("\nRound " + (currentRound + 1) + " of " + gameLength);
    
        currentScenario = scenarioFactory.createScenario();
    
        if (currentScenario != null) {
            currentScenario.initiate(player);
        } else {
            System.out.println("No scenario available this round.");
        }
    
        currentRound++;
    }
    public static void main(String[] args) {
        GameEngine game = GameEngine.getInstance();
        game.startGame();
    }

}
