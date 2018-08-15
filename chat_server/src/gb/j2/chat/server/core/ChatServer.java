package gb.j2.chat.server.core;

import gb.j2.chat.library.Bank_Messages;
import gb.j2.network.ServerSocketThread;
import gb.j2.network.ServerSocketThreadListener;
import gb.j2.network.SocketThread;
import gb.j2.network.SocketThreadListener;


import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    private ServerSocketThread server;
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss: ");
    private ChatServerListener listener;
    private Vector<SocketThread> clients = new Vector<>();

    public ChatServer (ChatServerListener listener) {
        this.listener = listener;
    }


    public void start(int port) {
        if (server != null && server.isAlive()) {
            putLog("Server is already running");
        } else {
            server = new ServerSocketThread(this, "Chat server", port, 3000);
        }
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Server is not running");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        msg = dateFormat.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() + ": " + msg;
        System.out.println(msg);
    }

    /**
     * Server Socket Thread Events
     * */

    @Override
    public void onServerThreadStart(ServerSocketThread thread) {
        putLog("server thread start");
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("server socket created");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, Socket socket) {
        putLog("socket accepted");
        String name = "SocketThread" + socket.getInetAddress() + ":" + socket.getPort();
        new SocketThread(this, name, socket);
    }

    @Override
    public void onAcceptTimeout(ServerSocketThread thread, ServerSocket server) {

        putLog("accept timeout");
    }

    @Override
    public void onServerThreadException(ServerSocketThread thread, Exception e) {
        putLog("server thread exception");
    }

    @Override
    public void onServerThreadStop(ServerSocketThread thread) {
        putLog("server thread stop");
    }

    /**
     * Socket Thread Events
     * */

    @Override
    public synchronized void onStartSocketThread(SocketThread thread, Socket socket) {
        putLog("start socket thread");
    }

    @Override
    public synchronized void onStopSocketThread(SocketThread thread) {
        putLog("stop socket thread");
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String msg) {
//        for (int i = 0; i < clients.size(); i++) {
//            SocketThread clients
//
//        }

    }

    @Override
    public synchronized void onSocketThreadIsReady(SocketThread thread, Socket socket) {
        clients.add(thread);
        putLog("socket thread is ready");
    }

    @Override
    public synchronized void onSocketThreadException(SocketThread thread, Exception e) {
        putLog("socket thread exception");
    }

}
