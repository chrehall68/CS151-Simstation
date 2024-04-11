package pdtournament;
import simstation.*;
import mvc.*;
public class Cooperate implements Strategy {
    @Override
    public boolean decide(boolean partnerCheated) {
        return true;
    }
}
