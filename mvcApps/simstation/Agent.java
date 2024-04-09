package simstation;

import mvc.Utilities;

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

    private void moveLeft() {
        xc = (xc + Simulation.SIZE - 1) % Simulation.SIZE;
    }

    private void moveRight() {
        xc = (xc + 1) % Simulation.SIZE;
    }

    private void moveUp() {
        yc = (yc + Simulation.SIZE - 1) % Simulation.SIZE;
    }

    private void moveDown() {
        yc = (yc + 1) % Simulation.SIZE;
    }

    public void move(int steps) {
        // TODO - move this agent
        for (int i = 0; i < steps; i++) {
            // move 1 step
            switch (heading) {
                case EAST: {
                    moveLeft();
                    break;
                }
                case WEST: {
                    moveRight();
                    break;
                }
                case NORTH: {
                    moveUp();
                    break;
                }
                case SOUTH: {
                    moveDown();
                    break;
                }
                case NORTHEAST: {
                    moveUp();
                    moveLeft();
                    break;
                }
                case NORTHWEST: {
                    moveUp();
                    moveRight();
                    break;
                }
                case SOUTHEAST: {
                    moveDown();
                    moveLeft();
                    break;
                }
                case SOUTHWEST: {
                    moveDown();
                    moveRight();
                    break;
                }
            }

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
