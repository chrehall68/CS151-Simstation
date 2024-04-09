package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        controls.setLayout(new GridLayout(5, 1));
        addControl(new JButton("Start"));
        addControl(new JButton("Suspend"));
        addControl(new JButton("Resume"));
        addControl(new JButton("Stop"));
        addControl(new JButton("Stats"));
    }
    private void addControl(JButton control){  // helper method to avoid having to copy/paste
        control.addActionListener(this);
        JPanel container = new JPanel();
        container.add(control);
        controls.add(container);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String cmmd = actionEvent.getActionCommand();
        Simulation simulation = (Simulation) model;

        if ((cmmd == "Save" || cmmd == "SaveAs")
                && (simulation.isRunning() && !simulation.isSuspended())) {
            Utilities.error("Cannot save active simulation that isn't suspended. Please suspend it first.");
            return;
        }
        super.actionPerformed(actionEvent);
    }

}
