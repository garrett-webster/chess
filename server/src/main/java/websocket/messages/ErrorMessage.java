package websocket.messages;

public class ErrorMessage extends ServerMessage{
    public String error;

    public ErrorMessage(String error) {
        super(ServerMessage.ServerMessageType.ERROR);
        this.error = error;
    }
}
