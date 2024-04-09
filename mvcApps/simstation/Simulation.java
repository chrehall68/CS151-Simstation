package simstation;

import java.util.*;
import mvc.*;

import java.util.Collection;

public abstract class Simulation extends Model {
    public final static int SIZE = 250;
    private Collection<Agent> agents;
    private boolean isRunning;
    private boolean isSuspended;
    transient private Timer timer; // timers aren't serializable
    private int clock;

    public Simulation(){
        super();
        agents = new LinkedList<Agent>();
        clock = 0;
        isRunning = false;
        isSuspended = false;
    }
    public Iterator<Agent> agentIterator() { //use for drawing all agents
        return agents.iterator();
    }

    public void start(){
        clock = 0;
        startTimer();
        agents.clear();
        populate();
        for(Agent a: agents){
            a.start();
        }
        isRunning = true;
        isSuspended = false;
        changed();
    }

    public void suspend(){
        stopTimer();
        for(Agent a: agents){
            a.suspend();
        }
        isSuspended = true;
        changed();
    }

    public void resume(){
        startTimer();
        for(Agent a : agents){
            a.resume();
        }
        isSuspended = false;
        changed();
    }

    public void stop(){
        stopTimer();
        for(Agent a: agents){
            a.stop();
        }
        isRunning = false;
        isSuspended = false;
        changed();
    }

    public Agent getNeighbor(Agent agent, double radius){
        // TODO - get neighbor

        return null;
    }

    public abstract void populate();

    public void addAgent(Agent agent){
        agents.add(agent);

    }
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    public boolean isRunning() {
        return isRunning;
    }
    public boolean isSuspended(){
        return isSuspended;
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

}
