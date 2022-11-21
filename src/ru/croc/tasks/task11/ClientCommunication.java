package src.ru.croc.tasks.task11;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientCommunication {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader reader;
    private String address;
    private int port;
    private String nickname;
    private Date time;
    private String hhmmssTime;

    public ClientCommunication(String address, int port) {
        this.address = address;
        this.port = port;
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            System.out.println("Ошибка при подключении сокета.");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.print("Введите никнейм: ");
            try {
                nickname = reader.readLine();
                out.write("Привет, " + nickname + ".\n");
                out.flush();
            } catch (IOException ignored) {
            }

            new ReadMessages().start();
            new WriteMessages().start();

        } catch (Exception e) {
            ClientCommunication.this.downClient();
        }
    }

    private void downClient() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }

    private class ReadMessages extends Thread {
        @Override
        public void run() {
            String message;
            try {
                while (true) {
                    message = in.readLine();
                    if (message.equals("logout")) {
                        ClientCommunication.this.downClient();
                        break;
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                ClientCommunication.this.downClient();
            }
        }
    }

    public class WriteMessages extends Thread {
        @Override
        public void run() {
            while (true) {
                String message;
                try {
                    time = new Date();
                    hhmmssTime = new SimpleDateFormat("HH:mm:ss").format(time);
                    message = reader.readLine();
                    if (message.equals("logout")) {
                        out.write("logout" + "\n");
                        ClientCommunication.this.downClient();
                        break;
                    } else {
                        out.write("[" + hhmmssTime + "] " + nickname + ": " + message + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    ClientCommunication.this.downClient();
                }

            }
        }
    }
}