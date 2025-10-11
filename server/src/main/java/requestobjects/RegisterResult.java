package requestobjects;

public class RegisterResult extends Result {
    String username;
    String authToken;

    public RegisterResult(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }

    @Override
    public String toJson(String... keysAndValues) {
        return buildJson("username", username, "authtoken", authToken);
    }
}
