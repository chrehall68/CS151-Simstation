package simstation.randomwalk;

import mvc.*;
import simstation.*;

class RandomWalkFactory extends SimStationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}