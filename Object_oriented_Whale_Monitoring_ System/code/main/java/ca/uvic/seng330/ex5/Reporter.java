package ca.uvic.seng330.ex5;

import java.util.ArrayList;

public class Reporter {
    private final int id;
    private final String name;
    private ArrayList<Observation> observations;

    public Reporter(int id, String name) {
        if(name == null) throw new NullPointerException();
        this.id = id;
        this.name = name;
        this.observations= new ArrayList<Observation>();
    }

    public Reporter(Reporter reporter){
        this.id = reporter.getId();
        this.name = reporter.getName();
        this.observations= reporter.getObservations();
    }

    public ArrayList<Observation> getObservations(){
        return this.observations;
    }
    
    public void addObservation(Observation obs){
        this.observations.add(obs);
    }
 
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

