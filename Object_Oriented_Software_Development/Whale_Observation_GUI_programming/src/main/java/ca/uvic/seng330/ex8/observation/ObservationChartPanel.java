package ca.uvic.seng330.ex8.observation;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

/**
 * A BarChart showing whale counts per Observation
 * see https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/chart/BarChart.html
 */
public class ObservationChartPanel extends HBox implements Observer {
  final NumberAxis xAxis = new NumberAxis();
  final CategoryAxis yAxis = new CategoryAxis();
  final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);
  private ObservationData model;
  private int counter = 5;

  final static String orca = "Orca";
  final static String grey = "Grey";
  final static String humpback = "Humpback";
  final static String porpoise = "Porpoise";

  ObservationChartPanel(ObservationData model) {
    this.model = model;
    bc.setTitle("Observation Summary");

    xAxis.setLabel("Count");
    xAxis.setTickLabelRotation(90);
    yAxis.setLabel("Species");

    XYChart.Series series1 = setSightings();
    bc.getData().add(series1); //TODO the call to update the chart. Add this to a callback.

    getChildren().add(bc);
  }

  @Override
  public void addObservations() {
    XYChart.Series series1 = setSightings();
    //label:

    //barchar:
    bc.getData().clear();
    bc.getData().add(series1);
  }

  public XYChart.Series setSightings() {
    counter++;
    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Total Observations");
    XYChart.Data data_orca = new XYChart.Data(model.getSightings("Orca"), orca);
    XYChart.Data data_grey = new XYChart.Data(model.getSightings("Grey"), grey);
    XYChart.Data data_humpback = new XYChart.Data(model.getSightings("Humpback"), humpback);
    XYChart.Data data_porpoise = new XYChart.Data(model.getSightings("Porpoise"), porpoise);
    series1.getData().add(data_orca);
    series1.getData().add(data_grey);
    series1.getData().add(data_humpback);
    series1.getData().add(data_porpoise);
    return series1;
  }
}
