package src.ru.croc.tasks.task11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<ServerCommunication> serverList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2022);
        System.out.println("Сервер запущен.");
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Новое соединение.");
                try {
                    ServerCommunication element = new ServerCommunication(socket);
                    element.startThread();
                    serverList.add(element);

                } catch (Exception e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}