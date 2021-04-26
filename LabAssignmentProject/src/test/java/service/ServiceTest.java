package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ServiceTest {

    public static Service service;

    @org.junit.jupiter.api.BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void findAllStudents() {
    }

    @org.junit.jupiter.api.Test
    void findAllHomework() {
    }

    @org.junit.jupiter.api.Test
    void findAllGrades() {
    }

    @org.junit.jupiter.api.Test
    void saveStudent() {
    }

    @org.junit.jupiter.api.Test
    @DisplayName("checking if homework save works")
    void saveValidHomework() {
        Homework hw = new Homework("77", "some easy homework", 6, 2);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        assertEquals(0, result);
        //assertTrue(result == 1);
        service.deleteHomework(hw.getID());
    }

    @org.junit.jupiter.api.Test
    void saveGrade() {
    }

    @org.junit.jupiter.api.Test
    void deleteStudent() {
    }

    @org.junit.jupiter.api.Test
    void deleteHomework() {
    }

    @org.junit.jupiter.api.Test
    void updateStudent() {
    }

    @org.junit.jupiter.api.Test
    void updateHomework() {
    }

    @org.junit.jupiter.api.Test
    void extendDeadline() {
    }

    @org.junit.jupiter.api.Test
    void createStudentFile() {
    }

    @Test
    void assertAllTest(){
        Student s = new Student("99", "Johan", 533);
        assertAll(
                "student",
                () -> assertEquals("99", s.getID()),
                () -> assertEquals("Johan", s.getName())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 55, 533})
    void testStudentAddByGroup(int group){
        assumeTrue(group >= 0);
        Student s = new Student("99", "Johan", group);
        int result = service.saveStudent(s.getID(), s.getName(), s.getGroup());
        assertEquals(1, result);
        service.deleteStudent(s.getID());
    }


}