package simstation;

import mvc.*;

import javax.swing.*;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        addControl(new JButton("Start"));
        addControl(new JButton("Suspend"));
        addControl(new JButton("Resume"));
        addControl(new JButton("Stop"));
        addControl(new JButton("Stats"));
    }

    private void addControl(JButton control) { // helper method to avoid having to copy/paste
        control.addActionListener(this);
        JPanel container = new JPanel();
        container.add(control);
        controls.add(container);
    }
}
