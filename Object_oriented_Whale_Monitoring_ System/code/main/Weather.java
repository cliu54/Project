package ca.uvic.seng330.ex5;

import org.json.JSONObject;

import java.io.*;

//A Weather object will hold the minimum and maximum temperatures for a given location at the current time.
public class Weather
{
    private final double tempMin;
    private final double tempMax;
    private final int visibility;
    private API api = APIFactory.getInstance();

    //Constructor given a Location object.
    public Weather(Location location) throws IOException
    {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        JSONObject obj = api.call(latitude, longitude);
        double tempMinUnrounded = obj.getJSONObject("main").getDouble("temp_min") - 273.15;
        double tempMaxUnrounded = obj.getJSONObject("main").getDouble("temp_max") - 273.15;
        this.visibility = obj.getInt("visibility");
        //Set the minimum and maximum temperatures to the returned information.
        this.tempMin = (double)Math.round(tempMinUnrounded * 10d) / 10d;
        this.tempMax = (double)Math.round(tempMaxUnrounded * 10d) / 10d;
    }

    //Constructor if a Weather object is passed.
    public Weather(Weather weather)
    {
        this.tempMin = weather.getTempMin();
        this.tempMax = weather.getTempMax();
        this.visibility = weather.getVisibility();
    }

    //Return the minimum temperature.
    public double getTempMin()
    {
        return this.tempMin;
    }

    //Return the maximum temperature.
    public double getTempMax()
    {
        return this.tempMax;
    }

    //Return the visibility.
    public int getVisibility(){
        return this.visibility;
    }
}
