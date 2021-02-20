package controllers;

public class WhaleData {
    private String species;
    private int est_size;
    private String gender;

    public WhaleData() {

    }

    public WhaleData(String species, int estimated_weight, String gender) {
        this.species = species;
        this.est_size = estimated_weight;
        this.gender = gender;
    }

    public int getEst_size() {
        return est_size;
    }

    public void setEst_size(int estimated_weight1) {
        this.est_size = estimated_weight1;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species1) {
        this.species = species1;
    }
}