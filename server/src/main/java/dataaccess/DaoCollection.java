package dataaccess;

import dataaccess.local.LocalAuthDao;
import dataaccess.local.LocalUserDao;

public class DaoCollection {
    public UserDao userDao;
    public AuthDao authDao;

    public DaoCollection() {
        this.userDao = new LocalUserDao();
        this.authDao = new LocalAuthDao();
    }
}
