package services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.BadRequestException;
import dataaccess.exceptions.UserNotValidatedException;
import requestobjects.CreateRequest;
import requestobjects.CreateResult;

public class GameService extends Service{
    DaoCollection DAOs;
    public GameService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public CreateResult create(String token, CreateRequest request) throws DataAccessException, BadRequestException {
        if(DAOs.authDao.authenticateToken(token) == null) throw new UserNotValidatedException("Not validated");
        checkForBadRequest(request.gameName());

        int id = DAOs.gameDao.create(request);

        return new CreateResult(id);
    }
}
