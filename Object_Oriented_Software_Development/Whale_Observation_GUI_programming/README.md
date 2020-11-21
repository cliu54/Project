Please read thru the [overall exercises overview](https://github.com/SENG330/course/blob/master/exercises/Exercises.md).

# Exercise 8
1. Extend the starter GUI app that allows users to enter observations of Whales, and shows a chart of observations.
	5. Take the starter JavaFX code and use MVC and Observer concepts to handle state changes. The final code should demonstrate how the number of whales of a given species entered is reflected in the whale chart. 
2. Add at least two FXTest tests to show UI testing. Use the sample code in the `/test` directory.
3. Update the code where needed, to conform to general OO principles as we have been discussing, including preserving encapsulation and preventing duplication.
4. Create an ADR and Sequence Diagram that show the rationale behind your team's MVC approach.

## Learning Objectives
- MVC and basic GUI programming.
- Unit testing of GUI code.

## Deliverables
* unit tests of the JavaFX code, showing how the model changes are managed, and one GUI test using TestFX.
* A sequence diagram that shows the Observer/MVC pattern from a user entering a value, until the chart is updated.
* The source code of the finished MVC pattern.
* An ADR explaining the approach your group chose to implement the pattern. 

## Tips
* Get started early, ensuring the Gradle script is working correctly. 
    * The JavaFX part may not run in the IDE directly; you may have to modify the gradle script to point Application/`mainclassName` to the class you use.
    * I added some sample JavaFX code in `javafxsample`. There is lots online.
* Read the MVC section in the book and study the sample code. 
* Do not directly update the chart based on the user's submission. A Model should be used to ensure consistency.
* Make sure to follow Rohith's video on how to keep code clean. We will be focusing on code style and understandability.
* https://docs.oracle.com/javafx/2/charts/bar-chart.htm will be a useful tutorial on JavaFX GUI charts.
* The JavaFX `SimpleNumber` sample updates whenever the user hits the `enter` key. This one should not do anything until the button is clicked.
* Think carefully about the control flow and necessary event handlers and callbacks. Do this before you write code. It will likely be quicker to create the sequence diagram first! ("A day of coding can save an hour of modeling").
* Full 5 marks are for apps that enhance the look and feel, clean up my layout, etc. 
 
## Due
- We will mark the last commit made before **Nov 20 at 11:59pm**. If that last commit was a mess, let us know. Make sure your code compiles!
- By midnight Nov 22, submit your team peer review form via the webapp (link omitted to prevent spam). *You must add text that explains your rating if it is not Very Good or Excellent*.
