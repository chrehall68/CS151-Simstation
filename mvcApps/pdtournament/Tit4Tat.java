package pdtournament;
import simstation.*;
import mvc.*;
import java.util.HashMap;

public class Tit4Tat implements Strategy {
    @Override
    public boolean decide(HashMap<Prisoner,Boolean> grudges, Prisoner partner) {
        if (grudges.containsKey(partner) && grudges.get(partner)) {
            // cheats if the partner cheated last time
            return false;
        }
        return true;
    }
}
