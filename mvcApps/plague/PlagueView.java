package plague;

import mvc.Model;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;
import java.util.Iterator;

public class PlagueView extends SimulationView {
    public PlagueView(Model model) {
        super(model);
    }
    protected void drawAgents(Graphics gc) {
        Iterator<Agent> it = model.as(Simulation.class).agentIterator();

        double cellWidth = ((double)getWidth())/ Simulation.SIZE;
        double cellHeight = ((double)getHeight())/ Simulation.SIZE;

        while (it.hasNext()) {
            Guy a = (Guy)it.next();
            if (a.isInfected())
                {gc.setColor(Color.RED);}
            else{gc.setColor(Color.GREEN);}
            gc.fillRect((int)(a.getXc() *cellWidth), (int)(a.getYc() *cellHeight), (int)cellWidth, (int)cellHeight);
        }
    }
}
