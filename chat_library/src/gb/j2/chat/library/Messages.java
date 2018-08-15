package gb.j2.chat.library;

public class Messages {
    /*
        /bcast§time§src§<message>
        /auth_request§login§password
        /auth_accept§nickname
        /auth_error
        /msg_format_error§<message>
     */
    public static final String DELIMITER = "§";
    public static final String AUTH_REQUEST = "/auth_request";
    public static final String AUTH_ACCEPT = "/auth_accept";
    public static final String AUTH_ERROR = "/auth_error";
    public static final String BROADCAST = "/bcast";
    public static final String MESSAGE_FORMAT_ERROR = "/msg_fmt_err";

    public static String getAuthRequest(String login, String password) {
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept(String nickname) {
        return AUTH_ACCEPT + DELIMITER + nickname;
    }

    public static String getAuthError() {
        return AUTH_ERROR;
    }

    public static String getBroadcast(String src, String msg) {
        return BROADCAST + DELIMITER + System.currentTimeMillis() +
                DELIMITER + src + DELIMITER + msg;
    }

    public static String getMessageFormatError(String msg) {
        return MESSAGE_FORMAT_ERROR + DELIMITER + msg;
    }

}
