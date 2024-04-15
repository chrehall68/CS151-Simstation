package pdtournament;

import simstation.*;
import mvc.*;

import java.util.Iterator;

public class TournamentSimulation extends Simulation {

    public static double COMMUNITYSIZE = 40;

    public static int MAX_VINDICTIVENESS = 10;
    // when set lower than PUNISH_THRESHOLD, cheaters are never punished
    public static int PUNISH_THRESHOLD = 7;
    // when set to 0, cheaters are always punished if able
    @Override
    public void populate() {
        for (int i = 0; i < COMMUNITYSIZE; ++i) {
            addAgent(new Prisoner(initialize(i), i % 4));
        }
    }

    public Strategy initialize(int in) {
        if (in % 4 == 0) {
            return new Cheat();
        }
        if (in % 4 == 1) {
            return new Cooperate();
        }
        if (in % 4 == 2) {
            return new RandomlyCooperate();
        }
        if (in % 4 == 3) {
            return new Tit4Tat();
        }
        return null;
    }

    @Override
    public String[] getStats() {
        double[] fitness = new double[4];       // sum-->avg of all Prisoner's total fitness
        double[] fitnessGains = new double[4];  // sum-->avg of all Prisoner's fitness gains last round
        Iterator<Agent> agentIterator = agentIterator();
        while (agentIterator.hasNext()) {
            Prisoner p = (Prisoner) agentIterator.next();
            fitness[p.getStrategyAsInt()] = fitness[p.getStrategyAsInt()] + p.getFitness();
            fitnessGains[p.getStrategyAsInt()] = fitnessGains[p.getStrategyAsInt()] + p.getFitnessGained();
        }
        String[] stats = new String[4];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stats.length; ++i) {
            builder.append("Average Fitness of Strategy [");
            if (i == 0) {
                builder.append("Cheat");
            } else if (i == 1) {
                builder.append("Cooperate");
            } else if (i == 2) {
                builder.append("Randomly Cooperate");
            } else {
                builder.append("Tit4Tat");
            }
            builder.append("] = ");
            builder.append(fitness[i]/(COMMUNITYSIZE/4));
            builder.append("  ||  Average Fitness Gained this Round = ");
            builder.append(fitnessGains[i]/(COMMUNITYSIZE/4));
            stats[i] = builder.toString();
            builder.setLength(0);
        }
        return stats;
    }


    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new TournamentFactory());
        panel.display();
    }

}
