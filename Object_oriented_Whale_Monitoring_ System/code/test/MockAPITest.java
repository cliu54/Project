package ca.uvic.seng330.ex5;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class MockAPITest {

    @Test
    public void testMockAPI() {
        API api = new MockAPI();
        APIFactory.setInstance(api);
        try {
            JSONObject object = api.call(0, 0);
            Assertions.assertEquals(object.getJSONObject("main").getDouble("temp_min"), 250.3);
            Assertions.assertEquals(object.getJSONObject("main").getDouble("temp_max"), 257.41);
            Assertions.assertEquals(object.getInt("visibility"), 10000);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
}
