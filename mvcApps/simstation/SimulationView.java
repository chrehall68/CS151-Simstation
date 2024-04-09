package simstation;

import mvc.*;

import java.awt.*;
import java.util.Iterator;

public class SimulationView extends View {
    protected Color agentColor = Color.WHITE;
    protected Color backgroundColor = Color.DARK_GRAY;

    public SimulationView(Model model) {
        super(model);
        this.setBackground(backgroundColor);
    }

    protected void drawAgents(Graphics gc) {
        Iterator<Agent> it = model.as(Simulation.class).agentIterator();
        gc.setColor(agentColor);

        double cellWidth = ((double)getWidth())/ Simulation.SIZE;
        double cellHeight = ((double)getHeight())/ Simulation.SIZE;

        while (it.hasNext()) {
            Agent c = it.next();
            gc.fillOval((int)(c.getXc() *cellWidth), (int)(c.getYc() *cellHeight), (int)cellWidth, (int)cellHeight);
        }
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();

        gc.setColor(backgroundColor);
        gc.fillRect(0,0, getWidth(), getHeight());
        drawAgents(gc);

        gc.setColor(oldColor);
    }
}
