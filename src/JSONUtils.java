import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Utils used to get board information from the API
 */
public class JSONUtils {
    /**
     * Method used to read the JSON and building a String from it
     * @param br The BufferedReader that points to the right InputStream
     * @return  String that represents the JSON object
     * @throws IOException
     */
    private static String read(BufferedReader br) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp; // Char position
        while ((cp = br.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Method that will read the JSON displayed on a given url
     * @param sUrl String containing url that will be read
     * @return  JSON object that was displayed in url
     * @throws IOException
     */
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

    /**
     * Gets the url to the next JSON object in the API
     * @param arr JSON array containing links to other JSON objects representing squares on the play-board
     * @return url of the next JSON object the Board needs
     */
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
