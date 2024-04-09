package simstation;

import java.util.*;
import mvc.*;

import java.util.Collection;

public abstract class Simulation extends Model {
    private Collection<Agent> agents;
    transient private Timer timer; // timers aren't serializable
    private int clock;

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

    public void start() {
        for (Agent a : agents) {
            a.start();
        }
    }

    public void suspend() {
        for (Agent a : agents) {
            a.suspend();
        }
    }

    public void resume() {
        // TODO - resume agents
    }

    public void stop() {
        for (Agent a : agents) {
            a.stop();
        }
    }

    public Agent getNeighbor(Agent agent, double radius) {
        // TODO - get neighbor

        return null;
    }

    public abstract void populate();

    // should return a string describing the current simulation statistics
    public abstract String stats();

}
