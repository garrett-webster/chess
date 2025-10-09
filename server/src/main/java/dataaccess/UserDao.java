package dataaccess;

import model.UserData;

public interface UserDao {
    public void createUser(UserData userData) throws DataAccessException;

    public UserData getUser(int userId) throws DataAccessException;

    public UserData getUser(String userName);

    public void updateUser(UserData userData) throws DataAccessException;

    public void deleteUser(int userId) throws DataAccessException;

    public UserData validateUser(String username, String password);

    public void clear();
}
