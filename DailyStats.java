package gursimar_hehar_project;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Gursimar Singh Hehar
 * this class represents daily stats of coronavirus
 */
public class DailyStats {
    
      /**
 *
 * This method is used to return global statistics of coronaVirus from a private API at rapidAPI.
 * @return String
 * 
 */
    public static String display() throws ParseException{
      //Creating StringBuilder
        StringBuilder stringBuilder = null;

        //API Credentials
        String apiLink = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php";
        String key = "893ac47585msh50e4355db8b189dp1c33a1jsn6aa0455f1574";
        String host = "coronavirus-monitor.p.rapidapi.com";

        try {
            //creating connection with api now
            URL url = new URL(apiLink);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", host);
            connection.setRequestProperty("x-rapidapi-key", key);

            //Reading data from api
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Parsing json data 
        JSONParser parser = new JSONParser();
        JSONObject rootObject = (JSONObject) parser.parse(stringBuilder.toString());
        JSONArray array = new JSONArray();
        array.add(rootObject);
        long cases = 0;
        JSONObject dataIn = null;
        for (int i = 0; i < array.size(); i++) {
            dataIn = (JSONObject) array.get(i);

        }

        return "New Cases: " + (String) dataIn.get("new_cases") + "\n"
                + "New Deaths: " + (String) dataIn.get("new_deaths");
             
    }
}
