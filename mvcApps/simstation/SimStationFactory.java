package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public abstract class SimStationFactory implements AppFactory {
    @Override
    public View makeView(Model m) {
        return new SimulationView(m);
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
    public String[] getEditCommands() {
        return new String[0];
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        return null;  // TODO - make edit commands here
    }
}
