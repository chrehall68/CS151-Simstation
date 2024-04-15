package simstation;

import mvc.*;

public abstract class SimStationFactory implements AppFactory {
    public abstract Model makeModel();

    @Override
    public View makeView(Model m) {
        return new SimulationView( m);
    }

    @Override
    public abstract String getTitle();

    @Override
    public String[] getHelp() {
        return new String[] { "Press Start to repopulate and start agents",
                "Press Suspend to pause all agents",
                "Press Resume to un-pause all agents",
                "Press Stop to halt all agents. They cannot be resumed after stopping.",
                "Press Stats to view the current simulation statistics" };
    }

    @Override
    public String about() {
        return "Simulation Group 10";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] { "Start", "Suspend", "Resume", "Stop", "Stats" };
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        switch (name) {
            case "Start":
                return new SimStationCommands.StartCommand(model);
            case "Suspend":
                return new SimStationCommands.SuspendCommand(model);
            case "Resume":
                return new SimStationCommands.ResumeCommand(model);
            case "Stop":
                return new SimStationCommands.StopCommand(model);
            case "Stats":
                return new SimStationCommands.StatsCommand(model);
            default:
                return null;
        }
    }
}
