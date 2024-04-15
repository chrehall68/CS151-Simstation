package pdtournament;

import java.io.Serializable;
import java.util.HashMap;

public class Tit4Tat implements Strategy, Serializable {
    @Override
    public boolean decide(HashMap<Prisoner,Boolean> grudges, Prisoner partner) {
        // cheats if the partner cheated last time
        return !grudges.containsKey(partner) || !grudges.get(partner);
    }
}
