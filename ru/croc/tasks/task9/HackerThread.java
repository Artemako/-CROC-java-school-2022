package ru.croc.tasks.task9;

public class HackerThread implements Runnable {
    private final int threadNumber;
    private final int numberOfThreads;
    private final int lenPassword;
    private final String searchingHash;
    private static final int lenAlphabet = 26;
    private final long numberOfAllPassword;
    private static volatile boolean isPasswordFound = false;


    public HackerThread(int threadNumber, int numberOfThreads, int lenPassword, String searchingHash) {
        this.threadNumber = threadNumber;
        this.numberOfThreads = numberOfThreads;
        this.lenPassword = lenPassword;
        this.searchingHash = searchingHash;
        numberOfAllPassword = (long) (Math.pow(lenAlphabet, lenPassword));
    }

    public void run() {
        long start = (numberOfAllPassword * threadNumber) / numberOfThreads;
        long finish = (numberOfAllPassword * (threadNumber + 1)) / numberOfThreads;

        for (long index = start; index <= finish && !isPasswordFound; index++) {
            String password = indexToPassword(index);
            if (PasswordConversion.hashPassword(password).equals(searchingHash)) {
                System.out.println(password); //passwrd - ответ
                isPasswordFound = true;
            }
        }
    }

    private String indexToPassword(long index) {
        int[] intPassword = new int[lenPassword];
        for (int i = 0; i < lenPassword; i++) {
            intPassword[i] = (int) (index % lenAlphabet);
            index /= lenAlphabet;
        }
        StringBuilder password = new StringBuilder("");
        for (int i = 0; i < lenPassword; i++) {
            password.append((char) ('a' + intPassword[i]));
        }
        return password.toString();
    }
}
