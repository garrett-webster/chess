package dataaccess;

import dataaccess.local.LocalUserDao;

public class DaoCollection {
    public UserDao userDao;
    public DaoCollection() {
        this.userDao = new LocalUserDao();
    }
}
