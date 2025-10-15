package services;

import dataaccess.DaoCollection;
import io.javalin.http.Context;

public class AppService {
    public DaoCollection DAOs;

    public AppService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public void clear(Context context) {
        this.DAOs.userDao.clear();
        this.DAOs.authDao.clear();
    }
}