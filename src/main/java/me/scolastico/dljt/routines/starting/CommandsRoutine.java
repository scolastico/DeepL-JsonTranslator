package me.scolastico.dljt.routines.starting;

import java.util.HashMap;
import me.scolastico.dljt.commands.HelpCommand;
import me.scolastico.dljt.commands.TranslateJsonFileCommand;
import me.scolastico.tools.console.ConsoleLoadingAnimation;
import me.scolastico.tools.console.ConsoleManager;
import me.scolastico.tools.handler.ErrorHandler;
import me.scolastico.tools.routine.Routine;
import me.scolastico.tools.routine.RoutineAnswer;
import org.fusesource.jansi.Ansi;

public class CommandsRoutine implements Routine {

  @Override
  public RoutineAnswer execute(HashMap<String, Object> hashMap) throws Exception {
    try {
      System.out.print("Registering commands... ");
      ConsoleLoadingAnimation.enable();
      ConsoleManager.registerCommand(new TranslateJsonFileCommand());
      ConsoleManager.registerCommand(new HelpCommand());
      ConsoleManager.setNotFoundMessage("Command '%' not found! Try 'help' for help!");
      ConsoleLoadingAnimation.disable();
      System.out.println(Ansi.ansi().fgGreen().a("[OK]").reset());
    } catch (Exception e) {
      try {
        ConsoleLoadingAnimation.disable();
      } catch (Exception ignored) {}
      System.out.println(Ansi.ansi().fgRed().a("[FAIL]").reset());
      ErrorHandler.handle(e);
      return new RoutineAnswer(true, "exception");
    }
    return new RoutineAnswer(hashMap);
  }

}
