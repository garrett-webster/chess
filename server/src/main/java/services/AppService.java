package services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import io.javalin.http.Context;

public class AppService extends Service{
    public DaoCollection daos;

    public AppService(DaoCollection daos) {
        this.daos = daos;
    }

    public void clear(Context context) throws DataAccessException {
        this.daos.userDao.clear();
        this.daos.authDao.clear();
        this.daos.gameDao.clear();
    }
}