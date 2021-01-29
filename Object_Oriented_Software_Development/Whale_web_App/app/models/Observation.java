package models;

import java.time.LocalDate;

public class Observation {
    private long id;
    private Gender gender;
    private int est_size;
    private String species;
    private LocalDate sightingDate;

    public Observation(long id, Gender gender, int est_size, String species, LocalDate sightingDate) {
        this.id = id;
        this.gender = gender;
        this.est_size = est_size;
        this.species = species;
        this.sightingDate = sightingDate;
    }

    public Observation(Observation oldObservation) {
        this.id = oldObservation.getId();
        this.gender = oldObservation.getGender();
        this.est_size = oldObservation.getEst_size();
        this.species = oldObservation.getSpecies();
        this.sightingDate = oldObservation.getSightingDate();
    }

    public Observation(int id, String species, String species1, LocalDate now) {
    }

    public long getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

    public int getEst_size() {
        return est_size;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }
}
