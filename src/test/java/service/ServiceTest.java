package service;

import domain.Grade;
import domain.Homework;
import domain.Pair;
import domain.Student;
import org.junit.jupiter.api.*;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private static Service serviceBefore;

    @BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        serviceBefore = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllStudents() {
        Collection<Student> students = (Collection<Student>) serviceBefore.findAllStudents();
        int before = students.size();
        Student student = new Student("92", "Johny", 333);
        serviceBefore.saveStudent(student.getID(), student.getName(), student.getGroup());
        students = (Collection<Student>) serviceBefore.findAllStudents();
        int after = students.size();
        serviceBefore.deleteStudent(student.getID());
        assertTrue(before == after - 1);
    }

    @Test
    void findAllHomework() {
        Collection<Homework> homeworks = (Collection<Homework>) serviceBefore.findAllHomework();
        int before = homeworks.size();
        Homework homework = new Homework("55", "valami", 4, 3);
        serviceBefore.saveHomework(homework.getID(), homework.getDescription(), homework.getDeadline(), homework.getStartline());
        homeworks = (Collection<Homework>) serviceBefore.findAllHomework();
        int after = homeworks.size();
        serviceBefore.deleteHomework(homework.getID());
        assertTrue(before == after - 1);
    }

    @Test
    void findAllGrades() {
        // ?
    }

    @Test
    @DisplayName("add valid student")
    void saveValidStudent() {
        Student student = new Student("90", "Johny", 532);
        int result = serviceBefore.saveStudent(student.getID(), student.getName(), student.getGroup());
        assertEquals(result, 1);
        serviceBefore.deleteStudent(student.getID());
    }

    @Test
    @DisplayName("add inValid student")
    void saveInvalidStudent() {
        assertThrows(ValidationException.class, () -> {
            Student student = new Student("99", "Johny", 11532);
            int result = serviceBefore.saveStudent(student.getID(), student.getName(), student.getGroup());
            assertEquals(result, 0);
            serviceBefore.deleteStudent(student.getID());
        });
    }

    @Test
    void saveValidHomework() {
        Homework homework = new Homework("55", "valami", 4, 3);
        int result = serviceBefore.saveHomework(homework.getID(), homework.getDescription(), homework.getDeadline(), homework.getStartline());
        assertEquals(result, 1);
        serviceBefore.deleteStudent(homework.getID());
    }

    @Test
    void saveGrade() {
    }

    @Test
    void deleteStudent() {
        Collection<Student> students = (Collection<Student>) serviceBefore.findAllStudents();
        int before = students.size();
        Student student = new Student("92", "Johny", 333);
        serviceBefore.saveStudent(student.getID(), student.getName(), student.getGroup());
        serviceBefore.deleteStudent(student.getID());
        Collection<Student> studentsAfter = (Collection<Student>) serviceBefore.findAllStudents();
        int after = students.size();
        assertAll(() -> assertEquals(before, after),
                () -> assertFalse(studentsAfter.contains(student))
        );
    }

    @Test
    void deleteHomework() {
        Collection<Homework> homeworks = (Collection<Homework>) serviceBefore.findAllHomework();
        int before = homeworks.size();
        Homework homework = new Homework("55", "valami", 4, 3);
        serviceBefore.saveHomework(homework.getID(), homework.getDescription(), homework.getDeadline(), homework.getStartline());
        serviceBefore.deleteHomework(homework.getID());
        Collection<Homework> homeworksAfter = (Collection<Homework>) serviceBefore.findAllHomework();
        int after = homeworks.size();
        assertAll(() -> assertEquals(before, after),
                () -> assertFalse(homeworksAfter.contains(homework))
        );
    }

    @Test
    @DisplayName("add valid grade")
    void saveValidGrade() {
        Homework homework = new Homework("55", "valami", 4, 3);
        Student student = new Student("92", "Johny", 333);
        serviceBefore.saveStudent(student.getID(), student.getName(), student.getGroup());
        serviceBefore.saveHomework(homework.getID(), homework.getDescription(), homework.getDeadline(), homework.getStartline());
        Grade grade = new Grade(new Pair<>(student.getID(), homework.getID()), 9.5, 3, "Cool");
        int result = serviceBefore.saveGrade(grade.getID().getObject1(), grade.getID().getObject2(), grade.getGrade(), grade.getDeliveryWeek(), grade.getFeedback());
        serviceBefore.deleteHomework(homework.getID());
        serviceBefore.deleteStudent(student.getID());
        assertEquals(result, 0);
    }

    @Test
    @DisplayName("add invalid grade")
    void saveInvalidGrade() {
        Grade grade = new Grade(new Pair<>("", ""), -1, 3, "Cool");
        int result = serviceBefore.saveGrade(grade.getID().getObject1(), grade.getID().getObject2(), grade.getGrade(), grade.getDeliveryWeek(), grade.getFeedback());
        assertEquals(result, -1);
    }

    @Test
    void updateStudent() {
    }

    @Test
    void updateHomework() {
    }

    @Test
    void extendDeadline() {
    }

    @Test
    void createStudentFile() {
    }
}