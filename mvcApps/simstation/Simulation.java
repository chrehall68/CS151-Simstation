package simstation;

import mvc.Model;

import java.util.Collection;

public abstract class Simulation extends Model {
    private int clock = 0;
    private Collection<Agent> agents;

    public void start(){
        for(Agent a: agents){
            a.start();
        }
    }

    public void suspend(){
        for(Agent a: agents){
            a.suspend();
        }
    }

    public void resume(){
        // TODO - resume agents
    }

    public void stop(){
        for(Agent a: agents){
            a.stop();
        }
    }

    public Agent getNeighbor(Agent agent, double radius){
        // TODO - get neighbor
        return null;
    }

    public abstract void populate();

}
