package pdtournament;

import java.io.Serializable;
import java.util.HashMap;

public class Cheat implements Strategy, Serializable {
    @Override
    public boolean decide(HashMap<Prisoner,Boolean> grudges, Prisoner partner) { return false; }
}
