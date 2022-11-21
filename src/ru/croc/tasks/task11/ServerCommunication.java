package src.ru.croc.tasks.task11;

import java.io.*;
import java.net.Socket;

class ServerCommunication extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerCommunication(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String nickname;
        String message;
        try {
            nickname = in.readLine();
            try {
                out.write(nickname + "\n");
                out.flush();
            } catch (IOException ignored) {
            }
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("logout")) {
                        this.downServer();
                        break;
                    }
                    System.out.println("Введено: " + message);
                    for (ServerCommunication vr : Server.serverList) {
                        vr.send(message);
                    }
                }
            } catch (NullPointerException ignored) {}


        } catch (IOException e) {
            this.downServer();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }

    private void downServer() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerCommunication vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}