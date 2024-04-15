package pdtournament;

import mvc.*;

import java.util.HashMap;

public class RandomlyCooperate implements Strategy {
    @Override
    public boolean decide(HashMap<Prisoner,Boolean> grudges, Prisoner partner) {
        return Utilities.rng.nextInt(100) % 2 == 0;
        // returns true or false 50% of the time
    }
}
