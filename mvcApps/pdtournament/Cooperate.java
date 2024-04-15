package pdtournament;

import java.io.Serializable;
import java.util.HashMap;

public class Cooperate implements Strategy, Serializable {
    @Override
    public boolean decide(HashMap<Prisoner, Boolean> grudges, Prisoner partner) { return true; }
}