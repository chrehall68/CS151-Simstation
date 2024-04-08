package simstation;

import java.io.Serializable;

public abstract class Agent implements Serializable, Runnable {
    private String name;
    // private Heading heading;  // TODO - figure out if Heading is a real class
    private int xc;  // x coordinate
    private int yc;  // y coordinate
    private Simulation world;

    private boolean suspended = false;
    private boolean stopped = false;
    transient protected Thread myThread;

    @Override
    public void run() {
        // TODO - run the agent
    }
    public void start(){
        // TODO - start the agent
    }
    public void suspend(){
        // TODO - suspend this agent
    }
    public void resume(){
        // TODO - resume the agent
    }
    public void stop(){
        // TODO - stop the agent

    }
    public abstract void update();  // child classes should flush this out

    public void move(int steps){
        // TODO - move this agent
    }
}
