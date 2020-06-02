package RestApi;

import org.json.JSONException;
import org.json.JSONObject;

public class main {
    public static void main(String[] args) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("Command", "CreateNewUser");
        json.put("User", "user");
        System.out.println(json);
    }
}
