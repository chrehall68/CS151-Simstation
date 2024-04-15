package pdtournament;

import java.util.HashMap;

public class Cheat implements Strategy {
    @Override
    public boolean decide(HashMap<Prisoner,Boolean> grudges, Prisoner partner) { return false; }
}
