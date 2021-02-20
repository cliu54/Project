package ca.uvic.seng330.ex8.observation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class ObservationApp extends Application{

  public static final int WIDTH = 200;
  public int counter = 0;
  private static final int GAP = 10;
  private static final int MARGIN = 20;

  /**
   * Launches the application.
   * @param pArgs This program takes no argument.
   */
  public static void main(String[] pArgs)
  {
    launch(pArgs);
  }

  @Override
  public void start(Stage pPrimaryStage)
  {
    //Model model = new Model();
    GridPane root = createPane(); // The root of the GUI component graph

    ObservationData model = new ObservationData();

    ObservationChartPanel chartPanel = new ObservationChartPanel(model);
    model.addObserver(chartPanel);



    InputObservationPanel inputPanel = new InputObservationPanel();
    SpeciesListPanel speciesPanel = new SpeciesListPanel();
    SubmitButtonBox submitBox = new SubmitButtonBox(model, inputPanel, speciesPanel);
    Label myLabel = model.label;

    root.add(chartPanel, 0, 0, 1, 1);
    root.add(inputPanel, 1, 1, 1, 1);
    root.add(speciesPanel, 0, 1, 1, 1);
    root.add(submitBox, 1, 2, 1, 1);
    root.add(myLabel,1,1,1,1);


    pPrimaryStage.setTitle("Observation App");
    pPrimaryStage.setResizable(false);
    pPrimaryStage.setScene(new Scene(root));
    pPrimaryStage.show();
  }

  /*
   * Helper method to hid the details of creating
   * a nice looking grid.
   */
  private static GridPane createPane()
  {
    GridPane root = new GridPane();
    root.setHgap(GAP);
    root.setVgap(GAP);
    root.setPadding(new Insets(MARGIN));
    return root;
  }
}