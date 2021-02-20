package ca.uvic.seng330.ex8.observation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class InputObservationPanel extends HBox {
  private Slider aSlider = createSlider();
  private int num = 1;
  InputObservationPanel() {
    aSlider.setValue(0);
    getChildren().add(aSlider);

    aSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        num = newValue.intValue();
      }
    });
  }

  private static Slider createSlider() {
    Slider slider = new Slider(1, 10, 5);
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMinWidth(ObservationApp.WIDTH);
    slider.setMajorTickUnit(1);
    slider.setBlockIncrement(1);
    slider.setMinorTickCount(0);
    slider.setSnapToTicks(true);
    return slider;
  }

  public int getNum() {
    return this.num;
  }
}

