package dataaccess;

public abstract class AuthDao extends Dao {
    abstract public void addAuthToken(String username, String token);
    abstract public String authenticateToken(String token);
    abstract public void remove(String token) throws DataAccessException;
    abstract public void clear();
}
