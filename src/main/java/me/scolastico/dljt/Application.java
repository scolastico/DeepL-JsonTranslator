package me.scolastico.dljt;

import java.util.ArrayList;
import me.scolastico.dljt.dataholders.Config;
import me.scolastico.dljt.routines.starting.CommandsRoutine;
import me.scolastico.dljt.routines.starting.ConfigRoutine;
import me.scolastico.dljt.routines.starting.ErrorRoutine;
import me.scolastico.dljt.routines.starting.FinishRoutine;
import me.scolastico.dljt.routines.starting.HeaderRoutine;
import me.scolastico.tools.console.ConsoleLoadingAnimation;
import me.scolastico.tools.console.ConsoleManager;
import me.scolastico.tools.handler.ConfigHandler;
import me.scolastico.tools.handler.ErrorHandler;
import me.scolastico.tools.routine.Routine;
import me.scolastico.tools.routine.RoutineManager;
import me.scolastico.tools.simplified.SimplifiedResourceFileReader;

public class Application {

  private static ConfigHandler<Config> configHandler;
  private static Config config;
  private final static String version = SimplifiedResourceFileReader.getInstance().getStringFromResources("staticVars/VERSION");
  private final static String branch = SimplifiedResourceFileReader.getInstance().getStringFromResources("staticVars/BRANCH");
  private final static String commit = SimplifiedResourceFileReader.getInstance().getStringFromResources("staticVars/COMMIT");

  public static void main(String[] args) {
    try {
      ArrayList<Routine> routines = new ArrayList<>();
      routines.add(new ErrorRoutine());
      routines.add(new HeaderRoutine());
      routines.add(new ConfigRoutine());
      routines.add(new CommandsRoutine());
      routines.add(new FinishRoutine());
      RoutineManager manager = new RoutineManager(routines);
      manager.startNotAsynchronously();

      if (!manager.isCanceled()) {
        if (args.length > 0) {
          System.out.println("Detected arguments. Starting headless mode...");
          System.out.println();
          int response = ConsoleManager.executeCommand(args);
          System.out.println();
          System.out.println("Done with the execution of the command. Exiting the application...");
          System.out.println();
          System.exit(response);
        } else {
          System.out.println("Detected no arguments. Starting console... Enter 'help' for help.");
          System.out.println();
          ConsoleManager.enable(false, true);
        }
      }
    } catch (Exception e) {
      try {
        ConsoleLoadingAnimation.disable();
      } catch (Exception ignored) {}
      ErrorHandler.handleFatal(e);
    }
  }

  public static String getBranch() {
    return branch;
  }

  public static String getCommit() {
    return commit;
  }

  public static String getVersion() {
    return version;
  }

  public static ConfigHandler<Config> getConfigHandler() {
    return configHandler;
  }

  public static void setConfigHandler(ConfigHandler<Config> configHandler) {
    Application.configHandler = configHandler;
  }

  public static Config getConfig() {
    return config;
  }

  public static void setConfig(Config config) {
    Application.config = config;
  }

}
