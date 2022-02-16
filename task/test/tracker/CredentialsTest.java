package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    @DisplayName("Validate good credentials test")
    void validateGoodCredentials() {
        String valid = "Jane Spark jspark@gmail.com";
        boolean result = Credentials.validate(valid);
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate it fails upon bad credentials")
    void validateBadCredentials() {
        String invalid = "J. D. Sprocket jsproket@yahoo.com";
        boolean result = Credentials.validate(invalid);
        assertFalse(result);
    }

    @Test
    @DisplayName("Validate correct points")
    void validateCorrectPoints() {
        String valid = "10000 10 2 2 2";
        boolean result = Credentials.validatePoints(valid);
        assertTrue(result);
    }

    @Test
    @DisplayName("It should fail as more than five parts")
    void validateWrongPoints() {
        String invalid = "10000 7 7 7 7 7";
        boolean result =  Credentials.validatePoints(invalid);
        assertFalse(result);
    }

    @Test
    @DisplayName("It should fail as one of the point value is negative")
    void validateWrongPoints2() {
        String invalid = "10000 -1 2 2 2";
        boolean result =  Credentials.validatePoints(invalid);
        assertFalse(result);
    }

    @Test
    @DisplayName("It should fail as one of the point value is non integer")
    void validateWrongPoints3() {
        String invalid = "10000 1 ? 2 2";
        boolean result =  Credentials.validatePoints(invalid);
        assertFalse(result);
    }
}