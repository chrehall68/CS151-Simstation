package simstation;

import mvc.*;

import java.awt.*;
import java.util.Iterator;

public class SimulationView extends View {
    // TODO static values
    protected final static int AGENT_SIZE = 5;
    protected Color agentColor = Color.GRAY;
    protected Color backgroundColor = Color.BLACK;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(backgroundColor);
    }

    protected void drawAgents(Graphics gc) {
        Simulation simulation = (Simulation)model;
        Iterator<Agent> it = simulation.agentIterator();
        gc.setColor(agentColor);
        int centerOffset = AGENT_SIZE / 2;
        while (((Iterator<?>) it).hasNext()) {
            Agent c = it.next();
            gc.fillOval(c.xc - centerOffset, c.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();

        gc.setColor(Color.BLACK);
        gc.drawRect(0,0, Simulation.SIZE, Simulation.SIZE);
        drawAgents(gc);

        gc.setColor(oldColor);
    }
}
