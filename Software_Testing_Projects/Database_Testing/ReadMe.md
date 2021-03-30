Database Testing

-- This is project is a good practice of database testing, spring framework and SQL. Spring annotation is used for 
initializing the interface object and automatically generating the student id. 

-- This is a course-based project. The source code is provided for the testing. My test code located in  
src/test/.../DatabaseTest.java. I manually added the @Query notation in StudentRepository.java interface and added
an override equals method in Student.java to check the context of the return lists. 

ps. StudentRepository.java can be found under src/main/java/com/example/data
    Student.java can be found under src/main/java/com/example/domain
    
-- My test is based on the five following functionality: 1. add new students in the database. 2. detele selected 
students from the database. 3. find the student by his last name. 4. find all active status students. 
5. Sort students by their first name.


