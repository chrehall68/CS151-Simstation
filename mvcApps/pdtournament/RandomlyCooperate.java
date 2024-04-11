package pdtournament;

import simstation.*;
import mvc.*;

public class RandomlyCooperate implements Strategy {
    @Override
    public boolean decide(boolean partnerCheated) {
        if (Utilities.rng.nextInt(100)%2==0) {
            return true;
        }
        return false;
    }
}
