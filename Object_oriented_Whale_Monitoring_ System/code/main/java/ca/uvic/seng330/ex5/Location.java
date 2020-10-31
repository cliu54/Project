package ca.uvic.seng330.ex5;

import java.lang.*;

public class Location implements Comparable<Location>{

    public double longitude;
    public double latitude;

    public Location(double longitude, double latitude){
        if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180)
        {
            this.longitude = 0.0;
            this.latitude = 0.0;
        }
        else
        {
            this.longitude = longitude;
            this.latitude = latitude;
        }
    }

    public Location(Location location){
        if (location.getLatitude() > 90 || location.getLatitude() < -90 ||
                location.getLongitude() > 180 || location.getLongitude() < -180)
        {
            this.longitude = 0.0;
            this.latitude = 0.0;
        }
        else
        {
            this.longitude = location.getLongitude();
            this.latitude = location.getLatitude();
        }
    }

    public int compareTo(Location location){

        if(this.longitude>location.longitude && this.latitude>location.latitude)
            return 1;
        else if(this.longitude==location.longitude && this.latitude==location.latitude)
            return 0;
        else if(this.longitude==location.longitude)
            return (int)(this.latitude-location.latitude);
        else if(this.latitude==location.latitude)
            return (int)(this.longitude-location.longitude);
        else
            return (int)(Math.max(this.longitude,this.latitude)-Math.max(location.longitude,location.latitude));

    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public String toString(){
        return "(" + String.valueOf(longitude) + "," + String.valueOf(latitude) + ")";
    }
}

