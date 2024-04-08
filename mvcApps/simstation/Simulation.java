package simstation;

import mvc.Model;

import java.util.Collection;

public abstract class Simulation extends Model {
    private int clock = 0;
    private Collection<Agent> agents;

    public void start(){
        // TODO - start agents
    }

    public void suspend(){
        // TODO - suspend agents
    }

    public void resume(){
        // TODO - resume agents
    }

    public void stop(){
        // TODO - stop agents
    }

    public Agent getNeighbor(Agent agent, double radius){
        // TODO - get neighbor
        return null;
    }

    public abstract void populate();

}
