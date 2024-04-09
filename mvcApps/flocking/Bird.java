package flocking;

import mvc.Utilities;
import simstation.*;
public class Bird extends Agent{
    private static final int RADIUS = 15;
    private int speed;
    public Bird() {
        super();
        speed = Utilities.rng.nextInt(5)+1;
    }

    @Override
    public void update() {
        Bird neighbor = (Bird)world.getNeighbor(this, RADIUS);
        if (neighbor != null){
            // take the heading and speed of the neighbor
            heading = neighbor.heading;
            speed = neighbor.speed;
        }
        move(speed);
    }
    public synchronized int getSpeed(){
        return speed;
    }
}
