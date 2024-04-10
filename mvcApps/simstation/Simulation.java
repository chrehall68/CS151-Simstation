package simstation;

import java.util.*;
import mvc.*;

public abstract class Simulation extends Model {
    public final static int SIZE = 150;
    protected final ArrayList<Agent> agents;  // arraylist for O(1) random access time
    private boolean isRunning;
    private boolean isSuspended;
    transient private Timer timer; // timers aren't serializable
    private int clock;

    public Simulation() {
        super();
        agents = new ArrayList<>();
        clock = 0;
        isRunning = false;
        isSuspended = false;
    }

    public Iterator<Agent> agentIterator() { // use for drawing all agents
        return agents.iterator();
    }

    public void start() {
        clock = 0;
        startTimer();
        agents.clear();
        populate();
        for (Agent a : agents) {
            a.start();
        }
        isRunning = true;
        isSuspended = false;
        changed();
    }

    public void suspend() {
        stopTimer();
        for (Agent a : agents) {
            a.suspend();
        }
        isSuspended = true;
        changed();
    }

    public void resume() {
        startTimer();
        for (Agent a : agents) {
            a.resume();
        }
        isSuspended = false;
        changed();
    }

    public void stop() {
        stopTimer();
        for (Agent a : agents) {
            a.stop();
        }
        isRunning = false;
        isSuspended = false;
        changed();
    }

    private double getDistance(Agent agent1, Agent agent2){
        // pythagorean theorem for distance
        return Math.sqrt(Math.pow(agent1.getXc()-agent2.getXc(), 2) + Math.pow(agent1.getYc()-agent2.getYc(), 2));
    }
    public Agent getNeighbor(Agent agent, double radius) {
        int start = Utilities.rng.nextInt(agents.size());
        for (int i = 0; i < agents.size(); ++i){
            Agent cur = agents.get((start + i)%agents.size());
            if (getDistance(cur, agent) < radius){
                return cur;
            }
        }

        return null;
    }
    public List<Agent> getAllNeighbors(Agent agent, double radius) {
        LinkedList<Agent> result = new LinkedList<>();
        for (Agent cur : agents){
            if (getDistance(cur, agent) < radius){
                result.add(cur);
            }
        }
        return result;
    }

    public abstract void populate();

    public String[] getStats() {
        return new String[]{"#agents = " + agents.size()};
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
        agent.setWorld(this);
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }
    public int getTime(){
        return clock;
    }
    public boolean isRunning() {
        return isRunning;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

}
