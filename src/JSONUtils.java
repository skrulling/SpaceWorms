import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class JSONUtils {

    private static String read(BufferedReader br) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp; // Char position
        while ((cp = br.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject JSONfromURL(String sUrl) throws IOException{
        InputStream is = new URL(sUrl).openStream();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = read(br);
            return new JSONObject(jsonString);
        }finally {
            is.close();
        }

    }

    public static String getNextURL(JSONArray arr){
        String url = "";
        for (int i = 0; i < arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            if (obj.getString("direction").equals("next")){
                url = obj.getString("url");
            }
        }
        return url.replaceAll("http", "https");
    }


}
