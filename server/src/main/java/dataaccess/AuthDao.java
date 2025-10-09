package dataaccess;

import model.AuthData;

public interface AuthDao {
    public void createAuth(AuthData authData) throws DataAccessException;

    public void getAuth(int authId) throws DataAccessException;

    public void updateAuth(AuthData authData) throws DataAccessException;

    public void deleteAuth(int authId) throws DataAccessException;

    public void clear();
}
