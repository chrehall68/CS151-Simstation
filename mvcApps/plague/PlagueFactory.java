package plague;

import mvc.*;
import simstation.*;

public class PlagueFactory extends SimStationFactory {
    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }
    @Override
    public View makeView(Model m) {
        return new PlagueView((PlagueSimulation)m);
    }

    @Override
    public String getTitle() {
        return "Plague";
    }
}
