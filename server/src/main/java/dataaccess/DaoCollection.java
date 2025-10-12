package dataaccess;

import dataaccess.local.localUserDao;

public class DaoCollection {
    public UserDao userDao;
    public DaoCollection() {
        this.userDao = new localUserDao();
    }
}
