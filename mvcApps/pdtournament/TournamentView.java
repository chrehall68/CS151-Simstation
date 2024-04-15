package pdtournament;

import mvc.*;
import simstation.*;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class TournamentView extends SimulationView {
    private static final Map<Integer, Color> colorMap = Map.of(0, Color.RED, 1, Color.GREEN, 2, Color.YELLOW, 3, Color.BLUE);
    public TournamentView(Model model) {
        super(model);
    }
    @Override
    protected Color getAgentColor(Agent agent) {
       return colorMap.get(((Prisoner)agent).getStrategyAsInt());
    }
}
