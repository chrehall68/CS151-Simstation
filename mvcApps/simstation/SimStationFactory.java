package simstation;

import mvc.*;

public abstract class SimStationFactory implements AppFactory {
    public abstract Model makeModel();
    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation)m);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[]{"TODO put a help message here"};
    }

    @Override
    public String about() {
        return "TODO put about here";
    }

    @Override
    public String[] getEditCommands() { return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"}; }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        switch (name) {
            case "Start":
                return new SimstationCommands.StartCommand(model);
            case "Suspend":
                return new SimstationCommands.SuspendCommand(model);
            case "Resume":
                return new SimstationCommands.ResumeCommand(model);
            case "Stop":
                return new SimstationCommands.StopCommand(model);
            case "Stats":
                return new SimstationCommands.StatsCommand(model);
            default:
                return null;
        }
    }
}
