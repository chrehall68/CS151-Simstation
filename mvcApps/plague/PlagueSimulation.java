package plague;

import simstation.*;
import mvc.*;

import java.util.Iterator;

public class PlagueSimulation extends Simulation {
    public static int COMMUNITYSIZE = 20;
    public static int VIRULENCE = 10; // % chance of infection
    public static int RESISTANCE = 50; // % chance of resisting infection
    public static int INITIALINFECTION = 1; //NUMBER of initially infected guys
    public int infected;
    @Override
    public void populate() {
        infected = 0;
        for(int i = 0; i < COMMUNITYSIZE; i++)
            addAgent(new Guy());
        initialize();
    }
    private void initialize(){
        Iterator<Agent> it = agentIterator();
        while(it.hasNext()){
            if (infected< INITIALINFECTION){
                ((Guy)it.next()).infect();
                infected++;
            }
            else {it.next();}
        }
    }
    @Override
    public String[] getStats() {
        return new String[]{"# agents = " + agents.size()
                            + "\nclock = " + getTime()
                            + "\n% infected = " + getPercentInfected()};
    }
    private int getPercentInfected() {
        //iterate through agents and see what percentage of them is infected
        return 100*getNumInfected()/COMMUNITYSIZE;
    }
    public int getNumInfected(){
        int infectedCount = 0;
        Iterator<Agent> it = agentIterator();
        while (it.hasNext()) {
            if (((Guy) it.next()).isInfected()) {
                infectedCount += 1;
            }
        }
        return infectedCount;
    }
    public static void main(String[] args) {
        AppFactory factory = new PlagueFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}