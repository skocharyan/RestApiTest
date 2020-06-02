package RestApi;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonGenerator {
    public String genJson(String property , String value) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(property,value);
        return jsonObject.toString();

    }
}
