package ca.uvic.seng330.ex5;

import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.net.URL;

public class RealAPI implements API {
    public JSONObject call(double latitude, double longitude) throws FileNotFoundException, MalformedURLException, IOException {

        String fillURL = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude +
                "&appid=9628badc833925698f66a0df73f98dae";
        URL url = new URL(fillURL);

        //Open a stream on the URL and scan the information into a string.
        String resultString = readFile(url);

        //Create a JSONObject to parse the returned JSON information.
        JSONObject obj = new JSONObject(resultString);
        return obj;
    }

    private String readFile(URL url) throws IOException {
        Scanner scan = new Scanner(url.openStream());
        String resultString = new String();
        while (scan.hasNext()) {
            resultString += scan.nextLine();
        }
        scan.close();
        return resultString;
    }
}
