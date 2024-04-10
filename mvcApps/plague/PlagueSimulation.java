package plague;

import flocking.FlockingFactory;
import simstation.*;
import mvc.*;

import java.util.Iterator;

public class PlagueSimulation extends Simulation {
    public static int COMMUNITYSIZE = 20;
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    public static int INITIALINFECTION = 5;
    @Override
    public void populate() {
        for(int i = 0; i < COMMUNITYSIZE; i++)
            addAgent(new Guy());
    }
    @Override
    public String[] getStats() {
        return new String[]{"#agents = " + agents.size()
                            + "\nclock = " + getTime()
                            + "\n% infected = " + getPercentInfected()};
    }
    private int getPercentInfected() {
        //iterate through agents and see what percentage of them is infected
        int infectedCount = 0;
        Iterator<Agent> it = agentIterator();
        while (it.hasNext()) {
            if (((Guy) it.next()).isInfected()) {
                infectedCount += 1;
            }
        }
        return 100*infectedCount/COMMUNITYSIZE;
    }
    public static void main(String[] args) {
        AppFactory factory = new PlagueFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}