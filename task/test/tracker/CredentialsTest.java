package tracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsTest {

    @Test
    void validateGoodCredentials() {
        String valid = "Jane Spark jspark@gmail.com";
        boolean result = Credentials.validate(valid);
        assertTrue(result);
    }
}