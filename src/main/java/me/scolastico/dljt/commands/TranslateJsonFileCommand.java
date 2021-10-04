package me.scolastico.dljt.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import me.scolastico.dljt.etc.JsonTranslatorAPI;
import me.scolastico.tools.handler.ErrorHandler;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "file", description = "Translate a json file.")
public class TranslateJsonFileCommand implements Callable<Integer> {

  @Option(
      names = {"-p", "--pretty-print"},
      description = "Save the json file in pretty print format."
  )
  boolean prettyPrint;

  @Parameters(
      description = "The input file.",
      arity = "1"
  )
  File input;

  @Parameters(
      description = "The output file.",
      arity = "1"
  )
  File output;

  @Parameters(
      description = "The language to be translated into.",
      arity = "1"
  )
  String language;

  private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

  @Override
  public Integer call() throws Exception {
    try {
      if (input.exists()) {
        System.out.println("Reading input file...");
        String inputString = FileUtils.readFileToString(input, StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(inputString);
        System.out.println("Starting with translation... (This can take a few minutes)");
        json = JsonTranslatorAPI.translate(json, language);
        System.out.println("Saving translation...");
        String outputString = json.toString();
        if (prettyPrint) {
          outputString = gson.toJson(JsonParser.parseString(outputString));
        }
        FileUtils.writeStringToFile(output, outputString, StandardCharsets.UTF_8);
        System.out.println("Done with translation!");
        return 0;
      } else {
        System.out.println("The file '" + input.getAbsolutePath() + "' doesnt exists!");
        return 2;
      }
    } catch (Exception e) {
      ErrorHandler.handle(e);
      return 1;
    }
  }

}
