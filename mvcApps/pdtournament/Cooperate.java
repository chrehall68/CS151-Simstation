package pdtournament;

import java.util.HashMap;

public class Cooperate implements Strategy {
    @Override
    public boolean decide(HashMap<Prisoner, Boolean> grudges, Prisoner partner) { return true; }
}