package simstation;

import mvc.AppFactory;
import mvc.AppPanel;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
    }
    public static void main(String[] args) {
        SimStationFactory factory = new SimStationFactory();
        SimulationPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
