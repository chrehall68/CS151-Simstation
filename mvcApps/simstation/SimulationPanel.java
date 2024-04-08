package simstation;

import mvc.*;

import javax.swing.*;

public class SimulationPanel extends AppPanel {
    private JButton start;
    private JButton suspend;
    private JButton resume;
    private JButton stop;
    private JButton stats;
    public SimulationPanel(AppFactory factory) {

        super(factory);
        start = new JButton("Start");
        suspend = new JButton("Suspend");
        resume = new JButton("Resume");
        stop = new JButton("Stop");
        stats = new JButton("Stats");

        start.addActionListener(SimulationPanel.this);
        suspend.addActionListener(SimulationPanel.this);
        resume.addActionListener(SimulationPanel.this);
        stop.addActionListener(SimulationPanel.this);
        stats.addActionListener(SimulationPanel.this);

        controls.add(start);
        controls.add(suspend);
        controls.add(resume);
        controls.add(stop);
        controls.add(stats);

    }

}
