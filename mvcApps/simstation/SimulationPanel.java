package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

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

    public void setModel(Model m) {  // Dr. Pearce's setModel
        super.setModel(m); // calling AppPanel.setModel(m)
        Simulation s = (Simulation)m;
        Iterator<Agent> it = s.agentIterator();
        while(it.hasNext()) {
            Thread t = new Thread(it.next());
            t.start(); // this will call Agent.run (see below)
        }
    }
    public void actionPerformed(ActionEvent actionEvent) {
        String cmmd = actionEvent.getActionCommand();
        Simulation simulation = (Simulation) model;

        if ((cmmd.equals("Open") || cmmd.equals("Save") || cmmd.equals("New") || cmmd.equals("Save As") || cmmd.equals("Quit")) && simulation.isRunning() && !simulation.isSuspended()) {
            Utilities.error("Cannot perform action while simulation isn't suspended. Please suspend it first.");
            return;
        }
        super.actionPerformed(actionEvent);
    }
}
