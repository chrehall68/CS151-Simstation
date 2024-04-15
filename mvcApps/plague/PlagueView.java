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

    @Override
    protected Color getAgentColor(Agent a) {
        if (((Guy)a).isInfected()) return Color.RED;
        return Color.GREEN;
    }
}
