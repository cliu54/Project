package ca.uvic.seng330.ex5;

import java.io.IOException;
import java.util.*;

public class ObservationCatalog implements Iterable<Observation>{
    private ArrayList<Observation> observations;

    public ObservationCatalog(){
        this.observations = new ArrayList<Observation>();
    }

    public ObservationCatalog(ObservationCatalog obs) {
        this.observations = obs.getObservationCatalog();
    }

    private ArrayList<Observation> getObservationCatalog(){
        return this.observations;
    }
    public Observation getObservation(int id) {
        return observations.get(id);
    }

    public Observation addObservation(Reporter reporter, Whale whale, Date timeStamp, Location location) throws IOException {

        Observation newObservation = createObservation(getId(), reporter, whale, timeStamp, location);
        observations.add(newObservation);
        return newObservation;
    }

    private int getId(){
        return this.observations.size();
    }

    public Observation createObservation(int id, Reporter reporter, Whale whale, Date timeStamp, Location location) throws IOException {
        Observation o = new Observation(id, reporter, whale, timeStamp, location);
        return o;
    }

    public int size(){
        return observations.size();
    }

    public void sortById(){
        if(this.observations.size() > 1)
        Collections.sort(this.observations,new CompareByObservationID());
    }

    public Optional<Observation> searchById(int id){

        Observation obs = new Observation(id);

        sortById();

        int index = Collections.binarySearch(this.observations, obs, new CompareByObservationID());

        if (index < 0)
            return Optional.empty();
        else
            return Optional.of(this.observations.get(index));

    }

    public void sortByLocation(){
        if(this.observations.size() > 1)
        Collections.sort(this.observations,new CompareByLocation());
    }

    public Optional<Observation> searchByLocation(double longitude,double latitude) throws IOException {

        Observation obs = new Observation(new Location(longitude,latitude));

        sortByLocation();

        int index = Collections.binarySearch(this.observations,obs,new CompareByLocation());

        if(index < 0)
            return Optional.empty();
        else
            return Optional.of(this.observations.get(index));

    }

    static class CompareByObservationID implements Comparator<Observation>{

       public int compare(Observation observation1, Observation observation2)
       {
           return observation1.getObservationId()-observation2.getObservationId();
       }

    }

    static class CompareByLocation implements Comparator<Observation>{

       public int compare(Observation observation1, Observation observation2)
       {
           return observation1.getLocation().compareTo(observation2.getLocation());
       }

    }

    public Iterator<Observation> iterator(){
        return this.observations.iterator();
    }

    

}
