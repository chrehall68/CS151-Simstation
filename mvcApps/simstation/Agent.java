package simstation;

import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Serializable, Runnable {
    protected String name;
    protected Heading heading;  // TODO - figure out if Heading is a real class
    protected int xc;  // x coordinate
    protected int yc;  // y coordinate
    protected Simulation world;
    private boolean suspended = false;
    private boolean stopped = false;
    transient protected Thread myThread;

    public Agent(String name){
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
        while(!stopped){
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch(InterruptedException e){
                Utilities.error(e);
            }
        }
        onExit();
        world.changed();
    }
    public synchronized void start(){
        myThread = new Thread(this);
        myThread.start();
    }
    public synchronized void suspend(){
        suspended = true;
    }
    public synchronized void resume(){
        // TODO - resume the agent

    }
    public synchronized void stop(){
        stopped = true;

    }
    public abstract void update();  // child classes should flush this out

    public void move(int steps){
        // TODO - move this agent
        for (int i = 0; i < steps; i++){
            //move 1 step
            world.changed();
        }
    }
    public void setWorld(Simulation world){
        this.world = world;
    }
    public void onStart() {}
    public void onInterrupted() {}
    public void onExit() {}
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }
}
