package me.scolastico.dljt.etc;

import java.util.Objects;
import me.scolastico.dljt.Application;
import me.scolastico.tools.handler.ErrorHandler;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class DeepLConnector {

  private static final OkHttpClient client = new OkHttpClient();

  public static String translate(String string, String language) {
    try {
      if (Application.getConfig().isDebug()) {
        System.out.println("[DEBUG] Starting translation to '" + language + "' of string: " + string);
      }
      RequestBody body = new FormBody.Builder()
          .add("auth_key", Application.getConfig().getApiToken())
          .add("text", string)
          .add("target_lang", language)
          .build();
      Request request = new Request.Builder()
          .url("https://" + Application.getConfig().getApiDomain() + "/v2/translate")
          .post(body)
          .build();
      Response response = client.newCall(request).execute();
      if (response.code() == 200) {
        JSONObject result = new JSONObject(Objects.requireNonNull(response.body()).string());
        String translatedText = result.getJSONArray("translations").getJSONObject(0).getString("text");
        if (Application.getConfig().isDebug()) {
          System.out.println("[DEBUG] Translation complete: " + translatedText);
        }
        return translatedText;
      }
    } catch (Exception e) {
      ErrorHandler.handle(e);
    }
    return string;
  }

}
