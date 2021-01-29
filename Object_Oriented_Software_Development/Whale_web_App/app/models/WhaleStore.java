package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WhaleStore implements DataStore<Whale> {
    private List<Whale> whales;
    private int size;

    public WhaleStore() {
        whales = new ArrayList<>();
        size = 0;
    }

    @Override
    public void add(Whale whale) {
        whales.add(whale);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Whale> iterator() {
        return whales.iterator();
    }

    public ArrayList<Whale> getWhales() {
        return new ArrayList<>(whales);
    }

}