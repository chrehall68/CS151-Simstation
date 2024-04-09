package simstation.randomwalk;

import mvc.AppFactory;
import simstation.*;

public class RandomWalkPanel extends SimulationPanel {
    public RandomWalkPanel(AppFactory factory) {
        super(factory);
    }
    public static void main(String[] args) {
        AppFactory factory = new RandomWalkFactory();
        RandomWalkPanel panel = new RandomWalkPanel(factory);
        panel.display();
    }}
