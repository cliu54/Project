package controllers;

public class ObservationData {
    private String gender;
    private int est_size;
    private String species;
    private String sightingDate;

    public ObservationData() {

    }

    public ObservationData(String gender, int est_size, String species, String sightingDate) {
        this.gender = gender;
        this.est_size = est_size;
        this.species = species;
        this.sightingDate = sightingDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getEst_size() {
        return est_size;
    }

    public void setEst_size(int est_size) {
        this.est_size = est_size;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(String sightingDate) {
        this.sightingDate = sightingDate;
    }
}
