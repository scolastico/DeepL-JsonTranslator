package me.scolastico.dljt.etc;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTranslatorAPI {

  public static JSONObject translate(final JSONObject json, final String language) {
    JSONObject ret = new JSONObject();
    for (String key:json.keySet()) {
      Object object = json.get(key);
      if (object instanceof String) {
        ret.put(key, DeepLConnector.translate((String) object, language));
      } else if (object instanceof JSONArray) {
        ret.put(key, translate((JSONArray) object, language));
      } else if (object instanceof JSONObject) {
        ret.put(key, translate((JSONObject) object, language));
      } else {
        ret.put(key, object);
      }
    }
    return ret;
  }

  public static JSONArray translate(final JSONArray json, final String language) {
    JSONArray ret = new JSONArray();
    for (int i = 0; i < json.length(); i++) {
      Object object = json.get(i);
      if (object instanceof String) {
        ret.put(DeepLConnector.translate((String) object, language));
      } else if (object instanceof JSONArray) {
        ret.put(translate((JSONArray) object, language));
      } else if (object instanceof JSONObject) {
        ret.put(translate((JSONObject) object, language));
      } else {
        ret.put(object);
      }
    }
    return ret;
  }

}
