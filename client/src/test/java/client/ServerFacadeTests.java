package client;

import dataaccess.DataAccessException;
import exception.ResponseException;
import org.junit.jupiter.api.*;
import requestobjects.LoginRequest;
import requestobjects.RegisterRequest;
import server.Server;
import server.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        String url = "http://localhost:" + port;
        serverFacade = new ServerFacade(url);
        System.out.println("Started test HTTP server on " + port);
    }

    @BeforeEach
    public void clearDb() throws DataAccessException {
        server.daos.userDao.clear();
        server.daos.gameDao.clear();
        server.daos.authDao.clear();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @Test
    public void createUserTest() throws ResponseException {
        RegisterRequest request = new RegisterRequest("Garrett", "password", "garrett@email.com");

        Assertions.assertEquals("Garrett", serverFacade.createUser(request).username());
    }

    @Test
    public void createDuplicateUserTest() throws ResponseException {
        RegisterRequest request = new RegisterRequest("Garrett", "password", "garrett@email.com");
        serverFacade.createUser(request);
        Assertions.assertThrows(NullPointerException.class, () -> serverFacade.createUser(request));
    }

    @Test
    public void loginUserTest() throws ResponseException {
        RegisterRequest rRequest = new RegisterRequest("Garrett", "password", "garrett@email.com");
        serverFacade.createUser(rRequest);

        LoginRequest lRequest = new LoginRequest("Garrett", "password");
        Assertions.assertNotNull(serverFacade.loginUser(lRequest).authToken());
    }

    @Test
    public void loginInvalidUserTest() {
        LoginRequest lRequest = new LoginRequest("Garrett", "password");
        Assertions.assertThrows(NullPointerException.class, () -> serverFacade.loginUser(lRequest));
    }

    @Test
    public void logoutUserTest() throws ResponseException {
        RegisterRequest rRequest = new RegisterRequest("Garrett", "password", "garrett@email.com");
        String token = serverFacade.createUser(rRequest).authToken();

        Assertions.assertDoesNotThrow(() -> serverFacade.logoutUser(token));
    }

    @Test
    public void logoutInvalidUserTest() {
        Assertions.assertThrows(NullPointerException.class, () -> serverFacade.logoutUser("NonsenseToken"));
    }

    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

}
