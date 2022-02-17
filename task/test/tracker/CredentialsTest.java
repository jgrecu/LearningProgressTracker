package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest(name = "{index} test for null and/or empty credentials")
    @NullAndEmptySource
    void validateBadCredentials2(String arg) {
        boolean result = Credentials.validate(arg);
        assertFalse(result);
    }


    @ParameterizedTest
    @ValueSource(strings = {"25841 4 10 5 0", "28405 0 8 7 5", "10010 1 5 2 2", "10001 20 10 2 2"})
    void validateCorrectPoints(String arg) {
        boolean result = Credentials.validatePoints(arg);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10000 7 7 7 7 7", "10000 -1 2 2 2", "10000 1 ? 2 2", "10000 ? 10 -2 2"})
    void validateWrongPoints(String arg) {
        boolean result = Credentials.validatePoints(arg);
        assertFalse(result);
    }

    @ParameterizedTest(name = "{index} test for null and/or empty points")
    @NullAndEmptySource
    void validateWrongPoints2(String arg) {
        boolean result = Credentials.validatePoints(arg);
        assertFalse(result);
    }
}