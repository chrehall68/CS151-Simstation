package pdtournament;

import mvc.*;
import simstation.*;

import java.awt.*;
import java.util.Iterator;

public class TournamentView extends SimulationView {
    public TournamentView(Model model) {
        super(model);
    }
    protected void drawAgents(Graphics gc) {
        Iterator<Agent> it = model.as(Simulation.class).agentIterator();

        double cellWidth = ((double)getWidth())/ Simulation.SIZE;
        double cellHeight = ((double)getHeight())/ Simulation.SIZE;

        while (it.hasNext()) {
            Prisoner a = (Prisoner)it.next();
            if (a.getStrategyAsInt() == 0)
                {gc.setColor(Color.RED);}
            else if (a.getStrategyAsInt() == 1)
                {gc.setColor(Color.GREEN);}
            else if (a.getStrategyAsInt() == 2)
                {gc.setColor(Color.YELLOW);}
            else {gc.setColor(Color.BLUE);}
            gc.fillRect((int)(a.getXc() *cellWidth), (int)(a.getYc() *cellHeight), (int)cellWidth, (int)cellHeight);
        }
    }
}
