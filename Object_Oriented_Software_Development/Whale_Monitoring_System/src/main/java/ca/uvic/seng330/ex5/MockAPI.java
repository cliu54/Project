package ca.uvic.seng330.ex5;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.net.MalformedURLException;

public class MockAPI implements API  {
    public JSONObject call(double latitude, double longitude) throws FileNotFoundException, MalformedURLException, IOException {
        File file = new File("object.json");
        Scanner scan = new Scanner(file);
        String resultString = new String();
        while (scan.hasNext()) {
            resultString += scan.nextLine();
        }
        scan.close();
        JSONObject obj = new JSONObject(resultString);
        return obj;
    }
}
