package flocking;

import mvc.Utilities;
import simstation.*;

import java.util.List;

public class Bird extends Agent{
    private static final int RADIUS = 20;
    private static final double MULTIPLIER = 0.9;
    public static final int MAX_SPEED = 5;
    private double speed;
    public Bird() {
        super();
        speed = Utilities.rng.nextInt(MAX_SPEED)+1;
    }

    @Override
    public void update() {
        List<Agent> neighbors = world.getAllNeighbors(this, RADIUS);
        if (!neighbors.isEmpty()){
            double averageX = 0;
            double averageY = 0;
            double averageSpeed = 0;
            for (Agent neighbor : neighbors){
                Bird bird = (Bird) neighbor;
                averageSpeed += bird.speed;
                averageX += bird.heading.getxDir();
                averageY += bird.heading.getyDir();
            }
            Heading oldHeading = heading;
            heading = new Heading(averageX, averageY);
            heading = new Heading(heading.getxDir() + MULTIPLIER* oldHeading.getxDir(),
                    heading.getyDir() + MULTIPLIER* oldHeading.getyDir());
            speed = (1-MULTIPLIER)*(averageSpeed/neighbors.size()) + MULTIPLIER*speed;
        }
        move((int)speed);
    }
    public synchronized int getSpeed(){
        return (int)speed;
    }
}
