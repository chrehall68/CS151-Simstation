package plague;

import mvc.Utilities;
import simstation.*;

public class Guy extends Agent {
    private static final int RADIUS = 15;
    private boolean isInfected;
    public Guy(){
        super();
        //if (infect(PlagueSimulation.INITIALINFECTION)){
        //    isInfected = true;
        //}
        //else{isInfected = false;}
        isInfected = false;
        heading = Heading.random();
    }
    private boolean infect(int rate){
        if (Utilities.rng.nextInt(100)< rate){
            return true;
        }
        return false;
    }
    public void infect(){this.isInfected=true;}
    private boolean resist(int rate){
        if (Utilities.rng.nextInt(100)<rate){
            return true;
        }
        return false;
    }
    public boolean isInfected(){
        return isInfected;
    }
    public void interact(){

    }
    @Override
    public void update() {
        //int steps = Utilities.rng.nextInt(10) + 1;
        Guy neighbor = (Guy)world.getNeighbor(this, RADIUS);
        if (neighbor != null && neighbor.isInfected && !this.isInfected){
            isInfected = infect(PlagueSimulation.VIRULENCE);
            isInfected = !resist(PlagueSimulation.RESISTANCE);
        }
        heading = Heading.random();
        move(3);
    }
}
