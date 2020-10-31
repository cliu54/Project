package ca.uvic.seng330.ex5;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    //Test if the service is working.
    @Test
    public void testWeatherService() {
      //Create the api object instance.
      API api = APIFactory.getInstance();

      //We will use Victoria's coordinates for each test.
      double VicLon = -123.3656;
      double VicLat = 48.4284;

      JSONObject obj = new JSONObject();
      try{
        obj = api.call(VicLat, VicLon);
      }
      catch (Exception e){
        System.out.println(e.getMessage());
        fail();
      }

      //Since the value of the temperature is constantly changing, we will just check that
      //the "temp_max" and "temp_min" keys are present in the JSONObject.
      assertTrue(obj.getJSONObject("main").has("temp_min"));
      assertTrue(obj.getJSONObject("main").has("temp_max"));
      assertTrue(obj.has("visibility"));
    }
}

