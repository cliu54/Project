package ca.uvic.seng330.ex5;

import java.util.Date;
import java.io.*;

public final class Observation {
    private final int observationId;
    private final Reporter reporter;
    private final Whale whale;
    private final Date timeStamp;
    private final Location location;
    private final Weather weather;


    public Observation(int observationId, Reporter reporter, Whale whale, Date timeStamp, Location location) throws IOException {
        assert reporter != null && whale != null && timeStamp != null && location != null;

        this.observationId = observationId;
        this.reporter = reporter;
        this.whale = whale;
        this.timeStamp = timeStamp;
        this.location = location;
        this.weather = new Weather(location);
    }

    public Observation(Observation observation){

        this.observationId=observation.getObservationId();
        this.reporter=observation.getReporter();
        this.whale=observation.getWhale();
        this.timeStamp=observation.getDateTime();
        this.location=observation.getLocation();
        this.weather=observation.getWeather();
    }
    
    public Observation(int id){
        this.observationId = id;
        this.reporter = null;
        this.whale = null;
        this.timeStamp = null;
        this.location = null;
        this.weather = null;
    }

    public Observation(Location location) throws IOException {
        this.observationId = -1;
        this.reporter = null;
        this.whale = null;
        this.timeStamp = null;
        this.location = location;
        this.weather = new Weather(location);
    }


    public int getObservationId() {
        return this.observationId;
    }

    public Reporter getReporter(){
        return this.reporter;
    }

    public Whale getWhale(){
        return this.whale;
    }

    public Date getDateTime(){
        return this.timeStamp;
    }

    public Location getLocation(){
        return this.location;
    }

    public Weather getWeather() //throws IOException
    {
        return this.weather;
    }


    public String toString(){
        String str = "Observation Id: " + String.format("%04d", observationId)
                + "\nReporter Name: " + reporter.getName()
                + "\nReporter ID: " + String.format("%04d", reporter.getId())
                + "\nWhale ID: " + String.format("%04d", whale.getId())
                + "\nDate and Time: " + timeStamp
                + "\nLocation: " + location
                + "\nMinimum temperature (celsius): " + weather.getTempMin()
                + "\nMaximum temperature (celsius): " + weather.getTempMax();
        return str;
    }
}
