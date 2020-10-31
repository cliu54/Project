package ca.uvic.seng330.ex5;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public interface API {

   public JSONObject call(double latitude, double longitude) throws FileNotFoundException, MalformedURLException, IOException;

}
