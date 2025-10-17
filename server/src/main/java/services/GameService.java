package services;

import dataaccess.DaoCollection;
import dataaccess.DataAccessException;
import dataaccess.exceptions.AlreadyTakenException;
import dataaccess.exceptions.BadRequestException;
import dataaccess.exceptions.NotAValidColorException;
import dataaccess.exceptions.UserNotValidatedException;
import requestobjects.CreateRequest;
import requestobjects.CreateResult;
import requestobjects.JoinRequest;
import requestobjects.ListResult;

import java.util.Objects;

public class GameService extends Service{
    DaoCollection DAOs;
    public GameService(DaoCollection DAOs) {
        this.DAOs = DAOs;
    }

    public ListResult list(String token) throws UserNotValidatedException {
        if(DAOs.authDao.authenticateToken(token) == null){throw new UserNotValidatedException("Not validated");}

        return new ListResult(DAOs.gameDao.list());
    }

    public CreateResult create(String token, CreateRequest request) throws DataAccessException, BadRequestException {
        if(DAOs.authDao.authenticateToken(token) == null) {throw new UserNotValidatedException("Not validated");}
        checkForBadRequest(request.gameName());

        int id = DAOs.gameDao.create(request);

        return new CreateResult(id);
    }

    public void join(String token, JoinRequest request) throws UserNotValidatedException, AlreadyTakenException {
        String username = DAOs.authDao.authenticateToken(token);

        if(username == null) {throw new UserNotValidatedException("Not validated");}
        checkForBadRequest(request.playerColor(), request.gameID(), DAOs.gameDao.getGame(request.gameID()));
        if(!Objects.equals(request.playerColor(), "WHITE") && !Objects.equals(request.playerColor(), "BLACK")){
            throw new NotAValidColorException("Not a valid color");
        }

        DAOs.gameDao.join(request, username);
    }
}
