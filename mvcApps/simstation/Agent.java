package simstation;

import mvc.Utilities;

import java.util.Map;
import java.util.function.Consumer;
import java.io.Serializable;

public abstract class Agent implements Serializable, Runnable {
    protected String name;
    protected Heading heading; // TODO - figure out if Heading is a real class
    protected int xc; // x coordinate
    protected int yc; // y coordinate
    protected Simulation world;
    private boolean suspended = false;
    private boolean stopped = false;
    transient protected Thread myThread;

    // mapping for heading -> action
    private static final Map<Heading, Consumer<Agent>> moveMap = Map.of(Heading.EAST, Agent::moveLeft,
            Heading.WEST, Agent::moveRight,
            Heading.NORTH, Agent::moveUp,
            Heading.SOUTH, Agent::moveDown,
            Heading.NORTHEAST, agent -> {
                Agent.moveLeft(agent);
                Agent.moveUp(agent);
            },
            Heading.NORTHWEST, agent -> {
                Agent.moveRight(agent);
                Agent.moveUp(agent);
            },
            Heading.SOUTHEAST, agent -> {
                Agent.moveLeft(agent);
                Agent.moveDown(agent);
            },
            Heading.SOUTHWEST, agent -> {
                Agent.moveRight(agent);
                Agent.moveDown(agent);
            });

    public Agent(String name) {
        this.name = name;
    }

    public Agent() {
        super();
        suspended = false;
        stopped = false;
        myThread = null;
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        onStart();
        while (!stopped) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch (InterruptedException e) {
                Utilities.error(e);
            }
        }
        onExit();
        world.changed();
    }

    public synchronized void start() {
        myThread = new Thread(this);
        myThread.start();
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        suspended = false;
        notify();
    }

    public synchronized void stop() {
        stopped = true;
    }

    public abstract void update(); // child classes should flush this out

    private static void moveLeft(Agent agent) {
        agent.xc = (agent.xc + Simulation.SIZE - 1) % Simulation.SIZE;
    }

    private static void moveRight(Agent agent) {
        agent.xc = (agent.xc + 1) % Simulation.SIZE;
    }

    private static void moveUp(Agent agent) {
        agent.yc = (agent.yc + Simulation.SIZE - 1) % Simulation.SIZE;
    }

    private static void moveDown(Agent agent) {
        agent.yc = (agent.yc + 1) % Simulation.SIZE;
    }

    public void move(int steps) {
        // TODO - move this agent
        for (int i = 0; i < steps; i++) {
            // move 1 step
            Agent.moveMap.get(heading).accept(this);

            world.changed();
        }
    }

    public void setWorld(Simulation world) {
        this.world = world;
    }

    public void onStart() {
    }

    public void onInterrupted() {
    }

    public void onExit() {
    }

    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }
}
