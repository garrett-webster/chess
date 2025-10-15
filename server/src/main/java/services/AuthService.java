package services;
import dataaccess.DaoCollection;

import java.util.UUID;

public class AuthService {
    public DaoCollection DAOs;
    public AuthService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public void clearAuthTokens() {

    }

    public String generateNewToken(String username) {
        String newToken = UUID.randomUUID().toString();

        this.DAOs.authDao.addAuthToken(username, newToken);
        return newToken;
    }
}
