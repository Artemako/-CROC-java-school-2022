package src.ru.croc.tasks.task11;

public class ClientTwo {
    public static void main(String[] args) {
        ClientCommunication client = new ClientCommunication("localhost", 2022);
        client.startThreads();
    }
}
