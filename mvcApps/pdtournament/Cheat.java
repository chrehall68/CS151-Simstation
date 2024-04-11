package pdtournament;
import simstation.*;
import mvc.*;

public class Cheat implements Strategy {
    @Override
    public boolean decide(boolean partnerCheated) {
        return false;
    }
}
