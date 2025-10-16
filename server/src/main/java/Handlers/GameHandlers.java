package Handlers;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.exceptions.BadRequestException;
import dataaccess.exceptions.UserNotValidatedException;
import requestobjects.CreateRequest;
import requestobjects.CreateResult;
import services.GameService;

import io.javalin.http.Context;

import static server.Server.buildJson;
import static server.Server.setErrorContext;

public class GameHandlers {
    GameService gameService;
    Gson serializer = new Gson();
    public GameHandlers(GameService gameService) {
        this.gameService = gameService;
    }

//    public void list(Context context) {
//        try {
//            gameService.
//        } catch (Exception e) {
//        }
//    }

    public void create(Context context) {
        try {
            CreateResult result = gameService.create(
                    context.header("authorization"), serializer.fromJson(context.body(), CreateRequest.class)
            );
            context.result(buildJson("gameID", result.gameID()));
        } catch (UserNotValidatedException e) {
            setErrorContext(context, "401 Unauthorized Error: Unauthorized", 401);
        } catch (DataAccessException e) {
            setErrorContext(context,"500 Data Access Error: Failed to create new game", 500);
        } catch (BadRequestException e) {
            setErrorContext(context,"400 Bad Request Error: Some field was missing", 400);
        }
    }
}
