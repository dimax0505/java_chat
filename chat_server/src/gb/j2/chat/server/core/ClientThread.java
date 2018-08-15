package gb.j2.chat.server.core;

import gb.j2.chat.library.Messages;
import gb.j2.network.SocketThread;
import gb.j2.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread{
    private String nickname;
    private boolean isAuthorized;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendString(Messages.getAuthAccept(nickname));
    }

    String getNickname () {
        return this.nickname;
    }

    void authError() {
        sendString(Messages.getAuthError());
        close();
    }

    void msgFormatError(String msg) {
        sendString(Messages.getMessageFormatError(msg));
        close();
    }
}
