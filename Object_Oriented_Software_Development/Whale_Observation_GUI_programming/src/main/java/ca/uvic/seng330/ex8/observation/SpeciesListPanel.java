package ca.uvic.seng330.ex8.observation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class SpeciesListPanel extends HBox {
  private static final int ROW_HEIGHT = 24;
  ListView<String> list = new ListView<String>();
  private String selected;

  SpeciesListPanel() {
    ObservableList<String> items = FXCollections.observableArrayList (
        "Orca", "Humpback", "Grey", "Porpoise"); //hmm, we've seen this before
    list.setItems(items);
    list.setPrefHeight(items.size() * ROW_HEIGHT + 2);
    getChildren().add(list);

    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        selected = newValue;
      }
    });
  }

  public String getSelected() {
    return selected;
  }
}
