package app.songy.com.lib_rn.codepush.react;

public final class CodePushNotInitializedException extends RuntimeException {

    public CodePushNotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodePushNotInitializedException(String message) {
        super(message);
    }
}