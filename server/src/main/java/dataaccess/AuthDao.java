package dataaccess;

public interface AuthDao extends DAO {
    void addAuthToken(String username, String token);

//    public void getAuth(int authId) throws DataAccessException;
//
//    public void updateAuth(AuthData authData) throws DataAccessException;
//
//    public void deleteAuth(int authId) throws DataAccessException;
//
    void clear();
}
