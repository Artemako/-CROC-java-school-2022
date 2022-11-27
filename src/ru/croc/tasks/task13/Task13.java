package src.ru.croc.tasks.task13;

public class Task13 {
    public static void main(String[] args) {
        User kolya = new User("user001");
        kolya.reloadDataUser();

        User danya = new User("user002");
        danya.reloadDataUser();

        User andrei = new User("user003");
        andrei.reloadDataUser();

        User artem = new User("user004");
        artem.reloadDataUser();

        User micha = new User("user005");
        micha.reloadDataUser();

        User dima = new User("user006");
        dima.reloadDataUser();

        kolya.recommendation();
        danya.recommendation();
        andrei.recommendation();
        artem.recommendation();
        micha.recommendation();
        dima.recommendation();
    }
}
