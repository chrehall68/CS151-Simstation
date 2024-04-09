package flocking;

import mvc.Model;
import simstation.*;

public class FlockingFactory extends SimStationFactory {
    @Override
    public Model makeModel() {
        return new FlockingSimulation();
    }

    @Override
    public String getTitle() {
        return "Flocking Simulator";
    }
}
