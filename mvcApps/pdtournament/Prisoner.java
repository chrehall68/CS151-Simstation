package pdtournament;

import mvc.*;
import simstation.*;
import java.util.HashMap;

class Prisoner extends Agent {
    private static final int RADIUS = 20;
    private int fitness = 0;
    private int fitnessGained = 0;
    private int vindictiveness;
    private boolean partnerCheated = false;
    // switch this to a table to compare against all previous agents
    // table has 2 columns: reference to partner object and boolean partnerCheated
    private HashMap<Prisoner, Boolean> grudges;
    private Strategy s;
    private int strategyAsInt;
    public Prisoner( Strategy s, int i) {
        super();
        this.s = s;
        this.strategyAsInt = i;
        this.vindictiveness = Utilities.rng.nextInt(0,TournamentSimulation.MAX_VINDICTIVENESS);
        // add random vindictiveness capped at global maximum
        grudges = new HashMap<>();
    }

    public boolean cooperate(Prisoner partner) {
        return s.decide(grudges, partner);
        // consults the prisoner's strategy
        // takes in prisoner's grudge table in case they are Tit4Tat
        // returns true if cooperate
        // returns false if cheat
    }

    public void update() {
        // find partner
        Prisoner partner = (Prisoner)world.getNeighbor(this, RADIUS);
        // play a game with partner, update fitness and partnerCheated
        // allow the user to change the parameters of the problem?
        boolean myChoice = cooperate(partner);
        boolean partnerChoice;
        if (partner != null && partner.s != null) {
            partnerChoice = partner.cooperate(this);
            if (myChoice && partnerChoice) {
                // both cooperated
                updateFitness(3);
                updateFitnessGained(3);
                partner.updateFitness(3);
                partner.updateFitnessGained(3);
                partnerCheated = false;
                partner.partnerCheated = false;
                grudges.put(partner,false);
            } else if (!myChoice && !partnerChoice) {
                // both cheated
                updateFitness(1);
                updateFitnessGained(1);
                partner.updateFitness(1);
                partner.updateFitnessGained(1);
                partnerCheated = true;
                partner.partnerCheated = true;
                grudges.put(partner,true);
            } else if (myChoice) {  // !partnerChoice implied by else statements
                // I cooperated, my partner cheated
                updateFitness(0);
                updateFitnessGained(0);
                partner.updateFitness(5);
                partner.updateFitnessGained(5);
                partnerCheated = true;
                partner.partnerCheated = false;
                grudges.put(partner,true);
            } else {    // !myChoice, partnerChoice implied by else statements
                // I cheated, my partner cooperated
                updateFitness(5);
                updateFitnessGained(5);
                partner.updateFitness(0);
                partner.updateFitnessGained(0);
                partnerCheated = false;
                partner.partnerCheated = true;
                grudges.put(partner,false);
            }
            // if this is cheated, has at least 3 fitness, is not a cheater, and randomly passes a vindictiveness check,
            // punish that cheater, losing 3 of own fitness and forcing the cheater to lose 10
            if (partnerCheated && strategyAsInt != 0 && fitness >= 3 && vindictiveness > 0) {
                int punish = Utilities.rng.nextInt(0,vindictiveness);
                if (punish >= TournamentSimulation.PUNISH_THRESHOLD) {//
                    updateFitness(-3);
                    partner.updateFitness(-10);
                }
            }
        }
        // move randomly
        for (int i=0;i<3;i++) {
            heading = Heading.random();
            move(5);
        }
    }

    private void updateFitness(int amt) {
        fitness = fitness + amt;
    }

    public int getFitness() { return fitness; }

    public int getStrategyAsInt() {return strategyAsInt; }
    public void updateFitnessGained(int amt) {
        fitnessGained = amt;
    }

    public int getFitnessGained() { return fitnessGained; }
}
