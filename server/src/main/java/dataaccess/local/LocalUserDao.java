package dataaccess.local;

import dataaccess.UserDao;
import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class LocalUserDao implements UserDao {
    public Map<String, UserData> users = new HashMap<>();

    @Override
    public void createUser(UserData userData) {
        users.put(userData.username(), userData);
    }

    @Override
    public UserData getUser(String username) {
        return users.get(username);
    }

    @Override
    public void clear() {
        users = new HashMap<>();
    }
}
