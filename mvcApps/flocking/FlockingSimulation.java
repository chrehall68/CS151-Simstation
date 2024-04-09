package flocking;

import mvc.AppFactory;
import simstation.*;

public class FlockingSimulation extends Simulation {
    @Override
    public void populate() {

    }

    @Override
    public String stats() {
        return "";
    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
