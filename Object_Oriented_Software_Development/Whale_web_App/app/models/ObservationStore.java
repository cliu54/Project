package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservationStore implements DataStore<Observation> {
    private List<Observation> observations;
    private int size;

    public ObservationStore() {
        observations = new ArrayList<>();
        size = 0;
    }

    @Override
    public void add(Observation observation) {
        observations.add(observation);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Observation> iterator() {
        return observations.iterator();
    }

    public ArrayList<Observation> getObservations() {
        return new ArrayList<>(observations);
    }

}
