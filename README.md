# New Bostonia

## Project Overview
"New Bostonia" is a text-based adventure game developed as part of an Object-Oriented Programming class, focusing on the implementation of various design patterns. Set in a post-apocalyptic world, the game challenges players to navigate through a wilderness filled with dangers and uncertainties, aiming to reach the mythical sanctuary of New Bostonia. This project serves as an educational tool to demonstrate the practical application of design patterns in software development.

## Narrative
In a world ravaged by an unknown catastrophe, players embark on a perilous journey through a wilderness teeming with dangers. Their ultimate goal is to reach New Bostonia, a beacon of hope and a symbol of new beginnings in the chaos of the post-apocalyptic landscape.

## Key Features

- **Dynamic Scenarios**: Encounter a variety of scenarios like mutant attacks, bandit encounters, and ancient relic discoveries, ensuring a unique experience in each playthrough.

- **Interactive Choices**: Make critical decisions that influence the outcome of your journey, including combat strategies, retreats, and NPC interactions.

- **Inventory Management**: Manage a collection of items, including weapons, food, and gold, crucial for survival and progress.

- **Health and State System**: Your health status (healthy, injured, near death) significantly impacts combat effectiveness and decision-making.

- **Text-Based Interface**: Engage with the game through a narrative-driven, text-based interface.

## Design Patterns and Their Implementation

### Singleton Pattern (GameEngine)
- **Purpose**: Ensures a single instance of the game engine, maintaining consistent game state.
- **Implementation**: Private constructor in `GameEngine` with a static method for instantiation.

### Factory Method Pattern (ScenarioFactory and RandomScenarioFactory)
- **Purpose**: Facilitates object creation without specifying the exact class, allowing for diverse scenarios.
- **Implementation**: `ScenarioFactory` interface for creating scenarios, with `RandomScenarioFactory` providing the logic for random scenario generation.

### State Pattern (PlayerState, HealthyState, InjuredState, NearDeathState)
- **Purpose**: Manages changes in player's health state, affecting behavior and capabilities.
- **Implementation**: `PlayerState` interface with specific states implementing distinct behaviors.

### Command Pattern (Choice and its subclasses)
- **Purpose**: Encapsulates requests as objects, enabling diverse client requests and undoable operations.
- **Implementation**: `Choice` as an abstract class with subclasses for specific game actions.

## Running the Project

### Compile the Project
```bash
javac -d . Entities/*.java States/*.java Choices/*.java Scenarios/*.java Utilities/*.java Factories/*.java GameEngine.java
```
### Run the Game
```bash
java GameEngine
```

### Contributing
Contributions to "New Bostonia" are welcome. Thank you for checking my project out, hope you enjoy it!
