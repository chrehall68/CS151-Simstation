package plague;

import simstation.*;
import mvc.*;
public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    public static int INITIALINFECTION = 5;
    @Override
    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Guy());
    }
    @Override
    public String[] getStats() {
        return new String[]{"#agents = " + agents.size()
                            + "\nclock = " + getTime()
                            + "\n% infected = " + getPercentInfected()};
    }
    private int getPercentInfected(){
        //iterate through agents and see what percentage of them is infected
    }

}