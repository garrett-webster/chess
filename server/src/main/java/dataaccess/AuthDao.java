package dataaccess;

public interface AuthDao extends DAO {
    void addAuthToken(String username, String token);
    String authenticateToken(String token);

//    public void getAuth(int authId) throws DataAccessException;
//
//    public void updateAuth(AuthData authData) throws DataAccessException;
//
    void remove(String token) throws DataAccessException;
//
    void clear();
}
