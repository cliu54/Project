package models;

public class Whale {

    private Long id;
    private String species;
    private int estimated_weight;
    private Gender gender;

    public Whale(Long id, String species, int estimated_weight, Gender gender) {
        this.id = id;
        this.species = species;
        this.estimated_weight = estimated_weight;
        this.gender = gender;
    }

    public int getEstimated_weight() {
        return estimated_weight;
    }

    public Gender getGender() {
        return gender;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }
}