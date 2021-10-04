package me.scolastico.dljt.routines.starting;

import java.util.HashMap;
import me.scolastico.tools.handler.ErrorHandler;
import me.scolastico.tools.routine.Routine;
import me.scolastico.tools.routine.RoutineAnswer;

public class ErrorRoutine implements Routine {

  @Override
  public RoutineAnswer execute(HashMap<String, Object> hashMap) throws Exception {
    ErrorHandler.enableErrorLogFile();
    ErrorHandler.enableCatchUncaughtException();
    ErrorHandler.enableSentry("https://05f8a39b9f4b4b7da970d0d0ae81196f@sentry.scolasti.co/6");
    return new RoutineAnswer(hashMap);
  }

}
