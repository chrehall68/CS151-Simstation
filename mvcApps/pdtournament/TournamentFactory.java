package pdtournament;
import mvc.*;
import simstation.*;

import java.util.Locale;

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

    @Override
    public String[] getHelp() {
        return new String[] { "Press Start to populate and start agents",
                "Press Suspend to pause all agents",
                "Press Resume to un-pause all agents",
                "Press Stop to halt all agents",
                "Press Stats to view the current simulation statistics",

                "Red Agents always Cheat",
                "Green Agents always Cooperate",
                "Yellow Agents randomly Cheat or Cooperate",
                "Blue Agents Cheat if their last partner Cheated"
                };
    }

}
