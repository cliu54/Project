## Video demonstration: https://www.youtube.com/watch?v=eneeZsE-UdY
1. Illustrate how to compile and run in sbt
2. play around whale/observation add and search function in the whale web application

Note,There is an issue with build.sbt after I uploaded the project to Github. I will resolve this issue as soon as 
possible. The codebase in Github is the same as I ran from my local machine. 

## Project Objectives
- Using Play framework, and learning about web development frameworks and architecture.
- Implementing a web app using java: routing, controllers, models, views. 
- Applying OO design concepts like encapsulation into a web app framework.
- Working with complex frameworks in teams; work assignment and schedule management.
- Front-end development.
- A bit (tiny bit) about sbt, the Scala Build Tool.

## Deliverables
* The source code of the complete web application.
* Tests, showing functionality.
 
## Program ran by Gradle
- After compile and run from sbt shell. Type in http://localhost:9000 to the web browser.
- The app runs locally only.

## Note, this project is compeleted by a team of 4, I listed my contribution below
# Chenghao Liu(cliu54)
- Created 'whale.scala.html' to represent all the current objects in the form and enable user to add new whale object to 
the form. Also enable user to do searching work.
- Created whaleSearch method for searching whale objects in WhaleController class.
- Wrote both whaleSearch test and observationSearch Test for 'WhaleController' and 'ObservationController'
- Fixed the bug for each time user adding a new whale in html the number of whale's weight is zero
- Updated whales routes file
- fixed the page CSS mismatching issues for both whale and observation search. created a couple of new scala.html files for fixing the bug.
- final review and code clean-up  

