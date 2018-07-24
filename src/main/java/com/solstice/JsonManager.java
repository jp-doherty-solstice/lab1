package com.solstice;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JsonManager {

    public static JsonArray getJsonArrayFromUrl(URL url) throws IOException {
        InputStream stream = null;
        JsonReader jsonReader = null;
        JsonArray jsonArray = null;
        try {
            stream = url.openStream();
            jsonReader = Json.createReader(stream);
            jsonArray = jsonReader.readArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jsonReader.close();
            stream.close();
        }
        return jsonArray;
    }

}
