package gb.j2.chat.library;


import gb.j2.network.SocketThread;
import gb.j2.network.SocketThreadListener;

import java.net.Socket;
import java.util.Vector;

public class Bank_Messages implements SocketThreadListener{
    Vector message = new Vector();
  //  Bank_Messages(){ }



    public Vector getMessage() {
        return message;
    }

    @Override
    public void onStartSocketThread(SocketThread thread, Socket socket) {

    }

    @Override
    public void onStopSocketThread(SocketThread thread) {

    }

    @Override
    public void onReceiveString(SocketThread thread, Socket socket, String msg) {
        message.add(thread.getName() + ":" + msg);
        System.out.println(message.firstElement());
        thread.sendString(String.valueOf(message.firstElement()));

    }

    @Override
    public void onSocketThreadIsReady(SocketThread thread, Socket socket) {

    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {

    }
}
