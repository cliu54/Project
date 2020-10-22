package ca.uvic.seng330.ex4;

import java.util.Date;

public final class Observation {
    private final int observationId;
    private final Reporter reporter;
    private final Whale whale;
    private final Date timeStamp;
    private final Location location;


    public Observation(int observationId, Reporter reporter, Whale whale, Date timeStamp, Location location) {
        if(reporter == null || whale == null || timeStamp == null || location == null) throw new NullPointerException();
        this.observationId = observationId;
        this.reporter = reporter;
        this.whale = whale;
        this.timeStamp = timeStamp;
        this.location = location;

    }

    public Observation(Observation observation){

        this.observationId=observation.getObservationId();
        this.reporter=observation.getReporter();
        this.whale=observation.getWhale();
        this.timeStamp=observation.getDateTime();
        this.location=observation.getLocation();

    }
    
    public Observation(int id){
        this.observationId = id;
        this.reporter = null;
        this.whale = null;
        this.timeStamp = null;
        this.location = null;
    }

    public Observation(Location location){
        this.observationId = -1;
        this.reporter = null;
        this.whale = null;
        this.timeStamp = null;
        this.location = location;
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

    public String toString(){
        String str = "Observation Id: " + String.format("%04d", observationId)
                + "\nReporter Name: " + reporter.getName()
                + "\nReporter ID: " + String.format("%04d", reporter.getId())
                + "\nWhale ID: " + String.format("%04d", whale.getId())
                + "\nDate and Time: " + timeStamp
                + "\nLocation: " + location;
        return str;
    }
}
