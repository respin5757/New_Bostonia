// PlayerState.java

//state pattern
package States;

import Entities.Player;

public interface PlayerState {
    void handleState(Player player);
    double getSuccessMultiplier();
}




