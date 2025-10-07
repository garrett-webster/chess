package dataaccess;

import model.UserData;

public class UserDao {
    public void createUser(UserData userData) throws DataAccessException {
        return;
    }

    public UserData getUser(int userId) throws DataAccessException {
        return null;
    }

    public UserData getUser(String userName) {
        return null;
    }

    public void updateUser(UserData userData) throws DataAccessException {
        return;
    }

    public void deleteUser(int userId) throws DataAccessException {
        return;
    }

    public UserData validateUser(String username, String password) {
        return null;
    }

    public void clear() {
        return;
    }
}
