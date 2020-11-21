package ca.uvic.seng330.ex8;
import ca.uvic.seng330.ex8.observation.*;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ClickableButtonTest_JUnit5Hamcrest {

  private Button button = new Button("Add");
  @BeforeAll
  static void setUp() throws Exception{
    ApplicationTest.launch(ObservationApp.class);
  }


  /**
   * Will be called with {@code @Before} semantics, i. e. before each test method.
   *
   * @param stage - Will be injected by the test runner.
   */
  @Start
  private void start(Stage stage) {
    button  = new Button("Submit");
    button.setId("Submit_id");
    stage.show();
  }

  /**
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void should_contain_button_with_text(FxRobot robot) {
    FxAssert.verifyThat(button, LabeledMatchers.hasText("Submit"));
  }

  @Test
  void check_Total_whale(FxRobot robot){

    robot.clickOn(LabeledMatchers.hasText("Humpback"));

    robot.clickOn(LabeledMatchers.hasText("Submit"));
    robot.clickOn(LabeledMatchers.hasText("Submit"));
    robot.clickOn(LabeledMatchers.hasText("Submit"));
    robot.clickOn(LabeledMatchers.hasText("Submit"));

    FxAssert.verifyThat("#mylabel", LabeledMatchers.hasText("Total Observation Entered:4"));
  }

  /**
   * @param robot - Will be injected by the test runner.
   */
}
