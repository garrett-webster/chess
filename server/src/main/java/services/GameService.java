package services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.BadRequestException;
import dataaccess.exceptions.UserNotValidatedException;
import requestobjects.CreateRequest;
import requestobjects.CreateResult;
import requestobjects.ListResult;

public class GameService extends Service{
    DaoCollection DAOs;
    public GameService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public ListResult list(String token) throws UserNotValidatedException {
        if(DAOs.authDao.authenticateToken(token) == null) throw new UserNotValidatedException("Not validated");

        return new ListResult(DAOs.gameDao.list());
    }

    public CreateResult create(String token, CreateRequest request) throws DataAccessException, BadRequestException {
        if(DAOs.authDao.authenticateToken(token) == null) throw new UserNotValidatedException("Not validated");
        checkForBadRequest(request.gameName());

        int id = DAOs.gameDao.create(request);

        return new CreateResult(id);
    }
}
