package validation;

import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validate() {

    }

    @Test
    @DisplayName("Test assert Exception")
    void testGeneralException() {
        Validator<Student> studentValidator = new StudentValidator();
        Student s = new Student("1", "Judy", 100);

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            studentValidator.validate(s);
        });
        System.out.println(exception.getMessage());
        // assertEquals("Group invalid!", exception.getMessage());
    }
}