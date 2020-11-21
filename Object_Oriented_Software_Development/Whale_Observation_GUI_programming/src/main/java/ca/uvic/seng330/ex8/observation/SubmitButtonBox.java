package ca.uvic.seng330.ex8.observation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;


public class SubmitButtonBox extends HBox {
    private String label = "Submit";
    private int counter = 2;
    private String tmp = " ";
    private Button button;
    private ObservationData model;
    private InputObservationPanel inputPanel;
    private SpeciesListPanel speciesPanel;

    SubmitButtonBox(ObservationData model, InputObservationPanel inputPanel, SpeciesListPanel speciesPanel) {

        this.model = model;
        this.inputPanel = inputPanel;
        this.speciesPanel = speciesPanel;
        button = new Button(label);
        button.setId("myButton");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        increment();
        button.setOnAction(actionEvent -> model.addObservations(speciesPanel.getSelected(), inputPanel.getNum()));
        getChildren().add(button);
    }
    public void increment(){
        this.counter++;
    }
  }

