package pdtournament;
import mvc.*;
import simstation.*;
public class TournamentFactory extends SimStationFactory {

    @Override
    public Model makeModel() { return new TournamentSimulation(); }

    @Override
    public View makeView(Model m) {
        return new TournamentView((TournamentSimulation)m);
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma Tournament";
    }

}
