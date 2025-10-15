package services;
import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.UserNotValidatedException;

import java.util.UUID;

public class AuthService {
    public DaoCollection DAOs;
    public AuthService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public String generateNewToken(String username) {
        String newToken = UUID.randomUUID().toString();

        this.DAOs.authDao.addAuthToken(username, newToken);
        return newToken;
    }

    public void logout(String token) throws DataAccessException, UserNotValidatedException {
        if (DAOs.authDao.authenticateToken(token) == null) throw new UserNotValidatedException("Not validated");
        DAOs.authDao.remove(token);
    }
}
