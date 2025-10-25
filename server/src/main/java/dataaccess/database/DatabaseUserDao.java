package dataaccess.database;

import dataaccess.UserDao;
import model.UserData;

public class DatabaseUserDao implements UserDao {
    @Override
    public void createUser(UserData userData) {
    }

    @Override
    public UserData getUser(String username) {
        return null;
    }

    @Override
    public boolean validateWithPassword(String username, String password) {
        return false;
    }

    @Override
    public void clear() {
    }
}
