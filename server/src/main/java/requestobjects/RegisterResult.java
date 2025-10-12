package requestobjects;

public class RegisterResult extends Result {
    String username;
    String authToken;
    String error;

    public RegisterResult(String username, String authToken, String error) {
        this.username = username;
        this.authToken = authToken;
        this.error = error;
    }

    @Override
    public String toJson(String... keysAndValues) {
        return buildJson("username", username, "authtoken", authToken);
    }
}
