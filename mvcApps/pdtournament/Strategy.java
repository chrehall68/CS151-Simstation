package pdtournament;
import simstation.*;
import mvc.*;

import java.util.HashMap;

public interface Strategy {

    public boolean decide(HashMap<Prisoner,Boolean> grudges,Prisoner partner);

}
