package RestApi;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRepository {
    public String geneBookAddString(String name, String isbn, String ailse, String author) {
        String s = "{" + "\n" +
                " \"name\":\"" + name + "\"" + " , \n" +
                " \"isbn\":\"" + isbn + "\"" + " , \n" +
                " \"aisle\":\"" + ailse + "\"" + " , \n" +
                " \"author\":\"" + author + "\"" + "\n" +
                "}";
        return s;

    }

    public String autoGeneBookAddString(String name, String isbn) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isbn", isbn);
        json.put("aisle", "ailse");
        json.put("author", "author");
        return json.toString();

    }

}
