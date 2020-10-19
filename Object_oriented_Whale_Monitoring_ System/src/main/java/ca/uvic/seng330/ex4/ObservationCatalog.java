package ca.uvic.seng330.ex4;

import java.util.*;

public class ObservationCatalog implements Iterable<Observation>{
    private ArrayList<Observation> catalog;

    public ObservationCatalog(){
        this.catalog = new ArrayList<Observation>();
    }

    public ObservationCatalog(ObservationCatalog obs) {
        this.catalog = obs.getObservationCatalog();
    }

    private ArrayList<Observation> getObservationCatalog(){
        return this.catalog;
    }
    public Observation getObservation(int id) {
        return catalog.get(id);
    }

    public Observation addObservation(Reporter reporter, Whale whale, Date timeStamp, Location location) {

        Observation newObservation = createObservation(getId(), reporter, whale, timeStamp, location);
        catalog.add(newObservation);
        return newObservation;
    }

    private int getId(){
        return this.catalog.size();
    }

    public Observation createObservation(int id, Reporter reporter, Whale whale, Date timeStamp, Location location){
        Observation o = new Observation(id, reporter, whale, timeStamp, location);
        return o;
    }

    public int size(){
        return catalog.size();
    }

    public void sortById(){
        if(this.catalog.size() > 1)
        Collections.sort(this.catalog,new CompareByObservationID());
    }

    public Optional<Observation> searchById(int id){

        Observation obs = new Observation(id);

        sortById();

        int index = Collections.binarySearch(this.catalog, obs, new CompareByObservationID());

        if (index < 0)
            return Optional.empty();
        else
            return Optional.of(this.catalog.get(index));

    }

    public void sortByLocation(){
        if(this.catalog.size() > 1)
        Collections.sort(this.catalog,new CompareByLocation());
    }

    public Optional<Observation> searchByLocation(double longitude,double latitude){

        Observation obs = new Observation(new Location(longitude,latitude));

        sortByLocation();

        int index = Collections.binarySearch(this.catalog,obs,new CompareByLocation());

        if(index < 0)
            return Optional.empty();
        else
            return Optional.of(this.catalog.get(index));

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
        return this.catalog.iterator();
    }

    

}
