package simstation;

import mvc.Utilities;
import mvc.Command;
import mvc.Model;

public class SimStationCommands {
    public static class ResumeCommand extends Command {
        public ResumeCommand(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            model.as(Simulation.class).resume();
        }
    }

    public static class SuspendCommand extends Command {
        public SuspendCommand(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            model.as(Simulation.class).suspend();
        }
    }

    public static class StopCommand extends Command {
        public StopCommand(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            model.as(Simulation.class).stop();
        }
    }

    public static class StatsCommand extends Command {
        public StatsCommand(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            Utilities.inform(model.as(Simulation.class).stats());
        }
    }

    public static class StartCommand extends Command {
        public StartCommand(Model model) {
            super(model);
        }

        @Override
        public void execute() throws Exception {
            model.as(Simulation.class).start();
        }
    }

}
