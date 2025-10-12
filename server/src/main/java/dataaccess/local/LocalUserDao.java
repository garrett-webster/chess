package dataaccess.local;

import dataaccess.DataAccessException;
import dataaccess.UserDao;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDao implements UserDao {
    public Map<String, UserData> users = new HashMap<>();

    @Override
    public void createUser(UserData userData) throws DataAccessException {
        if (!users.containsKey(userData.username())) {
            users.put(userData.username(), userData);
        } else {
            throw new DataAccessException("User with username " + userData.username() + " already exists.");
        }
    }
}
