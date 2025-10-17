package passoff.service;

import dataaccess.DaoCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import services.AuthService;

public class AuthServiceTests {
    public static AuthService authService;
    @BeforeEach
    public void setup() {
        DaoCollection DAOs = new DaoCollection();
        authService = new AuthService(DAOs);
        authService.DAOs.authDao.addAuthToken("Garrett", "dummytoken1");
        authService.DAOs.authDao.addAuthToken("Jerome", "dummytoken2");
    }

    @Nested
    class LogoutTests {
        @Test
        public void successfulLogout() {
            Assertions.assertDoesNotThrow(() ->  authService.logout("dummytoken1"));
        }

        @Test
        public void unsuccessfulLogout() {
            Assertions.assertThrows(Exception.class, () ->  authService.logout("dummytoken"));
        }
    }
}
