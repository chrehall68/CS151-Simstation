package flocking;

import mvc.AppFactory;
import simstation.*;

import java.util.Iterator;

public class FlockingSimulation extends Simulation {
    @Override
    public void populate() {
        for (int i = 0; i < 50; ++i){
            addAgent(new Bird());
        }
    }

    @Override
    public String[] getStats() {
        int[] speeds = new int[5];
        Iterator<Agent> agentIterator = agentIterator();
        while (agentIterator.hasNext()){
            speeds[((Bird)agentIterator.next()).getSpeed()-1]++;
        }
        String[] stats = new String[5];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i< stats.length; ++i){
            builder.append("#Birds @ speed ");
            builder.append(i+1);
            builder.append(" = ");
            builder.append(speeds[i]);
            stats[i] = builder.toString();
            builder.setLength(0);
        }
        return stats;
    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }
}
