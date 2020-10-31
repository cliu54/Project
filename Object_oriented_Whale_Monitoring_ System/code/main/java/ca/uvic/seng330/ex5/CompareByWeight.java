package ca.uvic.seng330.ex5;

import java.util.Comparator;

public class CompareByWeight implements Comparator<Whale> {

    public int compare(Whale whale1, Whale whale2){
        return whale1.getWeight()-whale2.getWeight();
    }
}