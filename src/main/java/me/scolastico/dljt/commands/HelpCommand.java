package me.scolastico.dljt.commands;

import com.github.freva.asciitable.AsciiTable;
import java.util.concurrent.Callable;
import me.scolastico.dljt.Application;
import picocli.CommandLine.Command;

@Command(name = "help", description = "Shows a help page with a list of all commands and a short description.")
public class HelpCommand implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    String[] headers = {"Command", "Description"};
    String[][] data = {
        {"help", "Shows this help page."},
        {"file", "Translate a json file."},
        {"exit", "Exit the application. (In console mode.)"}
    };
    String[] generatedTable = AsciiTable.getTable(headers, data).split("\\r?\\n|\\r");
    System.out.println();
    System.out.println("DeepL Json Translator - Version " + Application.getVersion() + " - " + Application.getBranch() + "/" + Application.getCommit());
    for(String output:generatedTable) {
      System.out.println(output);
    }
    System.out.println("Use '--help' after an command to show command specific help pages.");
    System.out.println();
    return 0;
  }

}
