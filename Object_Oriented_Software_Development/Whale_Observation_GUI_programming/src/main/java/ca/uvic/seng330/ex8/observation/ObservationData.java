package ca.uvic.seng330.ex8.observation;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Label;

/**
 * The observable object.
 */
public class ObservationData {

  private static final int DEFAULT = 5;
  private static final int MAX = 10;
  public int counter = 0;
  Label label =  new Label("Total Observation Entered:" + 0);

  private ArrayList<Observer> aObservers = new ArrayList<Observer>();
  private HashMap<String, Integer> observations = new HashMap();
  private int aNumber = DEFAULT;

  public ObservationData() {
    observations.put("Orca", 0);
    observations.put("Grey", 0);
    observations.put("Humpback", 0);
    observations.put("Porpoise", 0);
  }

  public void addObserver(Observer pObserver) {
    aObservers.add(pObserver);
  }

  private void notifyObservers(String species) {
    for (Observer observer : aObservers) {
      observer.addObservations();
    }
  }

  public void addObservations(String species, int num) {

    if(species==null)
      return;
    counter++;
    label = totalLabel();

    if (observations.containsKey(species)) {
      observations.put(species, observations.get(species) + num);
    } else {
      observations.put(species, num);
    }
    notifyObservers(species);
  }

  public int getSightings(String species) {
    return observations.get(species);
  }

  public Label totalLabel()
  {
    setId();
    label.setText ("Total Observation Entered:"+ counter);
    return label;
  }

  public void setId(){
      label.setId("mylabel");
  }
}