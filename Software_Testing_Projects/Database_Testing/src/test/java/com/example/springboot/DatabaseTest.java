package com.example.springboot;

import com.example.springboot.data.StudentRepository;
import com.example.springboot.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void createTest() {
        // add one student here
        Student studentObj = new Student("chenghao", "liu", 1);
        studentRepository.save(studentObj);

        // test if it is here
        Long objectId = studentObj.getId();
        studentRepository.findById(objectId).get().getFirstName();

        //check firstname
        assertEquals("chenghao",studentRepository.findById(objectId).get().getFirstName());

        //check last name
        assertEquals("liu",studentRepository.findById(objectId).get().getLastName());

        // check status
        assertEquals(1,studentRepository.findById(objectId).get().getActiveStatus());
    }

    @Test
    public void deleteTest() {
        // add instances to database
        Student obj1 = new Student("chenghao", "liu", 1);
        Student obj2 = new Student("Jack", "Ma", 0);
        Student obj3 = new Student("Chris", "Wu", 1);
        studentRepository.save(obj1);
        studentRepository.save(obj2);
        studentRepository.save(obj3);

        // get corresponding ids
        Long id1 = obj1.getId();
        Long id2 = obj2.getId();
        Long id3 = obj3.getId();

        // before deletion
        assertTrue(studentRepository.existsById(id1));
        assertTrue(studentRepository.existsById(id2));
        assertTrue(studentRepository.existsById(id3));

        // delete id1 id2
        studentRepository.deleteById(id1);
        studentRepository.deleteById(id2);

        // check id1& id2 gone, id3 is here
        assertFalse(studentRepository.existsById(id1));
        assertFalse(studentRepository.existsById(id1));
        assertTrue(studentRepository.existsById(id3));

    }

    @Test
    public void findByLastNameTest() {
        // add instances to database
        Student obj1 = new Student("chenghao", "liu", 1);
        Student obj2 = new Student("Jack", "Ma", 0);
        Student obj3 = new Student("Chris", "Wu", 1);
        Student obj4 = new Student("zheng", "yin", 1);
        Student obj5 = new Student("Alex", "Howard", 1);
        Student obj6 = new Student("James", "Harden", 1);

        studentRepository.save(obj1);
        studentRepository.save(obj2);
        studentRepository.save(obj3);

        // call interface method
        List<Student> st = studentRepository.findByLastName("liu");

        // check first name
        assertEquals(obj1.getFirstName(),st.get(0).getFirstName());

        // check last name
        assertEquals(obj1.getLastName(),st.get(0).getLastName());

        // check status
        assertEquals(obj1.getActiveStatus(),st.get(0).getActiveStatus());

    }

    @Test
    public void findAllActiveStudentsTest() {
        // add instances to database
        Student obj1 = new Student("chenghao", "liu", 0);
        Student obj2 = new Student("Jack", "Ma", 0);
        Student obj3 = new Student("Chris", "Wu", 0);
        Student obj4 = new Student("zheng", "yin", 1);
        Student obj5 = new Student("Alex", "Howard", 1);
        Student obj6 = new Student("James", "Harden", 1);

        // add instances to database
        studentRepository.save(obj1);
        studentRepository.save(obj2);
        studentRepository.save(obj3);
        studentRepository.save(obj4);
        studentRepository.save(obj5);
        studentRepository.save(obj6);
        // call interface method
        List<Student> activeList = studentRepository.findAllActiveStudents();

        // check existence of the active objects (obj4 ojb5 obj6)
        assertTrue(activeList.contains(obj4));
        assertTrue(activeList.contains(obj5));
        assertTrue(activeList.contains(obj6));
    }

    @Test
    public void sortedFirstNamesTest() {
        // add instances to database
        Student obj1 = new Student("Chenghao", "liu", 0);
        Student obj2 = new Student("Jack", "Ma", 0);
        Student obj3 = new Student("Chris", "Wu", 0);
        Student obj4 = new Student("Zheng", "yin", 1);
        Student obj5 = new Student("Alex", "Howard", 1);
        Student obj6 = new Student("James", "Harden", 1);

        // add instances to database
        studentRepository.save(obj1);
        studentRepository.save(obj2);
        studentRepository.save(obj3);
        studentRepository.save(obj4);
        studentRepository.save(obj5);
        studentRepository.save(obj6);

        // call interface method
        List<Student> sortedList = studentRepository.findAllFirstNameSort();

        // check instances of sorted list
        assertTrue(sortedList.get(0).equals(obj5));
        assertTrue(sortedList.get(1).equals(obj1));
        assertTrue(sortedList.get(2).equals(obj3));
        assertTrue(sortedList.get(3).equals(obj2));
        assertTrue(sortedList.get(4).equals(obj6));
        assertTrue(sortedList.get(5).equals(obj4));

    }
}
