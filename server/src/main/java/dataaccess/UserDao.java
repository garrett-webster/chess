package dataaccess;

import model.UserData;

public abstract class UserDao extends Dao {
    public abstract void createUser(UserData userData) throws DataAccessException;

//    public UserData getUser(int userId) throws DataAccessException;

    public abstract UserData getUser(String username) throws DataAccessException;

    public abstract boolean validateWithPassword(String username, String password);
//
//    public UserData getUser(String userName);
//
//    public void updateUser(UserData userData) throws DataAccessException;
//
//    public void deleteUser(int userId) throws DataAccessException;
//
//    public UserData validateUser(String username, String password);
//
public abstract void clear();
}
